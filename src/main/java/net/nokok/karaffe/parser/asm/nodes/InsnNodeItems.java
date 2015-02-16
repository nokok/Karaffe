package net.nokok.karaffe.parser.asm.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InsnNodeItems {
	private final List<InsnNodeItem<?>> items = new ArrayList<>();
	
	public void add(InsnNodeItem<?> insnNodeItem){
		items.add(insnNodeItem);
	}
	
	public Stream<InsnNodeItem<?>> stream(){
		return items.stream();
	}
}
