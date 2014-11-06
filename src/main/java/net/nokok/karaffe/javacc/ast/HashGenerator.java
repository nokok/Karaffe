package net.nokok.karaffe.javacc.ast;

import java.nio.charset.Charset;

public class HashGenerator {

    /**
     * Javaが識別子として認識可能なハッシュを生成します。
     *
     * @param obj
     *
     * @return
     */
    public static String javaIdentifier(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("Identifier_");
        for (byte b : obj.toString().getBytes(Charset.forName("UTF-8"))) {
            sb.append(b);
        }
        return sb.toString();
    }
}
