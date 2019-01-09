package karaffe.reflect;

public interface Member {
  Symbol getSymbol();

  Member.Type getType();

  enum Type {
    FIELD,
    CLASS,
    METHOD,
    CONSTRUCTOR
  }
}
