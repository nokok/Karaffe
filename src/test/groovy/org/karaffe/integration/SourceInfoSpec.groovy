package org.karaffe.integration

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import org.objectweb.asm.ClassReader
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import spock.lang.Specification

import java.nio.file.Paths

class SourceInfoSpec extends Specification {
  def "test"() {
    setup:
    def context = new CompilerContext()
    context.parseRawArgs(["--dry-run"] as String[])
    context.add(KaraffeSource.fromString(
      """class Main {
                  |}""".stripMargin()
      , "/foo/bar/baz/Main.krf"))
    def compiler = new KaraffeCompiler(context)
    compiler.run()
    def byteCode = context.outputFiles.get(Paths.get("Main.class"))
    ClassReader reader = new ClassReader(byteCode)
    ClassNode classNode = new ClassNode(Opcodes.ASM6)
    reader.accept(classNode, ClassReader.EXPAND_FRAMES)

    expect:
    classNode.sourceFile == "/foo/bar/baz/Main.krf"
  }
}
