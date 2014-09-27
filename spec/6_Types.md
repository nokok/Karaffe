#型
---


##Any
最上位の型です。  
このドキュメントで定義されている型は全てこの型からType Aliasを用いて再宣言されています。  

| 変数名 | 型 | 動作 |
|---|---|---|
|value| `Void to String` | オブジェクトの文字列表現を取得する
|hash|`Void to Int` | オブジェクトのハッシュ

##Num
数値を格納する型です。  

宣言
```
type Num = Any
check isNum
```

| 変数名 | 型 | 動作 |
|---|---|---|
|value| `Void to String` | 数値の文字列表現を取得する
|hash | `Void to Int` | 数値をそのまま返す。実数の場合は四捨五入を行う

##String
文字列を格納する型です。  
宣言
```
type String = Any
check isString
```

##Boolean
論理型です。  
宣言
```
type Boolean = Any
check booleanValue
```

##Array
配列です。  
宣言
```
type Array = Any
check arrayValue
```

##Function
関数を格納する型です。

##Enum
列挙型を格納する型です。  
宣言
```
type Enum = Any
```