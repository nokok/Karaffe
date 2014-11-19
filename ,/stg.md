#Karaffe
特徴
・副作用を許容する関数型言語＋オブジェクト指向言語  
・コンパイル時に推論出来るものはできるだけ行う  

#設計思想
1.Javaコードを生成する  
2.メモ帳でも書ける  
3.とにかくシンプルで  
4.現代的な機能を持った言語  

#変数
定数宣言  
`varName = 0`

変数宣言  
`seffect varName = 0`

型を指定した宣言  
`varName : Int = 0`

#関数

関数定義1

```
f : Int to Int
f = x * x
```

関数定義2

```
f : x:Int to Int = x * x
```

関数リテラル

```
f = [num] => x * x
```

関数の実装定義(パターンマッチ)

```
fizzBuzz : Int to String
fizzBuzz = 15 => "FizzBuzz" 
fizzBuzz = 3 => "Fizz"
fizzBuzz = 5 => "Buzz"
fizzBuzz = _ => _
```
この関数は以下のコードと同一の振る舞いをするが厳密には異なる。
上のコードは4つの関数が定義されているが、下のコードは1つの関数で内部で分岐させている。

```
fizzBuzz : Int to String =
. switch num
. 15 => "FizzBuzz"
. 3 => "Fizz"
. 5 => "Buzz"
```

パターンマッチのcaseでは以下のものを使用することが出来る。

- 式
- リテラル
- 型(演算子を含む)

デフォルト値付きの引数を持った関数定義

```
pow : x:Int n:Int = 2 to Int = x ^ n
pow 2   // 4
pow 4 5 // 1024
```
※区切りが曖昧になる時

```
pow 2 + 5
//この場合、pow関数にxとして2を渡してnのデフォルト値2を使った2 ^ 2に5を加えるのか、
//pow 7を計算するかが曖昧
//区切り文字である.を使用することで回避出来るようにする
pow 2 . + 5 //2^2を先に計算してから+5する
pow 
```

型の別名を定義(代入互換性を失う)

```
type NewName = ExistingTypeName
```

型を継承(代入互換性を持つが元の型が持つ関数はオーバーロード不可)

```
type NewName < ExistingTypeName
```

#ラムダ式

```
[] => expr
[varName] => expr
[v1 v2 v3] => expr
[v1:Type v2:Type] => expr
```

#演算子とその他
各演算子は型に紐付いているメソッドとして定義されている。
演算子はオーバーロードと追加の定義が可能。

木構造の評価をする際の優先度、結合性、対応する関数またはクラス

| 優先度 | 演算子 | 演算子の効果 | 結合性 | 対応する関数またはクラス
|:---:|:---:|:---:|:---:|:---:|
| 200 | (型推論) | 型の決定 | 結合性なし | なし
| 190 | 関数呼び出し | 関数の呼び出し | 右結合 | なし
| 180 | メンバ変数アクセス | オブジェクトが所有するメンバ変数へアクセス | 左結合 | なし
| 180 | 配列要素 | 配列要素の評価をします | 無結合 | なし
| 150 | `*` | 乗算 | 左結合 | `multiply : A A to A`
| 150 | `*?`| 乗算 | 左結合 | `multiply : A A to A?`
| 150 | `\` | 除算 | 左結合 | `division : A A to A`
| 150 | `\?`| 除算 | 左結合 | `division : A A to A?`
| 150 | `%` | 剰余算 | 左結合 | `remainder : A A to Int`
| 150 | `%?`| 剰余算 | 左結合 | `remainder : A A to Int?`
| 140 | `+` | 加算 | 左結合 | `addition : A A to A`
| 140 | `+?`| 加算 | 左結合 | `addition : A A to A?`
| 130 | `-` | 減算 | 左結合 | `subtraction : A A to A`
| 130 | `-?` | 減算 | 左結合 | `subtraction : A A to A?`
| 100 | `..` | 配列の生成 | 非結合 | `Array from:foo toClosed:bar step:1`
| 100 | `.<` | 配列の生成 | 非結合 | `Array from:foo to:bar step:1`

`?`で終わる演算子はオーバーフロー、ゼロ除算などの数学的なエラーに対して安全にする。  

算術演算子

```
a = 1 + 2
b = 1 - 2
c = 1 * 2
d = 1 / 2
e = 1 % 2
```

演算子の定義、オーバーロード
自作したPoint型に+演算子を追加する場合

```
type Point
x : Int = 0
y : Int = 0
op + : Point = [p,q] => 
. x = p x + q x
. y = p y + q y
. Point x:x y:y //最後に評価された式が返る
op - : Point = [p,q] => 
. x = p x - q x
. y = p y - q y
. Point x:x y:y
x = Point x:1 y:2
y = Point x:10 y:5
result = x + y
println result x // 11
println result y // 7
```

前項演算子の定義は`uop`で行う

```
uop - : Point = [p] =>
. x = - p x 
. y = - p y
. Point x:x y:y
//または
uop - : Point = [p] => Point x:- p x y:- p y
```

`.`で同じスコープを持たせる

```
hoge = [n] =>
. x = n + 2
. y = x + x
```

1行で書いた場合も同じ動作をする。

```
hoge = [n] => x = n + 2 . y = x + x
```

#式
完全なif式
`Result Bool a`型を返す

```
condition = true
result : Result Bool String = if (condition) "trueCond" else "falseCond"
println result value //"trueCond"
println result cond  //true
```

elseを省略したif式
返す型は同じだが`Bool`は常にtrueとなる
```
condition = true
result = if (condition) "trueCond"
println 
```

switch式
`Result MatchedCase a`型を返す

```
.
result : Result MatchedCase String = 
switch num % 2
0 => "EvenNumber"
1 => "OddNumber"
.
```	


#今のところ決っているその他の事
##評価戦略について
・先行評価が基本の評価戦略  
・無限配列が今のところ遅延評価  
・ネストは1段階のみ  
・配列はリテラルのみ。式を扱いたい場合はリストを使う
