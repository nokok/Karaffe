package net.nokok.karaffe.parser.asm.nodes;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

public class InsnNodeItem<T> {
	private final AbstractInsnNode node;
	private final Class<T> clazz;
	
	public InsnNodeItem(AbstractInsnNode node,Class<T> clazz){
		this.node = node;
		this.clazz = clazz;		
	}
	
	public AbstractInsnNode getNode(){
		return node;
	}
	
	public Class<T> getClazz(){
		return clazz;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(node.getClass()).append(":").append(clazz);
	    return sb.toString();
	}
}
