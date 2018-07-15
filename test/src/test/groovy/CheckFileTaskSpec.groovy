import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.base.util.Platform
import org.karaffe.compiler.base.util.config.Options
import org.karaffe.compiler.frontend.karaffe.tasks.options.CheckFileTask
import spock.lang.Specification

import java.nio.file.Files

class CheckFileTaskSpec extends Specification {

    def "ファイルが存在しない場合はNON_RECOVERABLEを返す"() {
        setup:
        def task = new CheckFileTask()
        def opt = new Options()
        opt.arguments = ["foo.krf"]

        expect:
        def result = task.run(opt, new CompilerContextImpl())
        result == TaskResult.FAILED
    }

    def "ファイルが存在するが読み込めない場合はNON_RECOVERABLEを返す"() {
        setup:
        def task = new CheckFileTask()
        def opt = new Options()
        def file = File.createTempFile("fffff", ".krf")
        file.setReadable(false)
        file.setWritable(false)
        opt.arguments = [file.getAbsolutePath()]

        expect:
        if (Platform.isWindows()) {
            true
        } else {
            !Files.isReadable(file.toPath())
            def result = task.run(opt, new CompilerContextImpl())
            result == TaskResult.FAILED
        }

        cleanup:
        file.setReadable(true)
        file.setWritable(true)
        file.delete()
    }
}
