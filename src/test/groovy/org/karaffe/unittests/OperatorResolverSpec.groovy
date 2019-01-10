package org.karaffe.unittests

import org.karaffe.compiler.util.resolver.OperatorResolver
import org.objectweb.asm.Opcodes
import spock.lang.Specification
import spock.lang.Unroll

class OperatorResolverSpec extends Specification {
  @Unroll
  def "valid plusOperator #source"() {
    when:
    new OperatorResolver(source)

    then:
    noExceptionThrown()

    where:
    source << [
      int,
      long,
      float,
      double
    ]
  }

  @Unroll
  def "invalid plusOperator #source to #type"() {
    when:
    new OperatorResolver(source)

    then:
    thrown(type)

    where:
    source  || type
    boolean || IllegalArgumentException
    char    || IllegalArgumentException
    null    || NullPointerException
  }

  @Unroll
  def "plus #sourceClass + #paramClass = #selectedOpCode"() {
    setup:
    def resolver = new OperatorResolver(sourceClass)
    def inst = resolver.plus(paramClass)

    expect:
    inst.opcode == selectedOpCode

    where:
    sourceClass | paramClass || selectedOpCode
    int         | int        || Opcodes.IADD
    int         | long       || Opcodes.LADD
    int         | float      || Opcodes.FADD
    int         | double     || Opcodes.DADD
    long        | int        || Opcodes.LADD
    long        | long       || Opcodes.LADD
    long        | float      || Opcodes.FADD
    long        | double     || Opcodes.DADD
    float       | int        || Opcodes.FADD
    float       | long       || Opcodes.FADD
    float       | float      || Opcodes.FADD
    float       | double     || Opcodes.DADD
    double      | int        || Opcodes.DADD
    double      | long       || Opcodes.DADD
    double      | float      || Opcodes.DADD
    double      | double     || Opcodes.DADD
  }
}
