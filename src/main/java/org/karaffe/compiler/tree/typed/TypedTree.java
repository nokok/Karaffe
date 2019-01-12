package org.karaffe.compiler.tree.typed;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.Position;

import javax.lang.model.type.TypeMirror;
import java.util.Objects;

public class TypedTree extends Tree {
  private TypeMirror typeMirror;

  public TypedTree(NodeType nodeType, TypeMirror typeMirror) {
    this(nodeType, typeMirror, Position.noPos());
  }

  public TypedTree(NodeType nodeType, String name, TypeMirror typeMirror) {
    this(nodeType, name, typeMirror, Position.noPos());
  }

  public TypedTree(NodeType nodeType, TypeMirror typeMirror, Position position) {
    this(nodeType, "", typeMirror, position);
  }

  public TypedTree(NodeType nodeType, String name, TypeMirror typeMirror, Position position) {
    super(nodeType, name, position);
    this.typeMirror = Objects.requireNonNull(typeMirror);
  }

  public TypeMirror getEvaluatedType() {
    return this.typeMirror;
  }
}
