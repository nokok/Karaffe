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
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;
import org.karaffe.compiler.base.util.Platform;

import java.util.Collections;
import java.util.LinkedHashMap;
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

    static class MapVisitor extends TreeVisitorAdapter<Map<String, Object>, Void> {

        @Override
        public Map<String, Object> visit(Modifier modifier, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mod", modifier.getType().name());
            return map;
        }

        @Override
        public Map<String, Object> visitCompileUnit(Tree.CompilationUnit tree, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", tree.getKind());
            map.put("pos", tree.getPos());
            map.put("body", tree.getChildren().stream().map(t -> t.accept(this, aVoid)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitTemplate(Tree.Template template, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", template.getKind());
            map.put("name", template.getName());
            map.put("pos", template.getPos());
            map.put("superClass", template.getSuperClass().accept(this, null));
            map.put("interfaces", template.getInterfaces().stream().map(t -> t.accept(this, null)).collect(toList()));
            map.put("body", template.getChildren().stream().map(t -> t.accept(this, null)).collect(toList()));
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
            map.put("methodName", apply.getChildren().get(1).accept(this, null));
            map.put("args", apply.getChildren().stream().skip(2).map(t -> t.accept(this, null)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitBlock(Block block, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", block.getKind());
            map.put("body", block.getChildren().stream().map(t -> t.accept(this, null)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitSelect(Select select, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", select.getKind());
            map.put("body", select.getChildren().stream().map(t -> t.accept(this, null)).collect(toList()));
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
            map.put("body", tuple.getChildren().stream().map(t -> t.accept(this, null)).collect(toList()));
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
        public Map<String, Object> visitLetDef(Def simpleDef, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", simpleDef.getDefKind());
            map.put("name", simpleDef.getName().accept(this, null));
            map.put("type", simpleDef.asType().accept(this, null));
            map.put("expr", simpleDef.getChildren().stream().map(t -> t.accept(this, null)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitAssignmentDef(Def simpleDef, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", simpleDef.getDefKind());
            map.put("target", simpleDef.getName().accept(this, null));
            map.put("expr", simpleDef.getChildren().stream().map(i -> i.accept(this, null)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitClassDef(Def def, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", def.getDefKind());
            map.put("mod", def.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
            map.put("name", def.getName().accept(this, null));
            map.put("body", def.getChildren().stream().map(m -> m.accept(this, null)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitImportDef(Def def, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("importPath", def.getChildren().get(0).accept(this, null));
            return map;
        }

        @Override
        public Map<String, Object> visitMethodDef(Def def, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mods", def.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
            map.put("methodName", def.getName());
            map.put("returnType", def.asType().accept(this, null));
            map.put("params", def.getChildren().get(0).accept(this, null));
            map.put("body", def.getChildren().stream().skip(1).map(b -> b.accept(this, null)).collect(toList()));
            return map;
        }

        @Override
        public Map<String, Object> visitPackageDef(Def def, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("mods", def.getModifiers().stream().map(m -> m.accept(this, null)).collect(toList()));
            map.put("packageName", def.getName());
            return map;
        }

        @Override
        public Map<String, Object> visitNoneName(Name name, Void aVoid) {
            return Collections.singletonMap("name", name);
        }

        @Override
        public Map<String, Object> visitFQCN(Name name, Void aVoid) {
            return Collections.singletonMap("name", name);
        }

        @Override
        public Map<String, Object> visitPackageName(Name name, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", name.getType());
            map.put("name", name.toString());
            return map;
        }

        @Override
        public Map<String, Object> visitThisName(Name name, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", name.getType());
            map.put("name", name.toString());
            return map;
        }

        @Override
        public Map<String, Object> visitVarName(Name name, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", name.getType());
            map.put("name", name.toString());
            return map;
        }

        @Override
        public Map<String, Object> visitTypeName(Name name, Void aVoid) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("kind", name.getType());
            map.put("name", name.toString());
            return map;
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
        public Map<String, Object> visitRefType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitArrayType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveIntType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveCharType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveByteType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitVoidType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveBooleanType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveLongType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveFloatType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveShortType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitPrimitiveDoubleType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }

        @Override
        public Map<String, Object> visitNoType(Type type, Void aVoid) {
            return Collections.singletonMap("type", type);
        }
    }
}
