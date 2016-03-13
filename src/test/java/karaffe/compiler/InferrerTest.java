package karaffe.compiler;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class InferrerTest {

    @Before
    public void setUp() {
        Context.INSTANCE.clear();
    }

    @Test
    public void testInt() {
        TypeElement type = Context.INSTANCE.getType(new IntLiteral("1", 1, 1));
        test(type, "karaffe", "core", "Int");
    }

    @Test
    public void testTrue() {
        TypeElement type = Context.INSTANCE.getType(new TrueLiteral());
        test(type, "java", "lang", "Boolean");
    }

    @Test
    public void testFalse() {
        TypeElement type = Context.INSTANCE.getType(new FalseLiteral());
        test(type, "java", "lang", "Boolean");
    }

    @Test
    public void testString() {
        TypeElement type = Context.INSTANCE.getType(new StringLiteral("hoge"));
        test(type, "java", "lang", "String");
    }

    @Test
    public void testAddExpr() {
        TypeElement type = Context.INSTANCE.getType(new AddExpr(new IntLiteral("1", 1, 1), new IntLiteral("2", 1, 3), 1, 1, 1, 3));
        test(type, "karaffe", "core", "Int");
    }

    @Test
    public void testAddExpr2() {
        LocalVarDef x = new LocalVarDef(new Identifier("x"), new TypeElement(new Identifier("Int"), new ArrayList<>()), new IntLiteral("1", 1, 5));
        LocalVarDef y = new LocalVarDef(new Identifier("y"), new TypeElement(new Identifier("Int"), new ArrayList<>()), new IntLiteral("1", 1, 5));
        List<NodeGeneratable<?>> defs = new ArrayList<>();
        defs.add(x);
        defs.add(y);

        MethodDef methodDef = new MethodDef(
            new ArrayList<>(0),
            new ArrayList<>(0),
            new Identifier("doSomething"),
            new ArrayList<>(0),
            new TypeElement(new Identifier("hoge"), new ArrayList<>()),
            defs
        );

        List<Statement> stmt = new ArrayList<>();
        stmt.add(methodDef);

        ClassDef classDef = new ClassDef(new ArrayList<>(), new ArrayList<>(), new Identifier("Cls"), stmt);

        PackageDef packageDef = PackageDef.none();

        Context.INSTANCE.beforeGenClassNode();
        TypeElement type = Context.INSTANCE.getType(new AddExpr(x.getIdentifier(), y.getIdentifier(), 1, 1, 1, 3));
        test(type, "karaffe", "core", "Int");
    }

    private void test(TypeElement type, String... clazz) {
        if ( !type.isResolved() ) {
            type.doResolve();
        }
        List<Identifier> args = type.resolvedType();
        assertThat(args.size(), is(clazz.length));
        for ( int i = 0; i < args.size(); i++ ) {
            System.out.println("arg  : " + args.get(i).id());
            System.out.println("clazz: " + clazz[i]);
            assertTrue(args.get(i).softEquals(new Identifier(clazz[i])));
        }
    }

}
