.class public TypeScriptProgram
.super java/lang/Object

.field private static curr_fib_count F
.field private static curr_fib_num F
.field private static first_fibo F
.field private static limit F
.field private static second_fibo F
.field private static this_fibo F
.field private static z F

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

      ldc 0.0
      putstatic TypeScriptProgram/first_fibo F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The first fibonacci number is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/first_fibo F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 1.0
      putstatic TypeScriptProgram/second_fibo F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The second fibonacci number is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/second_fibo F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 100.0
      putstatic TypeScriptProgram/limit F                                     ;pop value: assingment_node
      ldc 2.0
      putstatic TypeScriptProgram/curr_fib_count F                                     ;pop value: assingment_node
      ldc 0.0
      putstatic TypeScriptProgram/curr_fib_num F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/limit F                                     ;identifier
      getstatic TypeScriptProgram/limit F                                     ;identifier
      fadd
      putstatic TypeScriptProgram/z F                                     ;pop value: assingment_node
loop: 
      getstatic TypeScriptProgram/curr_fib_count F                                     ;identifier
       fstore_0
      getstatic TypeScriptProgram/limit F                                     ;identifier
       fstore_1
       fload_0
       fload_1
fcmpl 
iflt Label1
goto Empty1
Label1:
      getstatic TypeScriptProgram/first_fibo F                                     ;identifier
      getstatic TypeScriptProgram/second_fibo F                                     ;identifier
      fadd
      putstatic TypeScriptProgram/this_fibo F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/second_fibo F                                     ;identifier
      putstatic TypeScriptProgram/first_fibo F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/this_fibo F                                     ;identifier
      putstatic TypeScriptProgram/second_fibo F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The next fibonacci number is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/this_fibo F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/curr_fib_count F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/curr_fib_count F                                     ;pop value: assingment_node
goto loop
Empty1:

    return

.limit locals 100
.limit stack 16
.end method
