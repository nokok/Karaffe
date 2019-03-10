package org.karaffe.unittests

import org.karaffe.compiler.util.KaraffeSource
import org.karaffe.compiler.util.Platform
import spock.lang.Specification

import java.nio.CharBuffer
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class KaraffeSourceSpec extends Specification {

  def "toStringMethod"() {
    expect:
    KaraffeSource.fromString("Source").toString() == "Source"
  }

  def "testEquals"() {
    expect:
    CharBuffer.wrap(KaraffeSource.fromString("A")) == CharBuffer.wrap(KaraffeSource.fromString("A"))
  }

  def "fromPath"() {
    setup:
    def path = Paths.get("Source.krf")
    Files.write(path, "class Hoge {}".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)
    def p = KaraffeSource.fromPath(path)
    Files.delete(path)

    expect:
    p.sourceName == "Source.krf"
  }

  def "fromRelativePath"() {
    setup:
    def p = KaraffeSource.fromPath(Paths.get("src/test/resources/Main.krf"))

    expect:
    if (Platform.isWindows()) {
      p.sourceName == "src\\test\\resources\\Main.krf"
    } else {
      p.sourceName == "src/test/resources/Main.krf"
    }
  }

  def "testGetCodeByLine"() {
    setup:
    def s = KaraffeSource.fromString("0\n1")

    expect:
    s.getCodeByLine(1) == "0"
    s.getCodeByLine(2) == "1"
    s.getCodeByLine(3) == "<EOF>"
  }
}
