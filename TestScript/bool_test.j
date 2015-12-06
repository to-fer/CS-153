.class public TypeScriptProgram
.super java/lang/Object

.field private static q F
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
      ldc 3.0
      ldc 4.0
      fadd
      ldc 3.0
      fadd
      putstatic TypeScriptProgram/q F                                     ;pop value: assingment_node
      ldc 3.0
      ldc 4.0
      fadd
      ldc 1.0
      fadd
      ldc 1.0
      ldc 3.0
      fadd
fcmpl 
ifge Label1
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "yes"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
goto Empty1
Label1:
Empty1:

    return

.limit locals 100
.limit stack 16
.end method
