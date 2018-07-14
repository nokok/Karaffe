package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.CompilationUnitTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.def.Defs;
import org.karaffe.compiler.base.tree.modifier.Modifiers;

import java.io.Reader;
import java.net.URI;
import java.nio.Buffer;
import java.nio.channels.Channel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertDefaultImportTask extends AbstractTask implements NoDescriptionTask, CompilationUnitTask {

    private static final Set<String> defaultImportPackages;
    private static final Set<String> defaultImportClasses;

    static {
        // load classes
        List<Class<?>> classes = Arrays.asList(
                Integer.class,
                Reader.class,
                URI.class,
                List.class,
                Stream.class,
                Function.class,
                ForkJoinPool.class,
                Buffer.class,
                Path.class,
                Channel.class
        );


        defaultImportPackages = classes.stream().map(Class::getPackage).map(Package::getName).collect(Collectors.toSet());

        defaultImportClasses = new HashSet<>(Arrays.asList(
                java.util.regex.Matcher.class.getCanonicalName(),
                java.util.regex.Pattern.class.getCanonicalName()
        ));
    }

    @Override
    public String name() {
        return "insert-default-import";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        compilationUnit.accept(new DefaultVisitor<Void>() {
            @Override
            public Tree visitCompileUnit(Tree.CompilationUnit tree, Void aVoid) {
                super.visitCompileUnit(tree, aVoid);

                List<Tree> children = tree.getChildren();
                tree.setChildren(new ArrayList<>());

                for (String defaultImport : defaultImportPackages) {
                    Def def = Defs.onDemandImportDef(Position.noPos(), tree, defaultImport);
                    def.addModifier(Modifiers.modSynthetic(def));
                    tree.addChild(def);
                    context.onFileImportDef(tree.getPos(), def);
                }

                for (String defaultImport : defaultImportClasses) {
                    Def def = Defs.importDef(Position.noPos(), tree, defaultImport);
                    def.addModifier(Modifiers.modSynthetic(def));
                    tree.addChild(def);
                    context.onFileImportDef(tree.getPos(), def);
                }

                children.forEach(tree::addChild);

                return tree;
            }
        }, null);

        return TaskResult.SUCCESS;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
