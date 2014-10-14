#リテラル

##リテラル
```
literal = boolean_lit |
          dec_number_lit |
          hex_number_lit |
          float_lit |
          char_lit |
          string_lit | 
          type_lit | 
          "undefined"
          
```

##論理型
```
boolean_lit = "True" | "False" | "true" | "false"
```
真と偽を表現するリテラルです。  
関連する型: `Bool`

##数値
```
num_literal = dec_number_lit |
              hex_number_lit |
              float_lit
```

##整数(10進)
```
dec_number_lit = zero | (["-"] non_zero {zero | non_zero})
```

 * `1`
 * `-1`
 * `7895789231`

以下のリテラルは不正です

 * `.3`
 * `-.3`
 * `08`
 * `00`

関連する型: `Int`

##整数(16進)
```
hex_number_lit = "0x" hex_digit {hex_digit}
```

 * `0x9043`
 * `0x3f`
 * `0XFFFF`

以下のリテラルは不正です

 * `0x`

関連する型: `Int`

##実数
```
float_lit = ["-"] {zero | non_zero} "." digit {digit} ["e" | "E"] ["-"] non_zero {non_zero} 
```

 * `1.0`
 * `0.3`
 * `-0.1`
 * `5E10`
 * `1.7e14`
 * `-2.5e10`

関連する型: `Float`

##文字
```
char_lit        = "'" (printable_char | escape_sequence) "'"
escape_sequence = "\" ( "n" | "t" | "r" | """ | "\" | "'")
```

* `'0'`
* `'a'`
* `£`
* `ç`
* `'\n'`
* `あ`

####エスケープシーケンス
`escape_sequence`は次のように認識されます

`\n` = `\u000A` 改行  
`\t` = `\u0009` タブ  
`\r` = `\u000d` キャリッジリターン  
`\"` = `\u0022` "  
`\\` = `\u005c` \  
`\'` = `\u0027` '


##文字列
```
string_lit     = """ {string_element} """
string_element = printable_char_no_double_quote | escape_sequence 
```

* `"foo"`
* `"foo\nbar\n"`
* `"$100"`

##配列
```
array_lit     = "[" { array_element } "]"
array_element = literal | type_name | identifier
```

* `[ 1 2 3 4 5 ]`
* `[ 1 + 2 2 + 3 3 + 4]` ※1
* `[ Int Float String ]`
* `[ varName1 varName2 varName3 ]`  

※1 コンパイル時に`[ 3 5 7 ]`の配列になります。  


##undefined
undefinedは未定義動作であることを示す特殊なオブジェクトです。  
undefinedは全ての型に代入可能です。  
インターフェースの定義、未確定の動作の仮コードの記述などに使用できます。  
undefinedに対する操作は全てundefinedとなります。  
コンパイル時にundefinedに対して操作をすると警告が生成されます。  
型を省略した場合、`Any`で宣言されます。  

##型
```
type_lit = "Type" "[" type_name "]"
```

* `Type[Int]`
* `Type[]`