#Karaffe APIDoc

##型チェック関数
型チェック関数はType Aliasで型を生成すると自動で宣言されます。  
`TypeName`型を生成した場合、型チェック関数は`isTypeName`で宣言され、型は常に`Any to Boolean`です。  
この関数に変数を渡すと、その変数が`TypeName`型に変換可能である場合`True`を返します。  
```
type TypeName
isTypeName : Any to Boolean = sw
TypeName = true
_ = false
```

##演算子
優先度順に記述されています。上に行くほど優先度が高いです。  
優先度の境目に空セルが挿入されています。

| 演算子 | 型 | 結合性 | 概要 | コード例 | オーバーロード
|---|---|---|---|---|---|
| `*` | `Int Int to Int` | 左 | 乗算を行います | `1 * 2` |※1
| `/` | `Int Int to Int or Float` | 左 | 除算を行います | `4 / 3` | ※1
| `%` | `Int Int to Int` | 左 | 剰算を行います | `10 % 3` | ※2
| `&` | `Boolean Boolean to Boolean` | 左 | AND演算を行います。`and`関数と同じ | `True & False` | - |
|  |
| `+` | `Int Int to Int` | 左 | 加算を行います | `5 + 3` | ※3
| `-` | `Int Int to Int` | 左 | 減算を行います | `2 - 4` | ※3
| &#x7c; | `Boolean Boolean to Boolean` | 左 | OR演算を行います。`or`関数と同じ | `True | False` | -
※1  `Float Float to Int or Float`, `Int Float to Int or Float`,  `Float Int to Int or Float`  
※2 `Int Float to Int`,`Float Int to Int` , `Float Float to Int`  
※3 `Float Int to Int or Float`, `Int Float to Int or Float`, `Float Float to Int or Float`
##トップレベルユーティリティ関数
###I/O(コンソール)
| 関数 | 型 | 概要 | 入力例 | 