#notepad-java(仮)
notepad-langのJava実装です。メモ帳やプラグイン無しのEmacsやVimでも書けるくらい  
シンプルでパワフルな言語を目指しています。

特徴としては以下の通りです。  

- インデントなし(空行区切りのスコープ)
- 静的型付け
- 補完やシンタックスハイライトが無い事を最大限に考慮した文法
 

#Hello World

    println "Hello world!"

#notepad-java 言語仕様

#型
##定義
<sub>opt</sub>は省略可能なトークンであることを表します。    
...は1回以上の繰り返しが可能であることを表します。

##`Num`

    n = 1
    result = n isPositive
    println result //True

####Subtype
`Positive`,`Negative`,`Zero`,`Int`,`Float`

    p = 1     //Positive
    n = -1    //Negative
    z = 0     //Zero
    i = 10    //Int
    f = 1.0   //Float
    
#####注意:`Float`は他の一般的な言語の`Double`と同等の精度を持ちます。なので`Double`型は存在しません。気持ち悪く感じる場合は、少し後に説明する*Alias Type*を使って別名にしましょう。*恐らく*期待通りに動作します。

##`String`

    str = "String literal"

##`Boolean`,`True`,`False`

##`Array`
*Variablename* = [ Element1 | Element2... ]

    array = [ "hoge" | "fuga" | "piyo" ]
    println array[0] //hoge
    println array    // hoge | fuga | piyo

##`Alias Type`
type *NewTypeName* = *ExistingTypeName*

    type URL = String
    
##`Function`
func *#FunctionNameLabel* Argument1<sub>opt</sub> ...  
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


##`Pair`
*VariableName* = (*Expr1*,*Expr2*)  
*Expr1*と*Expr2*は同じ型である必要はありません。  
文が渡された時にコンパイルエラーが発生します。
    
    pair = ("Key","Value")
    
    println pair       //("Key", "Value")
    println pair left  //"Key"
    println pair right //"Value"

##`Dictionary`
Dictionary(Mapとも言います)は、Pairの配列で表現されます。
渡されるPairの型は、左右それぞれで統一されてなければなりません。
ただし、左右で同じ型である必要はありません。

*VariableName* = [ *Pair1* | *Pair2* | ... ]

    dic = [ ("Hoge",1) | ("Fuga",2)]   
    dic[0] is Pair         //True 
    println dic[0]         //("Hoge", 1)
    println dic["Hoge"]    //1
    
    invalid = [ ("key", "value") | (2, 1)] //Pair type must be unified.

##`Optional`
仕様策定中
    

#文法
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
    println hoge
    hoge = ... //duplicate variable

`global`修飾子が付与された変数はファイル内全域で利用可能になります。  
変数では、全ファイルからアクセスできるようなスコープは作成出来ません。  
`jump`命令で使用する`#`で始まるラベルは標準で`global`修飾子を付与した時と同じ動作をします。  
衝突を防止するため、`global`修飾子が付与された変数は全て大文字かつスネークケースで命名することを推奨します。

`global`修飾子

    global GLOBAL_VARIABLE = "App Name"
    
    println GLOBAL_VARIABLE //App Name
    GLOBAL_VARIABLE = ... //duplicate variable


##変数の宣言
変数の宣言は以下のように行います。

*VariableName* = *VariableInitializer*

初期化は必須です。

    invalidVariable //Variable initializer is required.

    variableName = variableInitializer //OK!

##`/* comment */`,`// line comment`
プログラム中にコメントを付ける事ができます。

##`#ラベル`
`#`の直後に任意の文字列をつなげるとラベルが宣言できます。

    #labelName
    
##`to`
キーワードと識別子を連結するために使用します。

    jump to ...
    overwrite to ...

##`jump` to
`jump` to *ExistingLabelName* Arguments<sub>opt</sub>

    #labelName
    println "Hello"
    
    jump to labelName //Infinite loop
    
    #labelName arg
    println arg
    
    jump to labelName "Hello World" //Infinite loop
    
##`overwrite` to

`overwritable`修飾子とラベルを付与した行は実行中に`overwrite` toを用いることで  
その行のコード自体を書き換える事が可能です。  

    overwritable #outputStr str = "Hello World"
    println str //Hello World
    overwrite to outputStr str = "Hello notepad"
    println str //Hello notepad
    
##`check`
`check` *Expression* "ErrorMessage"  
`check`は直後の式を評価し、`True`以外が返る場合にコンパイルエラーまたは実行時エラーを発生させます。  
どちらの場合でも、エラーが発生すると"ErrorMessage"が表示されます。
次のコードは、0が渡されるとエラーが発生するコードです。コード中で0が渡されている文が存在する場合は、
コンパイルエラーが発生します。

    func #functionWithErrorCheck arg
    check arg equals Positive or Negative
    println arg
     
    functionWithErrorCheck 2 //2
    functionWithErrorCheck 0 //Compile time error
    
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

#演算子
*LeftExpression* *Operator* *RightExpression*

*Expression1* *Operator*  
*Expression2*

*Expression1*  
*Operator* *Expression2*

##`=`
他の一般的な言語と同じく、変数への代入や変数の初期化に用いることができます。  
`=`を使用する際には変数名と式の間に半角のスペース` `を挿入することが推奨されます。

##算術演算子
##`+`,`-`,`*`,`\`,`%`

    println 1 + 2 //3
    println 2 - 1 //1
    println 3 * 3 //9
    println 3 / 2 //1.5
    println 5 % 3 //2

##比較演算子

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

##`equals`

    
##型演算子

    p = 1     //Positive or Int or Num
    n = -1    //Negative or Int or Num
    z = 0     //Zero or Int or Num
    i = 10    //Int or Positive or Num
    f = 1.0   //Float or Positive or Num

演算子で接続される型の集まりの名前は型リストとします。  
例えば、上の`p`に割り当てられている型は`Positive`と`Int`と`Num`ですが、  
この3つの型を総称して型リストと呼び、型同士は`or`演算子によって接続されています。

元の型と異なる型へのキャストは、この演算子で推論される範囲内のみ可能です。  
`Int`として受け取った後に演算によって(例えば割り算)`Float`へ変化する場合、  
動的に型が`Num`の範囲内で暗黙的にキャストされます。
誤差が生じにくくなりますが、返り値の型の不整合などに注意してください。

    num = 10 //Positive or Int or Num
    
#標準API
`println`などは、標準APIとしてあらかじめ宣言され、どこでも、準備なしで使用できます。  

標準で利用出来るAPI  

| API側の宣言 | 動作 |
|---|---|
|`func #println arg`| 渡された引数の内容を表示します |
