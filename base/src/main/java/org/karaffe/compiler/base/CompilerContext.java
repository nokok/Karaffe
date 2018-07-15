package org.karaffe.compiler.base;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;

import java.util.stream.Stream;

public interface CompilerContext extends
        SourceFileContainer,
        CommandLineOptionContext,
        StructuralInfoContainer,
        ReportContainer,
        BytecodeContainer {

    String getState();

    void setState(String state);

    void addLexer(Lexer lexer);

    Stream<CommonTokenStream> tokenStreamStream();

    void addContext(ParserRuleContext context);

    Stream<ParserRuleContext> contextStream();

    Tree getCompilationUnit();

    void setCompilationUnit(Tree compilationUnit);

    void onPackageFilePair(String packageName, String relativeFilePath);

    void onFileImportDef(Position position, Def importDef);

    void setInstructions(Instructions instructions);

    Instructions getInstructions();
}
