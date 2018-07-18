package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.CompilationUnitTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.def.Defs;
import org.karaffe.compiler.base.tree.modifier.Modifiers;

import java.io.Reader;
import java.net.URI;
import java.nio.Buffer;
import java.nio.channels.Channel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertDefaultImportTask extends AbstractTask implements NoDescriptionTask, CompilationUnitTask {

    private static final List<String> defaultImportPackages;
    private static final List<String> defaultImportClasses;

    static {
        // load classes
        List<Class<?>> classes = Arrays.asList(
                Integer.class,
                Reader.class,
                URI.class,
                List.class,
                Stream.class,
                Function.class,
                ForkJoinPool.class,
                Buffer.class,
                Path.class,
                Channel.class
        );


        defaultImportPackages = classes.stream().map(Class::getPackage).map(Package::getName).collect(Collectors.toList());

        defaultImportClasses = Stream.of(
                Object.class,
                String.class,
                System.class,
                Integer.class,
                java.util.regex.Matcher.class,
                java.util.regex.Pattern.class
        ).map(Class::getCanonicalName).map(p -> "L" + p.replaceAll("\\.", "/") + ";").collect(Collectors.toList());
    }

    @Override
    public String name() {
        return "backend-jvm-defaultimport";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        compilationUnit.accept(new DefaultVisitor<Void>() {
            @Override
            public Tree visitCompileUnit(Tree.CompilationUnit tree, Void aVoid) {
                super.visitCompileUnit(tree, aVoid);

                List<Tree> children = tree.getChildren();
                tree.setChildren(new ArrayList<>());

                for (String defaultImport : defaultImportPackages) {
                    Def def = Defs.onDemandImportDef(Position.noPos(), tree, defaultImport);
                    def.addModifier(Modifiers.modSynthetic(def));
                    tree.addChild(def);
                    context.onFileImportDef(tree.getPos(), def);
                }

                for (String defaultImport : defaultImportClasses) {
                    Def def = Defs.importDef(Position.noPos(), tree, defaultImport);
                    def.addModifier(Modifiers.modSynthetic(def));
                    tree.addChild(def);
                    context.onFileImportDef(tree.getPos(), def);
                }

                children.forEach(tree::addChild);

                return tree;
            }
        }, null);

        return TaskResult.SUCCESS;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
