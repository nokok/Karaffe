/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ASMUtil {

    public static final String OBJECT_CLASS = "java/lang/Object";
    public static final String DEFAULT_CONSTRUCTOR_NAME = "<init>";
    public static final String RETURN_VOID = "()V";

    public static ClassWriter createPublicClass(String className, String baseName) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, className, null, baseName, null);
        return classWriter;
    }
//
//    public static String nodeToTypeNameString(SimpleNode interfaceDcl) {
//        try {
//            IdentifierCollector collector = new IdentifierCollector();
//            interfaceDcl.childrenAccept(collector, null);
//            return createIdentifier((ASTIdentifier) interfaceDcl.jjtGetChild(0), collector.getParameters());
//        } catch (KaraffeParserException e) {
//            throw new IllegalArgumentException();
//        }
//    }
//
//    /**
//     id[Param1 Param2]形式の識別子を文字列id_Param1_Param2の形に変換します
//     @param identifier
//     @param parameters
//     @return
//     */
//    public static String createIdentifier(ASTIdentifier identifier, List<ASTIdentifier> parameters) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(identifier.jjtGetValue().toString());
//        parameters
//                .stream()
//                .map(i -> i.jjtGetValue().toString())
//                .forEach(s -> sb.append("_").append(s));
//        return sb.toString();
//    }
//
//    /**
//     このクラスは、例えば
//     {@code type Foo[A B C] < Bar[Baz[A] B C]}の宣言から
//     {@code Foo[A B C]},{@code Bar[Baz[A] B C]},{@code Baz[A]}を抜き出します。
//     */
//    private static class IdentifierCollector extends KaraffeParserDefaultVisitor {
//
//        private final List<List<ASTIdentifier>> types = new ArrayList<>();
//        private List<ASTIdentifier> current = new ArrayList<>();
//
//        @Override
//        public Object visit(ASTIdentifier node, Object data) throws KaraffeParserException {
//            current.add(node);
//            return data;
//        }
//
//        public List<ASTIdentifier> getParameters() {
//            List<ASTIdentifier> r = new ArrayList<>();
//            for (List<ASTIdentifier> type : types) {
//                for (ASTIdentifier t : type) {
//                    r.add(t);
//                }
//            }
//            return r;
//        }
//    }
}
