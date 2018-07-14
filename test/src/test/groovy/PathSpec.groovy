import org.karaffe.compiler.base.pos.Position
import org.karaffe.compiler.base.tree.term.NameKind
import org.karaffe.compiler.base.tree.term.Terms
import spock.lang.Specification

class PathSpec extends Specification {

    def "moduleName"() {
        setup:
        def name = Terms.moduleName(Position.noPos(), "qualified.moduleName")

        expect:
        name.nameKind == NameKind.MODULE
        name.asFullName() == "qualified.moduleName"
        name.asSimpleName() == "moduleName"
        !name.isPrimitiveType()
        !name.isDefaultInternalName()
    }

    def "nestedName"() {
        setup:
        def name = Terms.arrayTypeName(Position.noPos(), Terms.typeName(Position.noPos(), "karaffe.core.String"))

        expect:
        name.nameKind == NameKind.NESTED
        name.asFullName() == "Array[karaffe.core.String]"
        name.asSimpleName() == "Array[String]"
        !name.isPrimitiveType()
        !name.isDefaultInternalName()
    }
}