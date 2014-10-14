#![Logo](logo/Karaffe_Logo.png) Karaffe Programming Language
「メモ帳で書けるとにかくシンプルで現代的な機能を持つ言語」を目指しています。  
**ほとんど未実装です。**

#コンパイルと実行
`krfc`コマンドでコンパイルを行い、`krfrun`コマンドで実行します。
```
% ls
HelloWorld.krf
% cat HelloWorld.krf
println "Hello World!"
% krfc HelloWorld.krf -o HelloWorld.okrf
% ls
HelloWorld.okrf HelloWorld.krf
% krfrun HelloWorld.okrf
Hello World!
```

#Karaffe 言語仕様(書き直し中)
[はじめに](spec/Intro.md)  
[リテラル](spec/Literals.md)  
[文法](spec/Grammer.md)  
[宣言とアクセス](spec/4_Dcl.md)  
[宣言とアクセスnew](spec/Declaration.md)  
[宣言済みの型](spec/6_Types.md)  
[ユニットテスト](spec/8_Testing.md)  
[互換性ポリシー](spec/9_Compatibility.md)  
[その他](spec/10_Other.md)  
[変更履歴](ReleaseNote.md)
#Karaffeロゴについて

##配色

| 配置 | 色 | 
| --- | ---|
|枠線|`#36251e`
|左|`#7b5544`  
|右| `#4c352a`  
|中央|`#855d49`  
