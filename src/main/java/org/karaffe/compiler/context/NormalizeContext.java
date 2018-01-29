package org.karaffe.compiler.context;

import java.util.List;

import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.util.NameGen;

public class NormalizeContext {
	private final NameGen nameGen = new NameGen("kn_");
	
	public Name nextName(List<Node> nodes) {
		Name name = this.nameGen.genName();
        ValDef def = new ValDef(new Modifiers(), name, new Empty());
        nodes.add(def);
        return name;
	}
}
