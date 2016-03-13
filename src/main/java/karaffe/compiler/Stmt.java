package karaffe.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Stmt {

    static NodeGeneratable Goto(NodeGeneratable l) {
        return new GotoNode(l);
    }

    static FieldDef fieldDef(Identifier id, TypeElement type, Expression e) {
        return new FieldDef(id, type, e);
    }

    static FieldDef fieldDef(List<Annotation> a, List<Modifier> m, Identifier id, TypeElement type, Expression e) {
        return new FieldDef(a, m, id, type, e);
    }

    static FieldDef fieldDef(List<Annotation> a, List<Modifier> m, List<FieldDef> f) {
        return new FieldDef(f.get(0).id(), f.get(0).type(), f.get(0).expr());
    }

    static FieldDefBlock fieldDefBlock(List<Annotation> a, List<Modifier> m, List<FieldDef> l) {
        return new FieldDefBlock(a, m, l);
    }

    static ImportDef importDef(List<Identifier> il, int illeft, int ilright) {
        return new ImportDef(il, illeft, ilright);
    }

    static ClassDef innerClassDef(List<Annotation> a, List<Modifier> m, Identifier i, List<Statement> l) {
        return new ClassDef(a, m, i, l);
    }

    static ClassDef classDef(List<Annotation> a, List<Modifier> m, Identifier i, List<Statement> l) {
        return new ClassDef(a, m, i, l);
    }

    static NodeGeneratable label(Identifier i) {
        return new LabelNodeAST(i);
    }

    static LocalVarDef localVarDef(Identifier id, TypeElement type, Expression e) {
        return new LocalVarDef(id, type, e);
    }

    static MethodDef mainDef(List<NodeGeneratable<?>> b) {
        List<Modifier> modifiers = new ArrayList<>();
        modifiers.add(Modifiers.PUBLIC);
        modifiers.add(Modifiers.STATIC);
        Parameter parameter = new Parameter(new Identifier("args", -1, -1), new TypeElement(new Identifier("Array", -1, -1), Arrays.asList(new Identifier("String", -1, -1))));
        List<Parameter> param = new ArrayList<>(1);
        param.add(parameter);
        return new MethodDef(Collections.emptyList(), modifiers, new Identifier("main", -1, -1), param, TypeElement.none(), b);
    }

    static MethodDef methodDef(List<Annotation> a, List<Modifier> m, Identifier id, List<Parameter> p, TypeElement retType, List<NodeGeneratable<?>> b) {
        return new MethodDef(a, m, id, p, retType, b);
    }

    static PackageDef packageDef(List<Identifier> l, int lleft, int lright) {
        return new PackageDef(l, lleft, lright);
    }

    static TypeElement typeName(Identifier id, List<Identifier> targ) {
        return new TypeElement(id, targ);
    }

    static TypeElement unionType(TypeElement t1, TypeElement t2) {
        return new UnionTypeElement(t1, t2);
    }

}
