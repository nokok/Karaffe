package org.karaffe.unittests

import org.karaffe.compiler.tree.NodeType
import org.karaffe.compiler.tree.Tree
import spock.lang.Specification

class TreeSpec extends Specification {
    def "treetoString"() {
        expect:
        new Tree(NodeType.DefClass, "A").toString() == 'DefClass ("A", [], [])'
    }
}
