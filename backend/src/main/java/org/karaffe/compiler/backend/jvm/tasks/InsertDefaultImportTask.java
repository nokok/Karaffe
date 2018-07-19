package org.karaffe.compiler.backend.jvm.tasks;

import net.nokok.azm.Type;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.rule.TypeNameRewriteRule;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;

import java.io.Reader;
import java.net.URI;
import java.nio.Buffer;
import java.nio.channels.Channel;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertDefaultImportTask extends AbstractTask implements NoDescriptionTask, BackendTask {

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
    public boolean changed() {
        return true;
    }

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        // FQCNで書かないと後々クラスがロードできなくて死ぬことがある
        instructions.add(1, new TypeNameRewriteRule("Object", getImportAfterName(java.lang.Object.class)));
        instructions.add(1, new TypeNameRewriteRule("String", getImportAfterName(java.lang.String.class)));
        instructions.add(1, new TypeNameRewriteRule("System", getImportAfterName(java.lang.System.class)));
        instructions.add(1, new TypeNameRewriteRule("Integer", getImportAfterName(java.lang.Integer.class)));
        instructions.add(1, new TypeNameRewriteRule("Matcher", getImportAfterName(java.util.regex.Matcher.class)));
        instructions.add(1, new TypeNameRewriteRule("System", getImportAfterName(java.util.regex.Pattern.class)));
        instructions.add(1, new TypeNameRewriteRule("Array[String]", getImportAfterName(String[].class)));
        return TaskResult.SUCCESSFUL;
    }

    private String getImportAfterName(Class<?> clazz) {
        return Type.getInternalName(clazz);
    }
}
