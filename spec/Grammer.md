#文法

##範囲式
```
range_expr = omitted_simple_range_expr |
             simple_range_expr |
             closed_range_expr | 
             simple_range_with_step_expr |
             closed_range_with_step_expr 

ommited_simple_range_expr   = num_literal ".."
simple_range_expr           = omitted_simple_range_expr num_literal
closed_range_expr           = num_literal ".<" num_literal
simple_range_with_step_expr = simple_range_expr num_literal
closed_range_with_step_epxr = closed_range_expr num_literal
```

* `0..`
* `1..5`
* `1.<10`
* `1..10 2`
* `1.<10 2`
* `0.1..1.0 0.1`

`ommited_simple_range_expr`は左辺値以上の無限長の配列を増分1で生成します。  
`simple_range_expr`は左辺値以上、右辺値未満の範囲の配列を増分1で生成します。  
`closed_range_expr`は左辺値以上、右辺値以下の範囲の配列を増分1で生成します。  
`simple_range_with_step_expr`は左辺値以上、右辺値未満の配列を指定した増分式で生成します。  
`closed_range_with_step_expr`は左辺値以上、右辺値以下の配列を指定した増分式で生成します。  

##switch式
```
switch_expr = switch_dec {case} [otherwise]
switch_dec  = "switch" identifier newline
case        = "case" case_expr "=" (expr|literal) newline
otherwise   = "default" "=" expr newline

case_expr = return_boolean_expr | 
            existing_type_name |
            literal
```

```
func : Int to String = switch n
	n > 10  = "\(n) > 10"
	n == 10 = "\(n) = 10"
	n < 10  = "\(n) < 10"
println func 20 //20 > 10
println func 10 //10 = 10
println func 5  //5 < 10

```

switch式の型は`a to b`で宣言され、型推論が有効です。

#文
```
statement = (expr newline) | line_comment | multi_line_comment
```

##コメント
以下の2形式がコメントとして利用可能です。コメントは入れ子に出来ません。  

####行コメント
`//`から行末まで  
コンパイル時に`newline`へ置換されます

####範囲コメント
`/*`から`*/`まで
改行されない範囲コメントの場合、`space`へ置換されます。  
1つ以上の改行を含む範囲コメントの場合、1つの`newline`へ置換されます。

#識別子
```
identifier  = type_id | variable_id 

type_id     = upper_alphabet {alphabet}
variable_id = lower_alphabet {alphabet | zero | non_zero | "_"}
type_param  = lower_alphabet

```

型名は大文字から始め、変数名は小文字から始めなければなりません。  
型パラメータは小文字の英字1文字でなければなりません。  

以下の名前は予約されています。

| 予約語 | 使用箇所 |
|---|---|
| check | 式の検査 |
| false | Bool型
| False | Bool型 |
| global | ファイル内スコープ宣言 | 
| native | Javaネイティブ実装 |
| seffect | 再代入を許可する宣言 | 
| swich | switch式 | 
| type | Alias Typeなど |
| true | Bool型 |
| True | Bool型 |
| unary | 前置
| undefined | 未定義 |

