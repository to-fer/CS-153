.class public TypeScriptProgram
.super java/lang/Object

.field private static i F
.field private static square_sum F
.field private static sum F
.field private static t F


.method public tester()V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "hello world"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 4.0
      ldc 5.0
      fadd
      ldc 3.0
      fadd
      putstatic TypeScriptProgram/t F                                     ;pop value: assingment_node
