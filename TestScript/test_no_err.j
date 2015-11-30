.class public TypeScriptProgram
.super java/lang/Object

.field private static i D
.field private static j D
.field private static l B
.field private static s D
.field private static x C
.field private static y D

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

      ldc 1.0
      ldc 5.653
      dsub
      ldc 4434.0
      dadd
      putstatic TypeScriptProgram/i D
      ldc 2.0
      getstatic TypeScriptProgram/i D
      dadd
      putstatic TypeScriptProgram/j D
      ldc "hello person"
      putstatic TypeScriptProgram/x C
      getstatic TypeScriptProgram/true B
      putstatic TypeScriptProgram/l B
      ldc 1.0
      ldc 2.0
      dadd
      ldc 3.0
      dadd
      putstatic TypeScriptProgram/y D
      ldc 10.0
      ldc 5.0
      ddiv
      putstatic TypeScriptProgram/s D
      ldc 6.0
      putstatic TypeScriptProgram/y D

    return

.limit stack 16
.end method
