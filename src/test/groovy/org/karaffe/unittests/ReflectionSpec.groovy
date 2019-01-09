package org.karaffe.unittests


import karaffe.reflect.Reflections
import spock.lang.Specification

class ReflectionSpec extends Specification {

  def "typeMirror"() {
    setup:
    def typeMirror = Reflections.typeOf(Object)

    expect:
    typeMirror != null
  }

  def "decls"() {
    setup:
    def mirror = Reflections.typeOf(Object)
    def decls = mirror.decls()

    expect:
    decls.size() == 12
  }
}
