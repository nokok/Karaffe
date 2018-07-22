package org.karaffe.compiler.base.util;

import net.arnx.jsonic.JSON;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Operator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class CompilerContextJsonConverter {
    public static String toJson(CompilerContext context) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("state", context.getState());
        map.put("options", context.getCmdLineOptions());
        map.put("hasInvalidCmdLineArg", context.hasInvalidCmdLineArg());
        map.put("compilationUnit", context.getCompilationUnit().accept(new MapVisitor(), null));

        JSON jsonMapper = new JSON() {

            @Override
            protected Object preformat(Context context, Object value) throws Exception {
                if (value instanceof Position) {
                    return value.toString();
                }
                if (value instanceof Operator) {
                    return ((Operator) value).getOperatorKind().toString();
                }
                if (value instanceof Atom) {
                    return ((Atom) value).getValue();
                }
                return super.preformat(context, value);
            }
        };
        jsonMapper.setPrettyPrint(true);
        jsonMapper.setIndentText("  ");
        return jsonMapper.format(map);
    }


}
