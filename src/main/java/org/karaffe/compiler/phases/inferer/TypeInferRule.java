package org.karaffe.compiler.phases.inferer;

import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.InferResult;

public class TypeInferRule {

	public InferResult getType(Node node) {
		return InferResult.ofEmpty();
	}
}
