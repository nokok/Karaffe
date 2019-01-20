package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

public interface Positioned {

  default Position getPosition() {
    return Position.noPos();
  }

}
