package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public abstract class Literal extends TermNode {

	public Literal(final Token token) {
		super(NodeType.LITERAL, token);
	}

	abstract public boolean isBoolLiteral();

	abstract public boolean isTrueLiteral();

	public boolean isFalseLiteral() {
		if (this.isBoolLiteral()) {
			return !this.isTrueLiteral();
		}
		return false;
	}

	public abstract static class BoolLiteral extends Literal {
		public BoolLiteral(Token token) {
			super(token);
		}

		@Override
		public boolean isBoolLiteral() {
			return true;
		}

	}

	public static class TrueLiteral extends BoolLiteral {
		public TrueLiteral(Token token) {
			super(token);
		}

		@Override
		public boolean isTrueLiteral() {
			return true;
		}

		@Override
		public void accept(KaraffeTreeVisitor visitor) {
			visitor.visit(this);
		}
	}

	public static class FalseLiteral extends BoolLiteral {

		public FalseLiteral(Token token) {
			super(token);
		}

		@Override
		public boolean isTrueLiteral() {
			return false;
		}

		@Override
		public void accept(KaraffeTreeVisitor visitor) {
			visitor.visit(this);
		}
	}

	public static class IntLiteral extends Literal {
		public IntLiteral(Token token) {
			super(token);
		}

		@Override
		public boolean isBoolLiteral() {
			return false;
		}

		@Override
		public boolean isTrueLiteral() {
			return false;
		}

		@Override
		public void accept(KaraffeTreeVisitor visitor) {
			visitor.visit(this);
		}
	}

	public static class ThisLiteral extends Literal {
		public ThisLiteral(Token token) {
			super(token);
		}

		@Override
		public boolean isBoolLiteral() {
			return false;
		}

		@Override
		public boolean isTrueLiteral() {
			return false;
		}

		@Override
		public void accept(KaraffeTreeVisitor visitor) {
			visitor.visit(this);
		}
	}
	
	@Override
	public NodeList normalize(NormalizeContext context) {
		List<Node> nodes = new ArrayList<>();
		Name name = context.nextName(nodes);
		nodes.add(new Assign(name, this));
		return new NodeList(nodes);
	}

}
