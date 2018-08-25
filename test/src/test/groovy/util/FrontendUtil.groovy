package util

import groovy.json.JsonSlurper
import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions
import org.karaffe.compiler.base.task.Task
import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.base.util.CompilerContextJsonConverter
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.base.FrontendType
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend

class FrontendUtil {
    public static DeprecatedInstructions parseAndGenerateInstructions(String source) {
        return parseAndGenerateCompilerContext(source).getInstructions()
    }

    public static CompilerContext parseAndGenerateCompilerContext(String source) {
        CompilerContext context = new CompilerContextImpl();
        context.setFrontendType(FrontendType.KARAFFE);
        Task frontend = KaraffeCompilerFrontend.getFrontend(context)
        context.addSourceFile(SourceFile.fromLiteral(source))
        def result = frontend.run(context)
        if (result != TaskResult.SUCCESSFUL) {
            throw new RuntimeException()
        }
        return context
    }

    public static Object parseAndGenerateCompilerContextJSON(String source) {
        def jsonSlurper = new JsonSlurper()
        def context = parseAndGenerateCompilerContext(source)
        return jsonSlurper.parseText(CompilerContextJsonConverter.toJson(context))
    }
}
