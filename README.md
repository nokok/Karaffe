#![Logo](logo/Karaffe_Logo.png) Karaffe Programming Language
---
「メモ帳で書けるとにかくシンプルで現代的な機能を持つ言語」を目指しています。  

#検査と実行
`krfc`コマンドでコンパイルを行います。
```
% ls
HelloWorld._np
% krfc HelloWorld._np -o HelloWorld.out
% ls
HelloWorld._np HelloWorld
% krfrun HelloWorld.out
Hello World!
```

#Karaffe 言語仕様(中途半端)
---
[はじめに](spec/1_Introduction.md)  
[リテラル](spec/2_Literals.md)  
[構文](spec/3_Syntax.md)  
[宣言とアクセス](spec/4_Dcl.md)  
[文法](spec/5_Grammer.md)  
[宣言済みの型](spec/6_Types.md)  
[APIDoc](spec/7_APIDoc.md)  
[ユニットテスト](spec/8_Testing.md)  
[互換性ポリシー](spec/9_Compatibility.md)  
[変更履歴](ReleaseNote.md)
#Karaffeロゴについて

##配色

| 配置 | 色 | 
| --- | ---|
|枠線|`#36251e`
|左|`#7b5544`  
|右| `#4c352a`  
|中央|`#855d49`  
