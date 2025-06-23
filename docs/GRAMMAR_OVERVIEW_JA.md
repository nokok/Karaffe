# Karaffeコンパイラ：ANTLR文法概要 (`Karaffe.g4`)

このドキュメントでは、Karaffeプログラミング言語の構文を定義する `Karaffe.g4` ANTLR文法ファイルの概要を説明します。ANTLR (ANother Tool for Language Recognition) は、Karaffeでソースコードを読み取り、解析木（または具象構文木 - CST）を構築するために使用されるパーサジェネレータです。この解析木は、IR/AST生成フェーズなど、後のコンパイラフェーズで処理されます。

文法ファイル `Karaffe.g4` は次の場所にあります: [`src/main/antlr/Karaffe.g4`](./src/main/antlr/Karaffe.g4)

## 文法ファイルの構造

`Karaffe.g4` ファイルは、主に2種類のルールで構成されています。

1.  **パーサールール**: これらのルールは、言語の構文構造を定義します – キーワード、識別子、式、文などがどのように組み合わされて有効なKaraffeプログラムを形成するか。パーサールールの名前は通常、小文字で始まります (例: `sourceFile`, `classDef`, `expr`)。
2.  **レキサールール**: これらのルールは、言語の基本的なトークンを定義します – キーワード、識別子、リテラル（文字列、数値）、演算子など、最小の構成要素。レキサールールの名前は通常、大文字で始まります (例: `CLASS`, `IDENTIFIER`, `StringLiteral`)。

## 主要なパーサールール

Karaffeコードの全体構造を定義する最も重要なパーサールールの一部を以下に示します。

-   **`sourceFile`**: 完全なKaraffeソースファイルを解析するためのエントリポイント（または「開始記号」）です。基本的には、ソースファイルをクラス定義のシーケンス (`classDef*`) とそれに続くファイル終端マーカー (`EOF`) として定義します。
    ```antlr
    sourceFile
      : classDef* EOF
      ;
    ```

-   **`classDef`**: クラス定義の構文を定義します。`CLASS` キーワードで始まり、`Identifier`（クラス名）が続き、オプションでクラス本体 (`typeDefBody`) が続きます。
    ```antlr
    classDef
      : CLASS Identifier nl? typeDefBody? nl?
      ;
    ```

-   **`typeDefBody`**: 中括弧 `{ }` で囲まれたクラスの本体を表します。複数の `statement` を含むことができます。
    ```antlr
    typeDefBody
      : LBRACE nl? statement* RBRACE nl?
      ;
    ```

-   **`statement`**: このルールは、クラス本体内に現れる可能性のあるさまざまな種類の文をカバーします。これには以下が含まれます。
    -   `entryPointBlock`: `ENTRYPOINT` キーワードでマークされた特別なブロックで、おそらくプログラム実行のメインエントリポイントです。
    -   `initBlock`: `INIT` キーワードでマークされたブロックで、おそらく初期化コード用です。
    -   `varDef`: 変数定義（`DEF` キーワードを使用）。
    -   `assign`: 代入文。
    -   `expr`: 文として使用される一般的な式（例：関数呼び出し）。

-   **`varDef`** および **`binding`**: これらのルールは、変数がどのように宣言されるかを定義します。`varDef` は `DEF` キーワードを使用し、その後に `binding`（`Identifier` と `typeName`）が続き、オプションで初期化子が続きます。
    ```antlr
    varDef
      : DEF binding ('=' initializer=expr)? nl?
      ;

    binding
      : Identifier typeName nl?
      ;
    ```

-   **`expr`**: これは式の構造を定義する重要なルールです。Karaffeの式は次のようになります。
    -   識別子 (`id=Identifier`)
    -   リテラル (`lit=literal`)
    -   `this` キーワード (`t=THIS`)
    -   関数呼び出し (`function=expr LPAREN args=exprList? RPAREN`)
    -   二項演算 (`left=expr right=opExpr+` ここで `opExpr` は `op=binaryOperator right=expr`)
    -   メンバーアクセス (`target=expr DOT name=Identifier`)
    -   括弧で囲まれた式 (`LPAREN inExpr=expr RPAREN`)

-   **`nl` / `newLine`**: これらのルールは、改行とセミコロンを文の終端記号またはオプションの区切り文字として処理します。

## 主要なレキサールール

レキサールールは、入力テキストから直接認識されるトークンを定義します。

-   **キーワード**: `CLASS`, `DEF`, `ENTRYPOINT`, `INIT`, `IF`, `WHILE`, `RETURN`, `TRUE`, `FALSE`, `NULL`, `THIS` など、多くのキーワードが定義されています。各キーワードは特定の文字列リテラルです (例: `CLASS: 'class';`)。
-   **`Identifier`**: 有効な識別子（クラス、メソッド、変数の名前）を構成するものを定義します。Karaffeでは、識別子は文字と数字のシーケンス、または演算子文字のシーケンス（例: `+`, `==`, `*`）にすることができます。
    ```antlr
    Identifier
      : Letter LetterOrDigit*
      | OperatorChar+
      // ... 他の形式
      ;
    ```
    これは、`+` や `*` のような記号がレキサーによって識別子として扱われ、演算子としての意味は、おそらく後の解析または意味解析段階で `binaryOperator` パーサールールに基づいて決定されることを意味します。
-   **`StringLiteral`**: 文字列リテラルを定義します (例: `"hello"`)。
-   **`IntegerLiteral`**: 整数リテラルを定義します (例: `123`, `0`)。
-   **演算子と句読点**: `LPAREN` (`(`), `RPAREN` (`)`), `LBRACE` (`{`), `RBRACE` (`}`), `DOT` (`.`), `COMMA` (`,`), `SEMI` (`;`) のようなトークンが定義されています。
-   **`WS` (空白)**: スキップするように定義されているため (`-> skip`)、空白は一般にトークンを区切る以外にはパーサーにとって重要ではありません。

## 仕組み

Karaffeコンパイラがソースファイルに遭遇すると：
1.  `KaraffeLexer`（`Karaffe.g4` から生成）がソースコードを読み取り、レキサールールに基づいてトークンのストリームに分割します（例：`CLASS`, `Identifier "MyClass"`, `LBRACE` など）。
2.  `KaraffeParser`（これも `Karaffe.g4` から生成）がこのトークンストリームを受け取り、`sourceFile` ルールから始めてパーサールールに照合しようとします。
3.  トークンストリームが文法に準拠している場合、パーサーは正常に解析木を構築します。この木はコードの構文構造を表します。たとえば、ツリー内の `classDef` ノードには、`CLASS` キーワード、クラス `Identifier`、およびその `typeDefBody` の子があります。
4.  この解析木は、抽象構文木（AST）を生成し、さらなる分析を実行するために、後続のコンパイラフェーズ（`COMPILATION_PHASES_JA.md` で説明されている `IRPhase` など）に渡されます。

`Karaffe.g4` 文法を理解することは、Karaffeコンパイラによって受け入れられる正確な構文と、ソースコードが最初にどのように処理されるかを理解するために不可欠です。
