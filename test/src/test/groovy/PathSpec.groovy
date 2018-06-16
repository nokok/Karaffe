import org.karaffe.compiler.base.tree.term.NameKind
import org.karaffe.compiler.base.tree.term.Terms
import spock.lang.Specification

class PathSpec extends Specification {

    def "QualifiedModuleNameTest"() {
        setup:
        def name = Terms.moduleName("qualified.moduleName")

        expect:
        name.nameKind == NameKind.MODULE
        name.asFullName() == "qualified.moduleName"
        name.asSimpleName() == "moduleName"
        !name.isPrimitiveType()
        !name.isDefaultInternalName()
    }
}