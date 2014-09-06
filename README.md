#notepad-java
notepad-langのJava実装です。メモ帳やプラグイン無しのEmacsやVimでも書けるくらいに
シンプルでパワフルな言語を目指しています。

特徴としては以下の通りです。  

- インデントなし(空行区切りのスコープ)
- 静的型付け
- インタプリタのような実行方法

#Hello World

    println "Hello world!"

#型

##`Num`

    n = 1
    result = n isPositive
    println result // True

####Subtype
`Positive`,`Negative`,`Zero`,`Int`,`Float`

    p = 1     //Positive
    n = -1    //Negative
    z = 0     //Zero
    i = 10    //Int
    f = 1.0   //Float
    
#####注意:`Float`は他の一般的な言語の`Double`と同等の精度を持ちます。なので`Double`型は存在しません。気持ち悪く感じる場合は、少し後に説明する*Alias Type*を使って別名にしましょう

##`String`

    str = "String literal"

##`Boolean`,`True`,`False`
主に条件式での分岐で用いることが出来ます。

##`Array`
*Variablename* = [ Element1 | Element2... ]

    array = [ "hoge" | "fuga" | "piyo" ]
    println array[0] //hoge

##`Alias Type`
type *NewTypeName* = *ExistsTypeName*

    type URL = String
    
##`Function`
func *#FuncNameLabel* args
statement

    func #usefulFunction
    println "Function called."

    usefulFunction //Function called.
    
    func #functionWithArg arg
    println arg
    
    functionWithArg "Hello World" //Hello World  
    
    var func #function
    println "hello"
    
    func //Hello
    
    func #functionArguments f
    f "Hoge"
    
    functionArguments functionWithArg //Hoge


##Pair
*VariableName* = (*Expr1*,*Expr2*)

##`Dictionary`
*VariableName* = [ *Pair1* | *Pair2* | ...]

    dic = [ ("Hoge",1) | ("Fuga",2)]   
    dic[0] is Pair         //True 
    println dic[0]         //("Hoge",1)
    println dic["Hoge"]    //1

##`Optional`

    

#文法
##変数の宣言
変数の宣言は以下のように行います。

*VariableName* = *VariableInitializer*

初期化は必須です。

    varName //wrong

    variableName = variableInitializer //OK!

##スコープの規則
スコープは関数ごと、または空行を挟むことで区切られます。  
同じ関数内でも途中で空行を挟むと別スコープになります。  
空行を複数個追加してもスコープは変わりません。  

OK

    str = "Hello World"
    println str //Hello World

    str = "Other Hello world"
    println str //Other Hello world

NG

    str = "Hello World"

    println str //not found: str

    hoge = ...
    hoge = ... //duplicate variable

`global`修飾子を使用することで、ファイル内でスコープが有効になります。  
変数では、全ファイルからアクセスできるようなスコープは作成出来ません。  
`jump`命令で使用する`#`で始まるラベルは標準で`global`修飾子を付与した時と同じ動作をします。

`global`修飾子

    global GLOBAL_VARIABLE = "App Name"
    
    println GLOBAL_VARIABLE //App Name
    GLOBAL_VARIABLE = ... //duplicate variable

##`/* comment */`,`// line comment`

#式

##`if`

if式は返り値を持ちます。if式内で実行した最後の結果が返ります。
最後に実行した式の返り値が無い場合は、評価した式がそのまま返ります。

`if` *Expr* statement1 `else` statement2  

`if` *Expr*  
statement1  
`else`  
statement2  

*Expr*がBoolean以外を返す式の場合、または文である場合にコンパイルエラーが発生します。

*Expr*の評価結果が`True`の場合statement1が実行され、次の処理へ移行します。
`False`の場合statement2が実行され、次の処理へ移行します。

    hoge = True

    if hoge
    println "TrueCond"
    else
    println "FalseCond"
    
    //OR 
    
    if Expr /* do something */ else /* do something */ 
    
    isTrue = True
    
    result = if isTrue "TrueCond" else "FalseCond"
    
    println result //TrueCond 
    

##`loop`

    loop 1..5
    
##`jump`=`

    #labelName
    println "Hello"
    
    jump labelName //Infinite loop
    
    #labelName arg
    println arg
    
    jump labelName "Hello World" //Infinite loop
    
##`overwriteTo`

`overwritable`修飾子とラベルを付与した行は実行中に`overwriteTo`を用いることで  
その行のコード自体を書き換える事が可能です。  

    overwritable #outputStr str = "Hello World"
    println str //Hello World
    overwriteTo outputStr str = "Hello notepad"
    println str //Hello notepad

#演算子
##`=`
他の一般的な言語と同じく、変数への代入や変数の初期化に用いることができます。  
`=`を使用する際には変数名と式の間に半角のスペース` `を挿入することが推奨されますが、  
そのように書かなくても動きます。

*Variable*=*Expr*
  
*Variable* = *Expr*  

*Variable*=  
*Expr*  

*Variable*  
=*Expr*

以下の文法はコンパイルエラーが発生します。

*Variable*=statement

##`or`

    orResult1 = True or False //True
    orResult2 = False or False //False
    
##`and`
    
    andResult1 = True and True //True
    andResult2 = True and False //False
    andResult3 = False and False //False
    
##`not`

    notResult1 = not True //False
    notResult2 = not True and False //True
    
##型演算子

    p = 1     //Positive or Int or Num
    n = -1    //Negative or Int or Num
    z = 0     //Zero or Int or Num
    i = 10    //Int or Positive or Num
    f = 1.0   //Float or Positive or Num

他の型へのキャストは、このリスト演算子で推論される範囲内のみ可能です。`Int`として受け取った後に
演算によって(例えば割り算)`Float`へ変化する場合、動的に型が`Num`の範囲内で暗黙キャストされます。
誤差が生じにくくなりますが、返り値の型の不整合などに注意してください。

