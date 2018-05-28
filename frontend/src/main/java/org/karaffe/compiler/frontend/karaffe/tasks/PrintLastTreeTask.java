package org.karaffe.compiler.frontend.karaffe.tasks;

import net.arnx.jsonic.JSON;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.expr.Expr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;
import org.karaffe.compiler.base.util.Platform;

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
        Map<String, Object> json = new LinkedHashMap<>();
        json.put("state", context.getState());
        json.put("options", context.getCmdLineOptions());
        json.put("hasInvalidCmdLineArg", context.hasInvalidCmdLineArg());
        json.put("compilationUnit", context.getCompilationUnit().accept(new MapVisitor(), null));

        Platform.stdOut(JSON.encode(json, true));
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

    static class MapVisitor implements TreeVisitor<Object, Void> {

        @Override
        public Object visit(Def def, Void aVoid) {
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
        public Object visit(Expr binaryExpr, Void aVoid) {
            return null;
        }

        @Override
        public Object visit(Name simpleName, Void aVoid) {
            return simpleName;
        }

        @Override
        public Object visit(Modifier modifier, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mod", modifier.getType().name());
            return map;
        }

        @Override
        public Object visit(Type type, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("simpleName", type.getName());
            return map;
        }

        @Override
        public Object visitCompileUnit(Tree.CompilationUnit tree, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", tree.getKind());
            map.put("pos", tree.getPos());
            map.put("body", tree.getChildren().stream().map(t -> t.accept(this, aVoid)).collect(Collectors.toList()));
            return map;
        }

        @Override
        public Object visitTemplate(Tree.Template template, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", template.getKind());
            map.put("name", template.getName());
            map.put("pos", template.getPos());
            map.put("body", template.getChildren().stream().map(t -> t.accept(this, null)).collect(Collectors.toList()));
            return map;
        }
    }
}
