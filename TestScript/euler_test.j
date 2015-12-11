.class public TypeScriptProgram
.super java/lang/Object

.field private static expected_result F
.field private static i F
.field private static result F
.field private static square_of_sum F
.field private static square_sum F
.field private static sum F
.field private static t F
.field private static temp F


.method public static tester()V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "hello world"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 4.0
      ldc 5.0
      fadd
      ldc 3.0
      fadd
      putstatic TypeScriptProgram/t F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/t F                                     ;identifier
return
.limit locals 32
.limit stack 40
.end method


.method public static sum_square_diff()V
      ldc 1.0
      putstatic TypeScriptProgram/i F                                     ;pop value: assingment_node
      ldc 0.0
      putstatic TypeScriptProgram/sum F                                     ;pop value: assingment_node
      ldc 0.0
      putstatic TypeScriptProgram/square_sum F                                     ;pop value: assingment_node
loop1: 
      getstatic TypeScriptProgram/i F                                     ;identifier
       fstore_0
      ldc 100.0
       fstore_1
       fload_0
       fload_1
fcmpl 
ifle Label1
goto Empty1
Label1:
      getstatic TypeScriptProgram/sum F                                     ;identifier
      getstatic TypeScriptProgram/i F                                     ;identifier
      fadd
      putstatic TypeScriptProgram/sum F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/i F                                     ;identifier
      getstatic TypeScriptProgram/i F                                     ;identifier
      fmul
      putstatic TypeScriptProgram/temp F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/square_sum F                                     ;identifier
      getstatic TypeScriptProgram/temp F                                     ;identifier
      fadd
      putstatic TypeScriptProgram/square_sum F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/i F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/i F                                     ;pop value: assingment_node
goto loop1
Empty1:
      getstatic TypeScriptProgram/sum F                                     ;identifier
      getstatic TypeScriptProgram/sum F                                     ;identifier
      fmul
      putstatic TypeScriptProgram/square_of_sum F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The sum of the squares is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/square_sum F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The square of the sum is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/square_of_sum F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The difference between the sum of the squares of the first one hundred natural numbers and the square of the sum is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/square_of_sum F                                     ;identifier
      getstatic TypeScriptProgram/square_sum F                                     ;identifier
      fsub
      putstatic TypeScriptProgram/result F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/result F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 2.516415E7
      putstatic TypeScriptProgram/expected_result F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/result F                                     ;identifier
       fstore_0
      getstatic TypeScriptProgram/expected_result F                                     ;identifier
       fstore_1
       fload_0
       fload_1
fcmpg 
ifeq Label2
       goto Label3
Label2:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The result is correct"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       goto Label4
Label3:
Label4:
      ldc 0.0
return
.limit locals 32
.limit stack 40
.end method

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

      invokestatic TypeScriptProgram/sum_square_diff()V

    return

.limit locals 100
.limit stack 16
.end method
