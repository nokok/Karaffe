# Karaffeコンパイラ 抽象構文木 (AST)

## 導入

抽象構文木（AST）は、プログラミング言語で書かれたソースコードの抽象的な構文構造を木構造で表現したものです。Karaffeコンパイラにおいて、ASTはあなたのKaraffeコードの重要な中間表現です。ソーステキストを解析した後、コンパイラはASTを構築します。この木は、意味解析、最適化、コード生成といった後続のコンパイルフェーズで使用されます。

ASTは、表面的な構文（括弧やセミコロンなど、その意味が木構造に暗黙的に含まれる場合）の多くを無視し、本質的な構成要素とその関係に焦点を当てることで、コードを構造的に扱う方法を提供します。

## コアコンセプト

Karaffe ASTは、`src/main/java/org/karaffe/compiler/tree/` ディレクトリにあるいくつかのコアインターフェースとクラスを中心に構築されています。

### `Node` (`src/main/java/org/karaffe/compiler/tree/Node.java`)

`Node` インターフェースは、AST内の単一のノードを表します。`Node` の主な側面は次のとおりです。

- **親子関係**: 各ノード（ルートを除く）は親を持ち、複数の子を持つことができ、木構造を形成します。`getParent()` や `getChildren()` のようなメソッドで走査が可能です。
- **ノードタイプ**: すべてのノードは `NodeType`（下記参照）を持ち、それがどのような種類の言語構成要素を表すか（例：クラス定義、メソッド呼び出し、識別子）を指定します。
- **ソース位置**: ノードは `Positioned` インターフェースを実装しており、元のソースファイル内での位置情報（行番号と列番号）を格納していることを意味します。これはエラー報告に不可欠です。
- **操作**: このインターフェースは、`addChild(Tree child)` や `replaceThis(Tree after)` のように木を変更するためのメソッドを提供します。

### `Tree` (`src/main/java/org/karaffe/compiler/tree/Tree.java`)

`Tree` インターフェースは `Node` を（`Term` を介して間接的に）拡張し、ASTを操作するためのより高レベルなオペレーションを提供します。これには以下が含まれます。

- **クエリ**: `dig(...)` や `climb(...)` のようなメソッドで、特定のノードやパターンを木の中で検索できます。
- **プロセッサ**: `applyProcessor(Processor<R> processor)` メソッドにより、ビジターパターンを使用して木に様々な操作（変換や解析など）を適用できます。
- **挿入**: `insertBefore(Tree tree)` や `insertAfter(Tree tree)` のような便利なメソッドで、既存のノードに対して新しいノードを簡単に追加できます。

### `NodeType` (`src/main/java/org/karaffe/compiler/tree/NodeType.java`)

このenumは、Karaffe ASTに存在しうるすべての可能なノードタイプを定義します。これらのタイプを理解することは、任意のKaraffeプログラムのASTの構造を理解する鍵となります。いくつかの重要な例を以下に示します。

- **`CompilationUnit`**: 単一ソースファイルのASTのルートです。
- **`SourceFile`**: ソースファイル自体を表します。
- **`Module`**: モジュール宣言を表します。
- **`Package`**: パッケージ宣言を表します。
- **`DefClass`**: クラス定義を表します。
- **`DefConstructor`**: コンストラクタ定義を表します。
- **`DefMethod`**: メソッド定義を表します。
- **`DefVar`**: 変数定義（フィールド、ローカル変数）を表します。
- **`Apply`**: メソッド呼び出しや関数適用を表します。
- **`Select`**: オブジェクトやクラスのメンバーへのアクセス（例：`object.field`）を表します。
- **`Identifier`**: 識別子（例：変数名、クラス名）を表します。
- **`StringLiteral`**, **`IntLiteral`**: リテラル値を表します。
- **`BinOp`**: 二項演算（例：`a + b`）を表します。
- **`Assign`**: 代入操作を表します。
- **`TypeName`**, **`VarName`**: 型名や変数名を表し、宣言や参照でよく使用されます。

これは網羅的なリストではありませんが、遭遇する可能性のある一般的なノードタイプの多くをカバーしています。

## さらなる探求

AST実装についてより深く掘り下げるには、ソースコードを直接調べることができます。

- **コアASTインターフェースとクラス**: [`src/main/java/org/karaffe/compiler/tree/`](./src/main/java/org/karaffe/compiler/tree/)
  - [`Node.java`](./src/main/java/org/karaffe/compiler/tree/Node.java)
  - [`NodeType.java`](./src/main/java/org/karaffe/compiler/tree/NodeType.java)
  - [`Tree.java`](./src/main/java/org/karaffe/compiler/tree/Tree.java)
  - [`SimpleTree.java`](./src/main/java/org/karaffe/compiler/tree/SimpleTree.java) (`Tree` の一般的な実装)
  - [`TreeFactory.java`](./src/main/java/org/karaffe/compiler/tree/TreeFactory.java) (新しいツリーノードを作成するため)
- **AST Walker**: 木を走査するためには、[`src/main/java/org/karaffe/compiler/tree/walker/`](./src/main/java/org/karaffe/compiler/tree/walker/) を参照してください。
- **AST Processor**: 木に対して実行される操作については、[`src/main/java/org/karaffe/compiler/tree/processor/`](./src/main/java/org/karaffe/compiler/tree/processor/) を参照してください。

これらのファイルと、それらがコンパイラ全体でどのように使用されているか（例：フロントエンドの解析段階やバックエンドのコード生成段階）を調べることで、KaraffeのASTについての包括的な理解を得ることができます。
