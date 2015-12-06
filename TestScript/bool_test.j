.class public TypeScriptProgram
.super java/lang/Object

.field private static t Ljava/lang/String;

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

      ldc "heeelo"
      putstatic TypeScriptProgram/t Ljava/lang/String;                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/t Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "string print works"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V

    return

.limit locals 100
.limit stack 16
.end method
