.class public TypeScriptProgram
.super java/lang/Object

.field private static t F


.method public doStuff()V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "hello world"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 4.0
      ldc 5.0
      fadd
      ldc 3.0
      fadd
      putstatic TypeScriptProgram/t F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "printing inside the function value t"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/t F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
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

       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "hello world"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 4.0
      ldc 5.0
      fadd
      ldc 3.0
      fadd
      putstatic TypeScriptProgram/t F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "printing inside the function value t"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/t F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      new TypeScriptProgram
      dup
      invokespecial TypeScriptProgram/<init>()V
      invokevirtual TypeScriptProgram/doStuff()V
      new TypeScriptProgram
      dup
      invokespecial TypeScriptProgram/<init>()V
      invokevirtual TypeScriptProgram/doStuff()V
      new TypeScriptProgram
      dup
      invokespecial TypeScriptProgram/<init>()V
      invokevirtual TypeScriptProgram/doStuff()V
      new TypeScriptProgram
      dup
      invokespecial TypeScriptProgram/<init>()V
      invokevirtual TypeScriptProgram/doStuff()V

    return

.limit locals 100
.limit stack 16
.end method
