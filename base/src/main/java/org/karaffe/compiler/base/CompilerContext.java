package org.karaffe.compiler.base;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.base.util.config.Options;
import org.kohsuke.args4j.CmdLineException;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface CompilerContext {
    void startParseArgs() throws CmdLineException;

    String getState();

    void setState(String state);

    boolean hasInvalidCmdLineArg();

    void setInvalidCmdLineArg();

    boolean isEmptyRawArg();

    Options getCmdLineOptions();

    void printUsage();

    void addSourceFile(SourceFile sourceFile);

    Stream<SourceFile> sourceFileStream();

    void addLexer(Lexer lexer);

    Stream<CommonTokenStream> tokenStreamStream();

    void addContext(ParserRuleContext context);

    Stream<ParserRuleContext> contextStream();

    Tree getCompilationUnit();

    void setCompilationUnit(Tree compilationUnit);

    void onPackageFilePair(String packageName, String relativeFilePath);

    @SuppressWarnings("unused")
    void printUsage(PrintStream printStream);

    void onFileImportDef(Position position, Def importDef);

    Map<String, List<Def>> getFileImportMap();

    Map<String, String> getPackageFileMap();
}
