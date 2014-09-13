#notepad-java(仮)
notepad-langのJava実装です。メモ帳やプラグイン無しのEmacsやVimでも書けるくらい  
シンプルでパワフルな言語を目指しています。

特徴としては以下の通りです。  

- インデントなし(空行区切りのスコープ)
- 静的型付け + **強力な**コンパイル時エラーチェック
- 補完やシンタックスハイライト、オートインデントが無いエディタを最大限に考慮した文法

#Hello World

    println "Hello world!"
    
#フィボナッチ数

    func #fib n
    check n equals Num
    if n equals 0
    return n
    else if n equals 1
    return n
    else
    return fib n - 1 + fib n - 2

    println fib 5

※インデントを付与した以下のコードでも同様の動作をします。

    func #fib n
      check n equals Num
      if n equals 0
        return n
      else if n equals 1
        return 1
      else
        return fib n - 1 + fib n - 2

    println fib 5

#notepad-java 言語仕様

#型
##定義
<sub>opt</sub>は省略可能なトークンであることを表します。  
...は直前の同じ式または文などが1回以上繰り返し可能であることを表します。

##`Num`

    n = 1
    result = n isPositive
    println result //True

####Type Alias
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

##`Type Alias`
Type Aliasを使用すると、既存の型に別名を付与することが出来ます。  
また、作成した新しい型に対して`check`文を使用すると、  
(可能な場合)コンパイル時チェックが出来ます。  
`check`文で用いることの出来るメソッドは元の型*ExistingType*で利用可能なメソッドに限られ、  
変数は暗黙的に宣言されます。  

type *NewTypeName* = *ExistingTypeName*  
check *Expression*
...

    type URL = String
    check startsWith "https" or "http"

    url = "http://google.com"
    invalidURL = "Hoge" //コンパイルエラーです。なぜならば、文字列"Hoge"が"http"や"https"から始まっていません。
    
##`Function`
`Function`を用いることで、特定の処理をまとめておくことが出来ます。  
性質上、ラベルに対して暗黙的に`global`が付与されます。  
func *#FunctionNameLabel* ArgumentName1<sub>opt</sub> ...  
statement

    func #usefulFunction
    println "Function called."

    usefulFunction                          //Function called.
    
    func #functionWithArg arg
    println arg
    
    functionWithArg "Hello World"           //Hello World  
    
    var func #function
    println "hello"
    
    func                                    //Hello
    
    func #functionArguments f
    f "Hoge"
    
    functionArguments functionWithArg       //Hoge


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
渡されるPairの型は、左右それぞれで統一されてなければなりませんが、  
左右で同じ型である必要はありません。  

*VariableName* = [ *Pair1* | *Pair2* | ... ]

    dic = [ ("Hoge",1) | ("Fuga",2)]   
    dic[0] is Pair         //True 
    println dic[0]         //("Hoge", 1)
    println dic["Hoge"]    //1
    
    invalid = [ ("key", "value") | (2, 1)] //Pairの型は同じでなければなりません。(String,String)を期待していますが、(Num,Num)が渡されました。

##`Optional`
仕様策定中
    
##`void`
これは型ではありませんが、返り値が無い事を表現する為に用いられます。

#文法
##スコープの規則
スコープは関数ごと、または空行を挟むことで区切られます。  
同じ関数内でも途中で空行を挟むと別スコープになります。  
空行を複数個追加してもスコープは変わりません。  

OK

    str = "Hello World"
    println str                            //Hello World

    str = "Other Hello world"
    println str                            //Other Hello world

NG

    str = "Hello World"

    println str                            //見つかりません: str

    hoge = ...
    println hoge
    hoge = ...                             //変数が重複しています: hoge

`global`修飾子が付与された変数はファイル内全域で利用可能になります。  
変数では、全ファイルからアクセスできるようなスコープは作成出来ません。  
`jump`命令で使用する`#`で始まるラベルは標準で`global`修飾子を付与した時と同じ動作をします。  
衝突を防止するため、`global`修飾子が付与された変数は全て大文字かつスネークケースで命名することを推奨します。

`global`修飾子

    global GLOBAL_VARIABLE = "App Name"
    
    println GLOBAL_VARIABLE    //App Name
    GLOBAL_VARIABLE = ...      //変数が重複しています: global GLOBAL_VARIABLE


##変数の宣言
変数の宣言は以下のように行います。

*VariableName* = *VariableInitializer*
*VariableName* : *ExistingTypeName* = *VariableInitializer*

初期化は必須です。

    invalidVariable //エラー: 変数の初期化は必須です。
    invalid

    variableName = variableInitializer //OK!
    
    type URL = String
    
    variableWithType : URL = "http://google.com"
    

##`/* comment */`,`// line comment`

プログラム中にコメントを付ける事ができます。

##`#ラベル`
`#`の直後に任意の文字列をつなげるとラベルが宣言できます。

    #labelName
    
##`to`
キーワードと識別子を連結するために使用します。何らかの動作を与えることはありません。

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
    functionWithErrorCheck 0 //エラー: check文の条件が成立しないパラメータです Positive or Negative
    
##`return`
return *Expression*  
*Expression*の結果を外側のスコープまたは式などに制御を移します。

#式

##`if`

if式は返り値を持ちます。if式内で実行した最後の結果が返ります。
最後に実行した式の返り値が無い場合は、評価した式の結果(`True`/`False`)がそのまま返ります。

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
    
    isTrue = True
    
    result = if isTrue "TrueCond" else "FalseCond"
    
    println result //TrueCond 
    
##`..`
右辺以下の範囲の任意の`Num`型配列を展開する式です。

*Expr1* .. *Expr2*  
*Expr1* .. *Expr2* *Step Expr*<sub>opt</sub>  
*Expr1*、*Expr2*、*Step Expr*は`Num`型である必要があります。  
*Step Expr*で増/減分を指定することが出来ます。  
省略した場合、`1`が暗黙的に指定されます。

    1..5    //[ 1 | 2 | 3 | 4 | 5 ]
    2..10 2 //[ 2 | 4 | 6 | 8 | 10 ]
    10..8   //[ 10 | 9 | 8 ]
    
##`.<`
右辺の数を含まない範囲の`Num`型配列を展開する式です。

    1.<5    //[ 1 | 2 | 3 | 4 ]
    1.<10 2 //[ 2 | 4 | 6 | 8 ]

##`loop`
loop *Expression*  
statement<sub>opt</sub>  
*Expression*は`Array`型である必要があります。そうでない場合、コンパイルエラーが発生します。
*Expression*で評価された配列は、配列の先頭から最後まで取り出されます。
取り出された要素は、通常、`_`が暗黙的に`Num`型で宣言され、利用することが出来ます。  
statementは省略することが出来ます。  
`loop`式は変数へ代入をすることで特定の回数処理を行う`Function`が利用できます。  

    loop 1..5
    println "Hello World" + _
    
    func #loopBody arg
    println arg
    
    global l = loop 1..5
    l println "Hello World" //Hello Worldが5回出力される
    
    l loopBody
    
##入れ子の式


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
    println 3 / 2 //1.5 (Num/NumはFloatへ自動キャストされる場合があります。)
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

| API側の宣言 |返り値| 動作 |
|---|---|---|
|`func #println arg`|`voidk`| 渡された引数の内容を表示します |
|`func #typeName` arg` |`String`| 渡された引数の型を文字列表現にします | 