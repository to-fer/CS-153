.class public TypeScriptProgram
.super java/lang/Object

.field private static y Z
.field private static z Z

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

ldc 1
      putstatic TypeScriptProgram/z Z
      getstatic TypeScriptProgram/z Z ;assingment of identifier
      putstatic TypeScriptProgram/y Z
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/z Z
      invokestatic  java/lang/String.valueOf(Z)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/y Z
      invokestatic  java/lang/String.valueOf(Z)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "true"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "false"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V

    return

.limit locals 100
.limit stack 16
.end method
