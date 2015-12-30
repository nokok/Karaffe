#Karaffe構想メモ

##Karaffeの目標/思想

- メモ帳でも書きやすい言語  
  新言語には自分か誰かが作らないかぎりIDEが無いため、  
  IDEがある前提の文法では使いものにならない。  
  メモ帳はさすがに言い過ぎでも素のVimやEmacs、秀丸などの  
  一般的なエディタで十分書きやすい文法を目指す。  

- Javaと共存し、良い仲間になれる言語  
  既存のJava資産を活用でき、Javaから見ても不自然でないコード生成を行う。  

##型と値について
###Karaffeの型の扱い
コンパイル時に型を決定し、実行時に暗黙的な型変換を行わない。  
Karaffeは静的型付けで、かつ強い型付けをする。  

整数型(`Int`)、浮動小数点型(`Float`,`Double`)、文字型(`Character`)、文字列型(`String`)、ブール型(`Boolean`)の値の範囲や仕様はJavaの仕様を引き継ぐ。  
Javaの`Short`や`Byte`に相当するものは存在しないため`Int`にする。  

##構造に関する文法

###メソッドの宣言

```
def methodName(argName1 ArgType1, argName2 ArgType2) ReturnType {
...
}
```

返り値が無い(Javaのvoidに相当する)場合はそれを省略する。

###フィールドの宣言
```
def fieldName Type = Initializer
```


###クラスの宣言

```
class ClassName {
...
}
```

クラスを宣言すると、宣言したクラス名のclassファイルが生成される。  
上記の例だとHoge.classファイルが生成され、  
内部フォーマットはバージョン52.0(Java 8相当)で生成する。  
クラスはメソッドやフィールド宣言をまとめる。  

###パッケージ

```
package pkg.name
```
  
パッケージは.で区切られ、ディレクトリが階層的に生成される。  
上記の例だと、コンパイラから見たカレントディレクトリにcomディレクトリが生成される。  
さらにcomディレクトリの中にsomethingディレクトリが生成され、  
その中にclassファイルが生成される。  

```
<CompilerCurrentDir>  
  └─pkg      
     └─name
          Some.class
          ...
```

###import文

```
import packagename.ClassName
```

import文を使用することで、指定したクラスがコード中で使えるようになる。  
同パッケージに無い全てのクラスは、import文が必須の仕様。

メソッドやクラスで完全修飾名を使ってクラスにアクセスすることは出来ない。

###改名import文
```
import packagename.ClassName -> NewName
```

クラス名が衝突する場合、改名import文が必須になる。  
改名importによってユニークな名前を指定しなければならない。  

```
import org.some.pack.ClsName -> OClsName
import com.some.pack.ClsName -> CClsName
```

##制御構文と処理に関する文法

###main関数の宣言

```
main {
    
}
```

mainブロック内で、渡されたオプションが`args Array[String]`で暗黙的に宣言される。

###コンストラクタ

```
init {

}

init(arg1 Type1, arg2 Type2) {

}
```

###return文

```
return true
```

###if式

```
if( true ) {
    return "OK"
} else {
    return "NG"
}
```

###while式

```
while( true ) {
    ...
}
```

##修飾子
###public, private

アクセス修飾子

```
class Hoge {
    private def hoge ...

	private {
		def fuga ...
	}
	
	public {
		...
	}
}
```

###final, statics
```
class Hoge {
	final {
		...
	}
	
	public final {
	
	}
}
```

###組み合わせ
```
class Hoge {
	private static final {
		...
	}
}
```

###コンパイルオプション
コンパイル時のオプションは、`krfbuild`に記述する。
