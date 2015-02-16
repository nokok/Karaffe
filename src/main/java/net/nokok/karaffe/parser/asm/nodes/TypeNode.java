package net.nokok.karaffe.parser.asm.nodes;

import java.util.function.Function;

public abstract class TypeNode<T,F extends Function<Class<T>, R>,R> {
	private final Class<T> clazz;
	
	public TypeNode(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public abstract R apply(F func); 
}
