package org.karaffe.compiler.tree;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.resolvers.ConstructorResolver;
import org.karaffe.compiler.resolvers.MethodResolver;
import org.karaffe.compiler.resolvers.PackageResolver;
import org.karaffe.compiler.resolvers.StaticFieldResolver;
import org.karaffe.compiler.resolvers.TypeResolver;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.InferResult;

public class Select extends AbstractNode {

    public Select(final List<Node> names) {
        super(NodeType.SELECT, names);
    }

    public Select(final Node name) {
        this(new ArrayList<>(Arrays.asList(name)));
    }

    public Select(final Node... names) {
        this(new ArrayList<>(Arrays.asList(names)));
    }

    public Select(final Node target, final Node selector) {
        super(NodeType.SELECT, new ArrayList<>(Arrays.asList(target, selector)));
    }

    public Select(String... names) {
        this(Stream.of(names).map(Name::new).collect(Collectors.toList()));
        if (names.length == 0) {
            throw new IllegalArgumentException("empty names");
        }
    }

    public List<Node> getNames() {
        return (List<Node>) this.getChildren();
    }

    public boolean isSimpleName() {
        return getChildren().size() == 1;
    }

    public boolean isAnyRef() {
        return !this.isSimpleName();
    }

    public SelectCategory getCategory(TypeContext context) {
        String findName = this.toString(".");
        if (findName.isEmpty()) {
            return SelectCategory.UNKNOWN;
        }
        if (this.isSimpleName()) {
            Optional<String> fqcnOpt = context.findFQCN(findName);
            if (fqcnOpt.isPresent()) {
                return SelectCategory.CLASSREF;
            }
            if (context.hasAlreadyDefinedName(findName)) {
                return SelectCategory.DEFREF;
            }
            return SelectCategory.UNKNOWN;
        }
        if (findName.endsWith("<init>")) {
            String simpleName = findName.replace(".<init>", "");
            Optional<String> fqcnOpt = context.findFQCN(simpleName);
            Optional<List<Constructor<?>>> ctorsOpt = fqcnOpt.map(fqcn -> fqcn + ".<init>").flatMap(ConstructorResolver::findConstructors);
            if (ctorsOpt.isPresent() && !ctorsOpt.get().isEmpty()) {
                return SelectCategory.CONSTRUCTORREF;
            }
        }
        Optional<Package> packageOpt = PackageResolver.findPackage(findName);
        if (packageOpt.isPresent()) {
            return SelectCategory.PACKAGEREF;
        }
        Optional<Class<?>> clazzOpt = TypeResolver.findClass(findName);
        if (clazzOpt.isPresent()) {
            return SelectCategory.CLASSREF;
        }
        Optional<List<Method>> methodOpt = MethodResolver.findMethods(findName);
        if (methodOpt.isPresent() && !methodOpt.get().isEmpty()) {
            return SelectCategory.METHODREF;
        }
        Optional<List<Constructor<?>>> ctorOpt = ConstructorResolver.findConstructors(findName);
        if (ctorOpt.isPresent() && !ctorOpt.get().isEmpty()) {
            return SelectCategory.CONSTRUCTORREF;
        }
        Optional<Field> fieldOpt = StaticFieldResolver.findStaticField(findName);
        if (fieldOpt.isPresent()) {
            return SelectCategory.STATICFIELDREF;
        }
        return SelectCategory.UNKNOWN;
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final List<Node> names = new ArrayList<>();
        for (final Node n : this.getChildren()) {
            if (n.isName()) {
                names.add(n);
            } else {
                final NodeList normalized = n.normalize(context);
                nodes.addAll(normalized.flatten());
                names.add(normalized.lastAssignName());
            }
        }
        final Name name = context.nextName(nodes);
        final Assign ref = new Assign(name, new Select(names));
        nodes.add(ref);
        return new NodeList(nodes);
    }

    @Override
    public String toString() {
        return String.format("(Select %s)", this.toString(" "));
    }

    public String toString(final String delimiter) {
        return String.join(delimiter, this.getNames().stream().map(t -> {
            if (t instanceof Name) {
                final Name name = (Name) t;
                return name.getName();
            }
            return t.toString();
        }).collect(Collectors.toList()));
    }

    @Override
    public String vSource() {
        return this.getNames().stream().map(Node::vSource).reduce((l, r) -> l + "." + r).orElse("?");
    }

    @Override
    public Optional<InferResult> tryTypeInference(TypeContext context) {
        if (this.getChildren().isEmpty()) {
            return Optional.empty();
        }
        String findName = this.toString(".");
        SelectCategory category = this.getCategory(context);
        if (category.equals(SelectCategory.PACKAGEREF)) {
            return Optional.empty();
        }
        if (this.getChildren().size() == 1) {
            // Name
            Node node = this.getChildren().get(0);
            if (node.isName()) {
                Name name = (Name) node;
                if (context.hasAlreadyDefinedName(name)) {
                    return context.getInferredType(name);
                }
                return Optional.of(InferResult.anyTarget(name));
            }
        }
        if (category.equals(SelectCategory.CONSTRUCTORREF)) {
            String simpleClassName = findName.replace(".<init>", "");
            String className = context.findFQCN(simpleClassName).orElse(simpleClassName);
            String newFindName = className + ".<init>";
            Optional<List<Constructor<?>>> ctorOpt = ConstructorResolver.findConstructors(newFindName);
            if (ctorOpt.isPresent()) {
                List<Constructor<?>> constructors = ctorOpt.get();
                if (constructors.isEmpty()) {
                    return Optional.empty();
                }
                return Optional.of(InferResult.mayBeApplicable(InferResult.of(className), new Name("<init>")));
            }
        }

        if (this.getChildren().size() == 2) {
            // Ref
            Node ownerNode = this.getChildren().get(0);
            Node memberNode = this.getChildren().get(1);
            if (ownerNode.isName() && memberNode.isName()) {
                Name owner = (Name) ownerNode;
                Name member = (Name) memberNode;
                Optional<InferResult> ownerTypeOpt = context.getInferredType(owner);
                Optional<InferResult> memberTypeOpt = context.getInferredType(member);
                Optional<InferResult> composedType = ownerTypeOpt.flatMap(ownerType -> memberTypeOpt.flatMap(memberType -> ownerType.compose(memberType, context)));
                return composedType;
            }
            if (ownerNode.isName()) {
                return Optional.empty();
            }
            if (memberNode.isName()) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

}
