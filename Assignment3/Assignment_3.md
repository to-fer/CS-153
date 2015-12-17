# Assignment 3 Requirements 

## Requirements 

Modify the Pascal parser code from Chapter 6 and the interpreter code from Chapter 8
to add the ability to parse and execute **set expressions** and **set assignment
statements**. For this assignment, the base type of your sets (the data type of the
members of the sets) will be small integers from 0 through 50.


First **modify the Pascal parser** to build parse trees for set expressions. You will need to
define new node types and design new tree structures to represent set values and set
expressions. Then **modify the executor routines** to interpret set expression and
assignment statements by walking your parse tree.



You choose how to implement the Pascal set values during run time.
Tip: Examine the Java set classes.
Be aware of the following features of Pascal sets:
* The members of a set are unordered.
The set [1, 2, 3, 4] is the same as the set [3, 1, 4, 2].
* The members of a set are unique.
The set union [1, 2, 3, 4] + [3, 4, 5, 6] produces the set [1, 2, 3, 4, 5, 6].
* The .. token specifies a range of values in a set.
[1, 3, 6..9, 2] is the same as the set [1, 2, 3, 6, 7, 8, 9].
* You can have variables and expressions in a set value.
[3, k, 5, 3*m, n] if the value of each expression is 0 through 50.
(You should flag a set element value that is out of range as a runtime error.)


## Sample Program 

    BEGIN
         low := 15;
         mid := 45;
         high := 50;
         evens := [0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20];
         odds := [1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21];
         primes := [2, 3, 5, 7, 11, 13, 17, 19, 23, 29];
         teens := [13..19];
         s1 := evens*odds;
         s2 := evens - teens + [high, mid..47, 2*low];
         s3 := evens*primes + teens;
         s4 := s3 - odds;
         s5 := evens + odds + primes + teens;
         s6 := (primes - teens)*odds;
         b1 := odds - primes = [15, 9, 21, 1];
         b2 := teens <= evens + odds;
         b3 := primes >= teens;
         b4 := odds - teens*primes <> [21, 7, 9, 1, 11, 5, 15, 3];
         b5 := 15 IN teens - primes;
         s7 := [];
         i := 1;
         WHILE i <= 50 DO BEGIN
         s7 := s7 + [i];
         i := 2*i;
         IF 8 IN s7 THEN s7 := s7 + [10]
         ELSE s7 := s7 - [4]
         END
    END.