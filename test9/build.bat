@echo off
echo Building Java project...

REM Create output directory
if not exist target\classes mkdir target\classes

REM Find all Java source files
dir /s /b src\main\java\*.java > sources.txt

REM Compile Java files
echo Compiling Java source files...
javac -d target\classes @sources.txt

REM Clean up
del sources.txt

echo Build complete! Compiled classes are in target\classes\