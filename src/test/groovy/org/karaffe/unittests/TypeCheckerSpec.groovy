package org.karaffe.unittests

import karaffe.core.Int
import karaffe.core.Unit
import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.Tree
import org.karaffe.compiler.typechecker.TypeChecker
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.TypeInfo
import spock.lang.Specification

class TypeCheckerSpec extends Specification {
    def "literal"() {
        def context = new CompilerContext()
        def checker = new TypeChecker(context)
        expect:
        checker.unify(tree) == result

        where:
        tree                                           || result
        new Tree(NodeType.IntLiteral, "1", null)       || new TypeInfo(Int.class)
        new Tree(NodeType.StringLiteral, "Hoge", null) || new TypeInfo(karaffe.core.String.class)
    }

    def "method"() {
        def context = new CompilerContext()
        def checker = new TypeChecker(context)
        expect:
        checker.unify(tree) == result

        where:
        tree                                       || result
        new Tree(NodeType.DefMethod, "main", null) || new TypeInfo(Unit.class)
    }

}
