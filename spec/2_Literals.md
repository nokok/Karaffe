#リテラル

##論理型(Boolean)
```
論理型 ::= True | False | true | false
```
論理型は真と偽を表現するリテラルとしてよく知られています。Karaffeではこの論理型は`Boolean`型のオブジェクトとして宣言されています。

##整数
数字の中で整数を表現するリテラルです。    
数値リテラルの例を以下に示します。厳密な定義は未確定です。  

 * `1`
 * `-1`
 * `7895789231`
 * `0x9043`
 * `0x3f`
 * `0XFFFF`

関連する型:`Int`,`Num`
 
##実数
数字の中で実数を表現するリテラルです。  
実数リテラルの例を以下に示します。厳密な定義は未確定です。  

 * `1.0`
 * `0.3`
 * `-0.1`
 * `5E10`
 * `1.7e14`
 * `-2.5e10`

関連する型:`Float`,`Num`

##文字列
文字列を扱うリテラルです。  
文字リテラルは存在しません。  
文字列リテラルの例を以下に示します。厳密な定義は未確定です。  

 * `"string"`
 * `"string\nwith \"escape sequence\""`
 * `"string with \(variableName)"`

#####利用可能なエスケープシーケンス

| エスケープシーケンス | 説明 |
|---|---|
| `\"` | "
| `\\` | \
| `\/` | /
| `\n` | 改行
| `\r` | 復帰
| `\t` | タブ
| `\(foo)` | 変数fooに置き換え

関連する型:`String`

##配列
配列リテラルの例を以下に示します。厳密な定義は未定です。

* `[]`
* `[1]`
* `[1 2 3 4 5]`
* `[1..5]`
* `[1..]`

関連する型:`A[]`
##関数
関数リテラルの例を以下に示します。厳密な定義は未確定です。  

* `True`<sub>※</sub>
* `[] to "Hello"`
* `[a] to * a 2`
* `[a b] to + a b`

※:関数を格納する変数の型が引数を取らない場合(`Void to`で始まる型宣言)  
もしくは`$0`などの暗黙宣言される変数名に別名を付与しない場合、`[]`、`[変数名...]`が省略可能です。  

制限

* `Void to`から始まる型宣言の場合、`$0`などの変数を用いると宣言されていないためエラーとなります。  
* `Void to`から始まる型宣言の場合、`[]`中で変数宣言を行うとエラーとなります。
 
コード例
```
//型推論が働く例
a = [] to "hoge"
println typeof a //Type[Void to String]
b = [a] to a * 2
println typeof b //Type[Num to Num]

//型推論が働かない例
a = true
println typeof a //Type[Boolean]

//型を明示的に指定する例(推奨)
b  : Void to Boolean = true
b1 : Void to Boolean = [] to true

c  : Int Int to Int = [a b] to a + b
c1 : Int Int to Int = $0 + $1

```

関連する型:`Type[A to A]`,`Type[A to B]`

##型
型リテラルの例を以下に示します。厳密な定義は未確定です。  

* `Type[1]`
* `Type[0.4]`
* `Type[1..5]`
* `Type[T]`
* `Type[Int]`
* `Type[Int to Float]`

コード例
```
intType = Type[Int]
intType1 : Type[Int] = Type[Int]

```
関連する型:`Type[a]`