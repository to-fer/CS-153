.class public TypeScriptProgram
.super java/lang/Object

.field private static curr_fib_count F
.field private static curr_fib_num F
.field private static first_fibo F
.field private static limit F
.field private static second_fibo F
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
      putstatic TypeScriptProgram/first_fibo F
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The first fibonacci number is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/first_fibo F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 1.0
      putstatic TypeScriptProgram/second_fibo F
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "The second fibonacci number is: "
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/second_fibo F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 100.0
      putstatic TypeScriptProgram/limit F
      ldc 2.0
      putstatic TypeScriptProgram/curr_fib_count F
      ldc 0.0
      putstatic TypeScriptProgram/curr_fib_num F
      getstatic TypeScriptProgram/first_fibo F ;assingment of identifier
      getstatic TypeScriptProgram/second_fibo F ;assingment of identifier
      fadd
      putstatic TypeScriptProgram/second_fibo F
      getstatic TypeScriptProgram/limit F ;assingment of identifier
      getstatic TypeScriptProgram/limit F ;assingment of identifier
      fadd
      putstatic TypeScriptProgram/z F
