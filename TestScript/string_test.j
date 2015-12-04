.class public TypeScriptProgram
.super java/lang/Object

.field private static str Ljava/lang/String;

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

      ldc "hello world"
      putstatic TypeScriptProgram/str Ljava/lang/String;
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/str Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "hello world"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V

    return

.limit locals 100
.limit stack 16
.end method
