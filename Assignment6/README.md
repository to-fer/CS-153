# Assignment 6 Requirements

### Generate a parser with JavaCC
Design a grammar for your source language and write its productions rules using EBNF. Use the EBNF as a guide to create a .jj file that includes the production rules and any required token specifications. Use JavaCC to generate a working parser and scanner for your source language.

### Your source language
You may choose a source language for your team project to compile and execute:
* It must be a procedural language.
* It can be an existing language or subset thereof.
* If you choose Pascal, then your completed project must include features not in
the WCI book, such as statement labels and GOTO statements, WITH statements,
set types and expressions, pointer types and expressions, etc.
* You can invent a new language, as long as it’s procedural
(no Lisp or Lisp-like languages).
* By the end of the semester, you must implement enough of your source
language to be able to execute nontrivial programs written in it.
2
Keep your source language and its grammar simple for this assignment! This is a
snapshot of your early thinking about language design. You can change or add more
features later. Suggested order of implementation for your grammar and compiler:
* Expressions with numeric constants and scalar variables (no type checking, no
arrays or records yet)
* Assignment statements
* Control statements
* Variable declarations (no type definitions yet)
* Procedure and function declarations
* Procedure and function calls
* Type definitions

Do at least the first three for this assignment. You don’t have to do all your controlstatements; you can add more later.

Use syntactic actions to print out informative messages as your parser recognizes the major source language constructs (such as statements and expressions) in the source program. 

Your parser does not need to produce fancy output. As you incrementally implement more of your language, write small test programs to
ensure that each new feature works. To verify that you didn’t break anything, do
regression testing by re-running the test programs you had written earlier.