package org.karaffe.compiler.base.util;

import net.arnx.jsonic.JSON;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.pos.Position;

import java.util.LinkedHashMap;
import java.util.Map;

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
                return super.preformat(context, value);
            }
        };
        jsonMapper.setPrettyPrint(true);
        jsonMapper.setIndentText("  ");
        return jsonMapper.format(map);
    }


}
