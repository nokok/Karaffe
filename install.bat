@echo off
./gradlew installDist
 robocopy .\build\install\Karaffe-compiler\ D:\home\noko\dotfiles\ /MIR 
