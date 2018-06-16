package org.karaffe.compiler.frontend.karaffe.tasks;

import net.arnx.jsonic.JSON;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.util.Platform;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

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
        Platform.stdOut(jsonMapper.format(map));
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean isFinally(CompilerContext context) {
        return true;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCmdLineOptions().showLastTree && context.getCompilationUnit() != null;
    }

    static class MapVisitor implements TreeVisitor<Map<String, Object>, Void> {

        private Map<String, Object> visitTree(Tree tree) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mods", tree.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
            map.put("pos", tree.getPos());
            map.put("kind", tree.getKind());
            map.put("name", tree.getName().accept(this, null));
            map.put("type", tree.getTypeName().accept(this, null));
            map.put("body", visitChildren(tree));
            return map;
        }

        private List<Map<String, Object>> visitChildren(Tree tree) {
            return tree.getChildren().stream().map(t -> t.accept(this, null)).collect(toList());
        }

        @Override
        public Map<String, Object> visitCompileUnit(Tree.CompilationUnit tree, Void aVoid) {
            return visitTree(tree);
        }

        @Override
        public Map<String, Object> visitTemplate(Tree.Template template, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", template.getKind());
            map.put("name", template.getName());
            map.put("pos", template.getPos());
            map.put("superClass", template.getSuperClass().accept(this, null));
            map.put("interfaces", template.getInterfaces().stream().map(t -> t.accept(this, null)).collect(toList()));
            map.put("body", visitChildren(template));
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
            map.put("target", apply.getChildren().get(0).accept(this, null));
            map.put("methodName", apply.getName().accept(this, null));
            map.put("args", apply.getChildren().stream().skip(1).map(t -> t.accept(this, null)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitBlock(Block block, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", block.getKind());
            map.put("body", visitChildren(block));
            return map;
        }

        @Override
        public Map<String, Object> visitSelect(Select select, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", select.getKind());
            map.put("body", visitChildren(select));
            return map;
        }

        @Override
        public Map<String, Object> visitTuple(Tuple tuple, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", tuple.getKind());
            map.put("body", visitChildren(tuple));
            return map;
        }

        @Override
        public Map<String, Object> visitIfExpr(IfExpr ifExpr, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", ifExpr.getKind());
            map.put("cond", ifExpr.getChildren().get(0).accept(this, null));
            map.put("then", ifExpr.getChildren().get(1).accept(this, null));
            map.put("else", ifExpr.getChildren().size() == 3 ? ifExpr.getChildren().get(2).accept(this, null) : "");
            return map;
        }

        @Override
        public Map<String, Object> visitWhileExpr(WhileExpr whileExpr, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", whileExpr.getKind());
            map.put("cond", whileExpr.getChildren().get(0).accept(this, null));
            map.put("body", whileExpr.getChildren().get(1).accept(this, null));
            return map;
        }

        @Override
        public Map<String, Object> visitEmpty(EmptyTree emptyTree, Void aVoid) {
            return Collections.singletonMap("empty", "");
        }

        @Override
        public Map<String, Object> visitCast(Cast cast, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", cast.getKind());
            map.put("expr", cast.getChildren().get(0).accept(this, null));
            map.put("target", cast.getChildren().get(1).accept(this, null));
            return map;
        }

        @Override
        public Map<String, Object> visitBinding(Binding binding, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", binding.getKind());
            map.put("name", binding.getName().accept(this, null));
            map.put("type", binding.getTypeName().accept(this, null));
            return map;
        }

        @Override
        public Map<String, Object> visitLetDef(LetDef simpleDef, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", simpleDef.getDefKind());
            map.put("name", simpleDef.getName());
            map.put("type", simpleDef.getTypeName().accept(this, null));
            map.put("expr", visitChildren(simpleDef));
            return map;
        }

        @Override
        public Map<String, Object> visitAssignmentDef(AssignmentDef simpleDef, Void aVoid) {
            return visitTree(simpleDef);
        }

        @Override
        public Map<String, Object> visitClassDef(ClassDef def, Void aVoid) {
            return visitTree(def);
        }

        @Override
        public Map<String, Object> visitSimpleImportDef(SimpleImport tree, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mods", tree.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
            map.put("pos", tree.getPos());
            map.put("kind", tree.getDefKind());
            map.put("importPath", tree.getName().accept(this, null));
            map.put("body", visitChildren(tree));
            return map;
        }

        @Override
        public Map<String, Object> visitOnDemandImportDef(OnDemandImport tree, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mods", tree.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
            map.put("pos", tree.getPos());
            map.put("kind", tree.getDefKind());
            map.put("importPackage", tree.getName().accept(this, null));
            map.put("body", visitChildren(tree));
            return map;
        }

        @Override
        public Map<String, Object> visitMethodDef(MethodDef def, Void aVoid) {
            return visitTree(def);
        }

        @Override
        public Map<String, Object> visitPackageDef(PackageDef def, Void aVoid) {
            return visitTree(def);
        }

        @Override
        public Map<String, Object> visitStaticMod(Modifier modifier, Void aVoid) {
            return Collections.singletonMap("mod", modifier.getType());
        }

        @Override
        public Map<String, Object> visitPublicMod(Modifier modifier, Void aVoid) {
            return Collections.singletonMap("mod", modifier.getType());
        }

        @Override
        public Map<String, Object> visitSyntheticMod(Modifier modifier, Void aVoid) {
            return Collections.singletonMap("mod", modifier.getType());
        }

    }
}
