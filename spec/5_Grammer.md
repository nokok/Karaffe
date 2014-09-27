#文法

##範囲式
`Num`型配列の糖衣構文です。  

Int `..` Int<sub>opt</sub> :Step<sub>opt</sub>  
左辺のInt値から右辺のInt値までの配列を生成します。  

Int `.<` Int<sub>opt</sub> :Step<sub>opt</sub>  
右辺の数値を含まない配列を生成します  

Stepは省略した場合1となります。  
右辺を省略した場合、無限長の配列になります。  
この場合にStepは指定できません。1ずつ増えていきます。  

コード例
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
この変数は`$0`が暗黙的に宣言され、statement及びdefaultstmtで利用できます。  
Expressionの部分には型、式、数値、文字列、`Boolean`型を返す関数が置けます。  
RenameVariablesで`$0`〜`$n`に別名を付与することが可能です。  

コード例
```
global judge = sw
isZero  = println "Zero"
isInt   = println "Int"
isFloat = println "Float"
_          = println "Other"

judge 400    //Int
judge 0.3    //Float
judge 0      //Zero
judge "hoge" //Other

```
    
##コメント
`/*`, `*/`で囲むと囲んだ部分全てがコメントになります。  
仕様策定中
