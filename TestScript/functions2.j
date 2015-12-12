.class public functions2
.super java/lang/Object

.field private static t F


.method public static doStuff()V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "in the function"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic functions2/t F                                     ;identifier
       fstore_0
      ldc 20.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifge Label1
       goto Label2
Label1:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "function finished"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       goto Label3
Label2:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "recursively t ="
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic functions2/t F                                     ;identifier
      ldc 1.0
      fadd
      putstatic functions2/t F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     functions2/t F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      invokestatic functions2/doStuff()V
Label3:
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

      ldc 0.0
      putstatic functions2/t F                                     ;pop value: assingment_node
      invokestatic functions2/doStuff()V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "function recursion expect t==20"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     functions2/t F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic functions2/t F                                     ;identifier
       fstore_0
      ldc 20.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifeq Label1
       goto Label2
Label1:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "t = 20 test passed"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       goto Label3
Label2:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "t ="
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     functions2/t F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "test failed"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label3:

    return

.limit locals 100
.limit stack 16
.end method
