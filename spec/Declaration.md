#宣言

```
const_declaration    = (variable_dec | func_dec | newtype_dec)
variable_declaration = "seffect" const_declaration

variable_dec = identifier [type_dec] "=" body

func_dec     = identifier [type_list to type_expr] "=" body

newtype_dec  = "type" new_type "=" type_name

body         = (expr | literal) newline
type_list    = identifier type_dec {identifier type_dec}
type_dec     = ":" type_name
```

変数宣言

* `str = "Hello"`
* `ary : Int[] = [1 2 3 4 5]`

関数宣言

* `printInt : Int to Void = ...`
* ``