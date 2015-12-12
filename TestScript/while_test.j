.class public while_test
.super java/lang/Object

.field private static x F

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

loop1: 
      ldc 2.0
       fstore_0
      ldc 1.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifgt Label1
goto Empty1
Label1:
      ldc 1.0
      putstatic while_test/x F                                     ;pop value: assingment_node
goto loop1
Empty1:

    return

.limit locals 100
.limit stack 16
.end method
