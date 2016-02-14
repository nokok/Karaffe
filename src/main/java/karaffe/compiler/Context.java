package karaffe.compiler;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.objectweb.asm.Type;

public enum Context {

    INSTANCE;

    private final List<String> source;

    private final ClassResolver resolver;
    private PackageDef packageDef = null;
    private final List<ImportDef> importDefs;
    private final List<ClassDef> classDefs;
    private final List<MethodDef> methodDefs;
    private final List<FieldDef> fieldDefs;
    private final List<LocalVarDef> localVarDefs;
    private final List<Parameter> parameters;
    private final List<Identifier> identifiers;
    private final Map<MethodDef, List<LocalVarDef>> localVarMap;
    private final Map<String, Object> pathList;

    private Context() {
        this.source = new ArrayList<>();
        this.resolver = new ClassResolver();
        this.importDefs = new ArrayList<>();
        this.classDefs = new ArrayList<>(1);
        this.methodDefs = new ArrayList<>();
        this.fieldDefs = new ArrayList<>();
        this.localVarDefs = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.identifiers = new ArrayList<>();
        this.localVarMap = new LinkedHashMap<>();
        this.pathList = new LinkedHashMap<>();
    }

    public void setPackageDef(PackageDef def) {
        if ( packageDef != null ) {
            throw new IllegalStateException("duplicate packagedef or clear missing");
        }
        this.packageDef = Objects.requireNonNull(def);
    public void add(File file) {
        try {
            this.source.clear();
            this.source.addAll(Files.readAllLines(file.toPath()));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public void add(ImportDef importDef) {
        this.importDefs.add(Objects.requireNonNull(importDef));
    }

    public void add(ClassDef classDef) {
        this.classDefs.add(Objects.requireNonNull(classDef));
        this.methodDefs.stream().forEach(m -> m.setParent(classDef));
    }

    public void add(MethodDef methodDef) {
        this.methodDefs.add(Objects.requireNonNull(methodDef));
        this.localVarDefs.forEach(l -> l.setParent(methodDef));
        this.parameters.forEach(l -> l.setParent(methodDef));
        this.identifiers.forEach(l -> l.setParent(methodDef));
    }

    public void add(LocalVarDef localVarDef) {
        this.localVarDefs.add(Objects.requireNonNull(localVarDef));
    }

    public void add(FieldDef fieldDef) {
        this.fieldDefs.add(Objects.requireNonNull(fieldDef));
    }

    public void add(Parameter param) {
        Position position = param.position();
        //この中でaddされるので this.localVarDefs.add(varDef);は不要
        LocalVarDef varDef = new LocalVarDef(new Identifier(param.id(), position), param.getTypeElement(), param.getExpr());
    }

    public void add(Identifier identifier) {
        this.identifiers.add(Objects.requireNonNull(identifier));
    }

    public PackageDef getPackageDef() {
        return packageDef;
    }

    public List<ImportDef> getImportDefs() {
        return importDefs;
    }

    public List<ClassDef> getClassDefs() {
        return classDefs;
    }

    public List<LocalVarDef> getLocalVarDefs() {
        return localVarDefs;
    }

    public int findLocalVarIndex(String path) {
        int lastIndex = path.lastIndexOf(".");
        CharSequence prefix = path.subSequence(0, lastIndex);
        List<String> localPaths = new ArrayList<>();
        for ( Map.Entry<String, Object> entry : pathList.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if ( key.startsWith(prefix.toString()) && value instanceof LocalVarDef ) {
                localPaths.add(key);
            }
            if ( key.startsWith(prefix.toString()) && value instanceof Parameter ) {
                localPaths.add(key);
            }
        }
        Collections.reverse(localPaths);
        int index = localPaths.indexOf(path);
        if ( index == -1 ) {
            Identifier sym = identifiers.stream().filter(id -> id.getPath().equals(path)).findAny().get();
            System.err.println("symbol not found:" + sym.id() + " at " + sym.getLine() + ":" + sym.getColumn());
            throw new RuntimeException();
        }
        return index;
    }

    public void clear() {
        packageDef = null;
        importDefs.clear();
        classDefs.clear();
        methodDefs.clear();
        fieldDefs.clear();
        localVarDefs.clear();
        localVarMap.clear();
        parameters.clear();
    }

    public void updateLocalVarIndexes() {
        localVarMap.clear();
        for ( LocalVarDef def : localVarDefs ) {
            MethodDef parent = def.getParent();
            int index;
            if ( parent == null ) {
                throw new RuntimeException("null parent");
            }
            List<LocalVarDef> list;
            if ( localVarMap.containsKey(parent) ) {
                list = localVarMap.get(parent);
            } else {
                list = new ArrayList<>();
            }
            list.add(def);
            index = list.indexOf(def);
            def.setIndex(index);
            localVarMap.put(parent, list);
        }
    }

    public Optional<String> resolveInternalNameByIdent(String ident) {
        return resolver.resolveInternalNameByIdent(ident);
    }

    public Optional<String> resolveInternalNameByFullName(List<String> idents) {
        return resolver.resolveInternalNameByFullName(idents);
    }

    public Optional<Type> resolveTypeByIdent(String ident) {
        return resolver.resolveTypeByIdent(ident);
    }

    public Optional<Type> resolveTypeByFullName(List<String> idents) {
        return resolver.resolveTypeByFullName(idents);
    }

    public Optional<Class<?>> resolveClassByIdent(String ident) {
        return resolver.resolveClassByIdent(ident);
    }

    public Optional<Class<?>> resolveClassByFullName(List<String> idents) {
        return resolver.resolveClassByFullName(idents);
    }

    void generatePaths() {
        classDefs
            .stream()
            .map(classdef -> {
                classdef.setPath(packageDef.toPath("."));
                return classdef;
            })
            .forEach(classdef -> {
                if ( pathList.containsKey(classdef.getPath()) ) {
                    throw new RuntimeException("duplicate");
                }
                pathList.put(classdef.getPath(), classdef);
            });
        methodDefs
            .stream()
            .map(methoddef -> {
                methoddef.setPath(methoddef.getParent().getPath());
                return methoddef;
            })
            .forEach(methoddef -> {
                if ( pathList.containsKey(methoddef.getPath()) ) {
                    throw new RuntimeException("duplicate");
                }
                pathList.put(methoddef.getPath(), methoddef);
            });
        localVarDefs
            .stream()
            .map(localVardef -> {
                localVardef.setPath(localVardef.getParent().getPath());
                return localVardef;
            })
            .forEach(localVarDef -> {
                if ( pathList.containsKey(localVarDef.getPath()) ) {
                    throw new RuntimeException("duplicate");
                }
                pathList.put(localVarDef.getPath(), localVarDef);
            });
        AtomicInteger index = new AtomicInteger(0);
        identifiers.stream()
            .map(identifier -> {
                identifier.setPath(identifier.getParent().getPath());
                return identifier;
            })
            .forEach(id -> {
                if ( pathList.containsKey(id.getPath()) ) {
                    pathList.put(id + "_$:" + index.incrementAndGet(), id);
                    return;
                }
                pathList.put(id + "_$:" + index, id);
            });
    }

    public Map<String, Object> getPathList() {
        return Collections.unmodifiableMap(pathList);
    }

    void reportTypeError(Position e1Pos) {
        reportTypeError(e1Pos, Collections.emptyList(), Collections.emptyList());
    }

    public void reportSyntaxError(int line, int column) {
        System.out.println("Syntax Error at " + (line + 1) + ":" + column);
        throw new RuntimeException();
    }

}
