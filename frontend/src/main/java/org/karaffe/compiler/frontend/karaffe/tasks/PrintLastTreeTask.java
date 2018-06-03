package org.karaffe.compiler.frontend.karaffe.tasks;

import net.arnx.jsonic.JSON;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;
import org.karaffe.compiler.base.util.Platform;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintLastTreeTask extends AbstractReadOnlyTask implements NoDescriptionTask {
    @Override
    public String name() {
        return "print lasttree";
    }

    @Override
    public TaskResult run(CompilerContext context) {
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
        Platform.stdOut(jsonMapper.format(map));
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean isFinally(CompilerContext context) {
        return true;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCmdLineOptions().showLastTree;
    }

    static class MapVisitor extends TreeVisitorAdapter<Map<String, Object>, Void> {

        @Override
        public Map<String, Object> visit(Def def, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", def.getDefKind());
            map.put("mods", def.getModifiers().stream().map(m -> m.accept(this, null)).collect(Collectors.toList()));
            map.put("type", def.asType().accept(this, null));
            map.put("name", def.getName().accept(this, null));
            map.put("pos", def.getPos());
            map.put("body", def.getBody().stream().map(d -> d.accept(this, null)).collect(Collectors.toList()));
            return map;
        }

        @Override
        public Map<String, Object> visit(Name simpleName, Void aVoid) {
            return Collections.singletonMap("name", simpleName);
        }

        @Override
        public Map<String, Object> visit(Modifier modifier, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mod", modifier.getType().name());
            return map;
        }

        @Override
        public Map<String, Object> visit(Type type, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("simpleName", type.getName());
            return map;
        }

        @Override
        public Map<String, Object> visitCompileUnit(Tree.CompilationUnit tree, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", tree.getKind());
            map.put("pos", tree.getPos());
            map.put("body", tree.getChildren().stream().map(t -> t.accept(this, aVoid)).collect(Collectors.toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitTemplate(Tree.Template template, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", template.getKind());
            map.put("name", template.getName());
            map.put("pos", template.getPos());
            map.put("superClass", template.getSuperClass().accept(this, null));
            map.put("interfaces", template.getInterfaces().stream().map(t -> t.accept(this, null)).collect(Collectors.toList()));
            map.put("body", template.getChildren().stream().map(t -> t.accept(this, null)).collect(Collectors.toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitAtom(Atom atom, Void aVoid) {
            return Collections.singletonMap("value", atom.getValue());
        }

        @Override
        public Map<String, Object> visitApply(Apply apply, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", apply.getKind());
            map.put("body", apply.getChildren().stream().map(t -> t.accept(this, null)).collect(Collectors.toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitBlock(Block block, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", block.getKind());
            map.put("body", block.getChildren().stream().map(t -> t.accept(this, null)).collect(Collectors.toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitSelect(Select select, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", select.getKind());
            map.put("body", select.getChildren().stream().map(t -> t.accept(this, null)).collect(Collectors.toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitOperator(Operator operator, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("op", operator.getName());
            return map;
        }

        @Override
        public Map<String, Object> visitTuple(Tuple tuple, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", tuple.getKind());
            map.put("body", tuple.getChildren().stream().map(t -> t.accept(this, null)).collect(Collectors.toList()));
            return map;
        }
    }
}
