.class public TypeScriptProgram
.super java/lang/Object

.field private static a F
.field private static b F

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

      ldc 0.0
      putstatic TypeScriptProgram/a F                                     ;pop value: assingment_node
      ldc 1.0
      putstatic TypeScriptProgram/b F                                     ;pop value: assingment_node
      getstatic TypeScriptProgram/a F                                     ;identifier
       fstore_0
      getstatic TypeScriptProgram/b F                                     ;identifier
       fstore_1
       fload_0
       fload_1
fcmpl 
iflt Label1
       goto Label2
Label1:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc 1.0
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label2:

    return

.limit locals 100
.limit stack 16
.end method
