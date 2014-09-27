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

##if式
if Expression statement1  
if Expression statement1 else statement2  

最初にExpressionが評価されます  
Expressionで演算子は使用できず、ただひとつの変数または関数のみが使用できます。  
Expressionでは`Boolean`型または`Optional`型を返す関数または変数が使用できます。  
評価結果が`True`の場合statement1が実行されます  
評価結果が`False`の場合かつstatement2が存在する場合、statement2が実行されます。  
Expressionが`Optional`型の場合、アンラップを試みます。  
アンラップ成功した場合、statement1が実行され、if式を終了します。  
アンラップ失敗した場合、かつstatement2が存在する場合、statement2が実行されます。  
アンラップ失敗した場合、かつstatement2が存在しない場合、if式を終了します。  
statementが返り値を持つ場合、返してif式の実行を終了します。  
statementが返り値を持たない場合、Expessionの評価結果を返してif式を終了します。  

##unless式
unless Expression statement1  
unless Expression statement2 else statement2

Expressionにnot演算子を付与した状態で評価を行います。評価前後の動作はif式と同様です。  

##case match式
仕様策定中
    
##コメント
`/*`, `*/`で囲むと囲んだ部分全てがコメントになります。  
仕様策定中
