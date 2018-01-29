package org.karaffe.compiler.phases.inferer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitorAdapter;
import org.karaffe.compiler.types.InferResult;

public class TypeInferVisitor extends KaraffeTreeVisitorAdapter {

	private final Map<String /* name */, String /* defType */> definedMap = new HashMap<>();
	private final Map<String /* name */, String /* type */> inferredTypes = new HashMap<>();
	private final TypeInferRule rule = new TypeInferRule();

	public Optional<String> getType(String name) {
		if (this.inferredTypes.containsKey(name)) {
			return Optional.ofNullable(this.inferredTypes.get(name));
		}
		return Optional.empty();
	}

	@Override
	public void visit(ValDef node) {
		Node name = node.findNameNode();
		Node typeName = node.findTypeNameNode();
		if (name.isName()) {
			Name nameNode = (Name) name;
			this.definedMap.put(nameNode.getName(), rule.getType(typeName).getType().orElse(InferResult.defaultTypeName()));
		}
		super.visit(node);
	}
}
