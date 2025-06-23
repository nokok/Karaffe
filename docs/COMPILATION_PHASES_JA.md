# Karaffeコンパイラ：コンパイルフェーズ

このドキュメントでは、Karaffeコンパイラの主要なコンパイルフェーズについて説明します。コンパイラは、Karaffeのソースコードを一連のステップで処理し、最終的に実行可能なコードまたはその他の出力を生成します。これらのステップは「フェーズ」として管理されます。

## コアコンセプト

- **`Phase`**: コンパイルの個別の段階（例：解析、型チェック、コード生成）を表すインターフェース。各フェーズは `org.karaffe.compiler.phase.Phase` を実装します。 ([`src/main/java/org/karaffe/compiler/phase/Phase.java`](./src/main/java/org/karaffe/compiler/phase/Phase.java))
- **`Phases`**: `Phase` オブジェクトのコレクション。コンパイラは通常、一連のフェーズを実行します。 ([`src/main/java/org/karaffe/compiler/phase/Phases.java`](./src/main/java/org/karaffe/compiler/phase/Phases.java))
- **`CompilerContext`**: ソースファイル、解析結果、生成されたAST/IR、エラーや警告など、コンパイルの状態を保持するオブジェクト。フェーズは `CompilerContext` から読み取り、書き込みを行います。 ([`src/main/java/org/karaffe/compiler/util/CompilerContext.java`](./src/main/java/org/karaffe/compiler/util/CompilerContext.java))
- **`DefaultPhasesFactory`**: 標準的なコンパイルフェーズのシーケンスを作成する責任を負います。 ([`src/main/java/org/karaffe/compiler/phase/DefaultPhasesFactory.java`](./src/main/java/org/karaffe/compiler/phase/DefaultPhasesFactory.java))

## 主なコンパイルフロー

全体のコンパイルプロセスは `org.karaffe.compiler.KaraffeCompiler` によって調整されます。これはフロントエンドを呼び出し、次にバックエンドを呼び出します。

### 1. フロントエンドフェーズ (`org.karaffe.compiler.phase.frontend.karaffe.KaraffeFrontend`)

`KaraffeFrontend` は、ソースコードを処理する初期フェーズをグループ化します。これらは通常、順次実行されます。 ([`src/main/java/org/karaffe/compiler/phase/frontend/karaffe/KaraffeFrontend.java`](./src/main/java/org/karaffe/compiler/phase/frontend/karaffe/KaraffeFrontend.java))

#### 1.1. 解析 (`KaraffeParsePhase`)
- **名前**: `frontend-karaffe-parser`
- **説明**: これは最初の主要なフェーズです。生のKaraffeソースコードを入力として受け取り、解析木（具象構文木またはCSTとも呼ばれる）を生成します。
    - ANTLRで生成された `KaraffeLexer` を使用して、ソースコードをトークンに分割します。
    - 次に `KaraffeParser` を使用して、`Karaffe.g4` で定義された文法に基づいて解析木を構築します。
- **入力**: Karaffeソースファイル。
- **出力**: ソースファイル名とその `KaraffeParser.SourceFileContext`（そのファイルの解析木のルート）のマップ。このマップは `CompilerContext` にキー `"parse.result"` で格納されます。
- **ソース**: [`src/main/java/org/karaffe/compiler/phase/frontend/karaffe/KaraffeParsePhase.java`](./src/main/java/org/karaffe/compiler/phase/frontend/karaffe/KaraffeParsePhase.java)

#### 1.2. 中間表現 (IR) 生成 (`IRPhase`)
- **名前**: `ir`
- **説明**: このフェーズは `KaraffeParsePhase` によって生成された解析木を受け取り、それらをより抽象的な表現である抽象構文木（AST）または類似の中間表現（IR）に変換します。このIRは、後続の分析および最適化フェーズで扱いやすくなります。
    - `IRGen` クラス（おそらくANTLRビジター）を使用して解析木をウォークし、IR/ASTを構築します。
    - 生成されるAST構造は `org.karaffe.compiler.tree.*` で定義されています（`AST_DOCUMENTATION_ja.md` を参照）。
- **入力**: `CompilerContext` からの解析木マップ（キー: `"parse.result"`）。
- **出力**: 生成されたIR/AST。（これを `CompilerContext` に保存したり、バックエンドで利用可能にする正確なメカニズムは、`IRGen` または `IR` オブジェクト自体の中にある可能性があります）。
- **ソース**: [`src/main/java/org/karaffe/compiler/phase/frontend/karaffe/IRPhase.java`](./src/main/java/org/karaffe/compiler/phase/frontend/karaffe/IRPhase.java)
- **関連**: [`src/main/java/org/karaffe/compiler/phase/frontend/karaffe/IRGen.java`](./src/main/java/org/karaffe/compiler/phase/frontend/karaffe/IRGen.java)

### 2. バックエンドフェーズ (例: `org.karaffe.compiler.phase.backend.jvm.BackendForJavaVM`)

フロントエンドが完了した後、`KaraffeCompiler` は明示的にバックエンドを呼び出します。バックエンドは、IR/ASTを受け取り、ターゲット固有のコードを生成する責任を負います。

#### 2.1. JVMバックエンド (`BackendForJavaVM`)
- **名前**: `jvm`
- **説明**: このバックエンドは、Java仮想マシン（JVM）バイトコードを生成することを目的としています。
    - *注: 前回のレビュー時点では、このフェーズの `execute` メソッドは空であり、開発中であるか、ロジックが異なる方法でトリガーされる可能性を示唆しています。*
- **入力**: フロントエンドによって生成されたIR/AST。
- **出力**: JVMバイトコード（例：`.class` ファイル）。
- **ソース**: [`src/main/java/org/karaffe/compiler/phase/backend/jvm/BackendForJavaVM.java`](./src/main/java/org/karaffe/compiler/phase/backend/jvm/BackendForJavaVM.java)

## ユーティリティフェーズ

`DefaultPhasesFactory` は、ユーティリティフェーズも設定します。
- **`ShowUsagePhase`**: コマンドラインの使用情報を表示します。
- **`ShowVersionPhase`**: コンパイラのバージョンを表示します。
- **`ShowReportsPhase`**: コンパイル中に生成されたエラー、警告、または情報メッセージを表示します。

この構造により、モジュール式で拡張可能なコンパイラ設計が可能になり、新しいフェーズを追加したり、既存のフェーズを変更してコンパイラの動作を変更または強化したりすることができます。
