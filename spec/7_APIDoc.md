#Karaffe APIDoc


これらの関数は標準であらかじめ自動で宣言され、importなどの準備無しで使用出来ます。  

表にある**変換**の項目は、関数を使用することで返り値の型が*変換される(可能性も含む)*場合に記述されます。  
`変換元の型名` -> `変換される可能性のある型名1`...    
変換が発生しない場合は-が記述されています。記述されていても結果によっては変換が発生しない場合もあります。  
変換される場合でも、変換後の型は宣言されている型のType Alias内での動作なのでコンパイルや実行には影響を与えません。  
例えば、`/`関数で`Int`型が`Float`に変換される以下の場合、表面上は`Num`で処理されるため  
問題なく動作します。  

    x : Num = 10
    y : Num = 3
    value = x / y 
    println typeName x     //Num
    println typeName y     //Num
    println typeName value //Num
    println value          //3.33333333333

`println value`で3.33333..が出力されているのは、`value = x / y`の演算時に  
内部で`Int`型から`Float`型へ変換されたためです。  
**実行中に切り上げたり、切り捨てたり、四捨五入が行われることはありませんが、  
宣言された範囲で型の変換が行われる場合があります**  
`/`演算子は`Float`型へ変換される可能性がある演算子です。  
上記の仕様のため、以下のコードはコンパイルエラーとなります。

    //引数の型がIntであることに注意
    global printInt : Int to Void = [value] to println value
   
    x : Num = 10
    y : Num = 3
    result = x / y
    println
    printInt result //コンパイルエラー。valueはFloatとなるため。FloatからIntへの暗黙変換は不可。
    printInt intValueFrom result //3
    
    x : Num = 10
    y : Num = 2
    value = x / y
    printInt value //変換が発生しないため、コンパイル・実行可能
    
何らかのデータを実行時に読み込む場合に、型のチェックなしで操作を行う場合はコンパイルエラーとなります。  
