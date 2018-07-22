import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.pos.Position
import org.karaffe.compiler.base.tree.Trees
import org.karaffe.compiler.base.tree.def.Defs
import org.karaffe.compiler.base.tree.def.MethodDef
import org.karaffe.compiler.base.tree.expr.Exprs
import org.karaffe.compiler.base.tree.term.Terms
import org.karaffe.compiler.frontend.karaffe.subject.MethodDefSubject
import spock.lang.Specification

import java.util.function.Consumer

class SubjectSpec extends Specification {
    def "MethodDefSubject"() {
        setup:
        def subject = MethodDefSubject.getSubject()
        def context = new CompilerContextImpl()
        def compilationUnit = Trees.compilationUnit()
        compilationUnit.addChild(
                Defs.methodDef(
                        Position.noPos(),
                        compilationUnit,
                        "A",
                        Terms.typeName("void"),
                        Exprs.tuple())
        )
        compilationUnit.addChild(Defs.mainMethodDef(Position.noPos(), compilationUnit))
        def classDef = Defs.classDef(Position.noPos(), "A")
        classDef.addChild(Defs.methodDef(
                Position.noPos(),
                compilationUnit,
                "A",
                Terms.typeName("void"),
                Exprs.tuple())
        )
        compilationUnit.addChild(classDef)
        context.compilationUnit = compilationUnit

        def counter = 0
        subject.onMethodDef((Consumer<MethodDef>) { methodDef -> counter++ })
        context.compilationUnit.accept(subject.asVisitor())

        expect:
        counter == 3
    }
}
