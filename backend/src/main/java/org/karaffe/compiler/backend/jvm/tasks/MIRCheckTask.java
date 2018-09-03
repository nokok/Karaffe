package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.backend.jvm.util.BackendException;
import org.karaffe.compiler.backend.jvm.util.JavaIdentifier;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.ir.Module;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.report.Report;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;

public class MIRCheckTask extends AbstractTask implements MIRTask, NoDescriptionTask, ReadOnlyTask {

    @Override
    public TaskResult run(IR ir, CompilerContext context) {
        if (!ir.hasModule()) {
            context.addReport(Report.createError("No Module found.", Position.noPos(), ""));
            return TaskResult.FAILED;
        }
        for (Module module : ir.getModules()) {
            try {
                new JavaIdentifier(module.getModuleName());
            } catch (BackendException e) {
                context.addReport(e.getReport());
            }
        }
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "backend-jvm-mircheck";
    }

}
