#文法

##範囲式
`Int`型または`Float`型配列の糖衣構文です。  

Int `..` Int<sub>opt</sub> :Step<sub>opt</sub>  
左辺のInt値から右辺のInt値までの配列を生成します。  

Int `.<` Int<sub>opt</sub> :Step<sub>opt</sub>  
右辺の数値を含まない配列を生成します  

Stepは省略した場合1となります。  
右辺を省略した場合、無限長の配列になります。  
この場合にStepは指定できません。1ずつ増えていきます。  

#### コード例
```
println 1..5 //[ 1 2 3 4 5 ]
println 1.<5 //[ 1 2 3 4 ]
println 0..10 3 //[0 3 6 9]
println 0.5..5.0 0.5 [0.5 1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0]
```
##switch式
sw RenameVariables<sub>opt</sub>  
Expression = statement  
...  
\_<sub>opt</sub> = defaultstmt

switch式は渡された引数によって処理を分岐します。  
switch式は1つの変数のみから分岐が可能です。  
switch式は何らかの関数を返します。  
この変数は`$0`が暗黙的に宣言され、statement及びdefaultstmtで利用できます。  
Expressionの部分には型、式、数値、文字列、`Boolean`型を返す関数が利用できます。  
RenameVariablesで`$0`〜`$n`に別名を付与することが可能です。  

####コード例

渡された引数の型によって表示を変更する例
```
type Zero = Num
check equals 0

global judge = sw
isZero  = println "Zero"
isInt   = println "Int"
isFloat = println "Float"
_       = println "Other"

judge 400    //Int
judge 0.3    //Float
judge 0      //Zero
judge "hoge" //Other
```
`isType`は`Any to Boolean`型の関数です。`println`は`Any to Void`型です。  
`Any`を渡して最終的に`Void`となるので`judge`関数は`Any to Void`型で宣言されます。  

2進数の文字列を操作する例
```
global parse = sw
"0" = 0
"1" = 1

binaryAry : Int[] = "0010011101010101" map:parse 
```
※この例は`String`型の標準関数である`toInt`が利用できます。  
`parse`関数は、`String to Int`として宣言されます。  

##コメント
`/*`, `*/`で囲むと囲んだ部分全てがコメントになります。  
仕様策定中
