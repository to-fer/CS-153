# Assignment 7 Requirements

### Generate parse trees with JJTree
Expand upon your grammar and parser from Assignment #6. Create a .jjt file and use
JJTree to generate a parser and an AST (parse tree). Print the parse tree in the XML format that we used for our Pascal interpreter. Your grammar must now include variable declarations. Integrate the symbol table routines from our Pascal interpreter with your generated parser and print a crossreference
listing of all the names in your source program. If you are already parsing procedures and functions (or their equivalents in your source
language), then there should be a separate symbol table and parse tree for each routine and for the main program, as we had done for our Pascal interpreter.
**Your parser must do error recovery**. Minimal error recovery is skipping to the end of the current line, but you ought to be able to do better than that.
Print the cross-reference table and the parse tree even if the source program contained syntax errors.
