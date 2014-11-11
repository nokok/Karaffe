#Karaffe
特徴
・関数型言語
・副作用を許す


宣言、定義系

定数宣言
`varName = 0`

変数宣言
`seffect varName = 0`

関数定義1
```
f : Int to Int
f = x * x
```

関数定義2
```
f : x:Int to Int = x * x
```

関数リテラルによる定義
```
f = [num] => x * x
```

パターン関数定義
```
fizzBuzz : Int to String
fizzBuzz = 15 => "FizzBuzz" 
fizzBuzz = 3 => "Fizz"
fizzBuzz = 5 => "Buzz"
fizzBuzz = _ => _
//この関数は以下のコードと同一の振る舞いをするが厳密には異なる。
//上のコードは4つの関数が定義されているが、下のコードは1つの関数で内部で分岐させている。
fizzBuzz : Int to String =
. switch num
. 15 = "FizzBuzz"
. 3 = "Fizz"
. 5 = "Buzz"
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

型の別名を定義(代入互換性を失う)
```
type NewName = ExistingTypeName
```

型を継承(代入互換性を持つが元の型が持つ関数はオーバーロード不可)
```
type NewName < ExistingTypeName
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