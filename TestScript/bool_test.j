.class public TypeScriptProgram
.super java/lang/Object

.field private static passed F
.field private static result F
.field private static wrong F

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

      ldc 0.0
      putstatic TypeScriptProgram/wrong F                                     ;pop value: assingment_node
      ldc 0.0
      putstatic TypeScriptProgram/passed F                                     ;pop value: assingment_node
      ldc 1.0
       fstore_0
      ldc 1.0
      ldc 3.0
      fadd
       fstore_1
       fload_0
       fload_1
fcmpl 
iflt Label1
       goto Label2
Label1:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 1 < 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label2:
      ldc 10.0
       fstore_0
      ldc 1.0
      ldc 3.0
      fadd
       fstore_1
       fload_0
       fload_1
fcmpl 
iflt Label3
       goto Label4
Label3:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "WRONG: 10 < 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/wrong F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/wrong F                                     ;pop value: assingment_node
Label4:
      ldc 10.0
       fstore_0
      ldc 1.0
      ldc 3.0
      fadd
       fstore_1
       fload_0
       fload_1
fcmpg 
ifgt Label5
       goto Label6
Label5:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 10 > 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label6:
      ldc 3.0
       fstore_0
      ldc 4.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifgt Label7
       goto Label8
Label7:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "WRONG: 3 > 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/wrong F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/wrong F                                     ;pop value: assingment_node
Label8:
      ldc 10.0
       fstore_0
      ldc 1.0
      ldc 3.0
      fadd
       fstore_1
       fload_0
       fload_1
fcmpg 
ifge Label9
       goto Label10
Label9:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 10 >= 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label10:
      ldc 4.0
       fstore_0
      ldc 4.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifge Label11
       goto Label12
Label11:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 4 >= 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label12:
      ldc 3.0
       fstore_0
      ldc 4.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifge Label13
       goto Label14
Label13:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "WRONG: 3 >= 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/wrong F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/wrong F                                     ;pop value: assingment_node
Label14:
      ldc 1.0
       fstore_0
      ldc 1.0
      ldc 3.0
      fadd
       fstore_1
       fload_0
       fload_1
fcmpl 
ifle Label15
       goto Label16
Label15:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 1 <= 4"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label16:
      ldc 4.0
       fstore_0
      ldc 3.0
       fstore_1
       fload_0
       fload_1
fcmpl 
ifle Label17
       goto Label18
Label17:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "WRONG: 4 <= 3"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/wrong F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/wrong F                                     ;pop value: assingment_node
Label18:
      ldc 0.11
       fstore_0
      ldc 0.11
       fstore_1
       fload_0
       fload_1
fcmpl 
ifle Label19
       goto Label20
Label19:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 0.11 <= 0.11"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label20:
      ldc 2.44
       fstore_0
      ldc 2.44
       fstore_1
       fload_0
       fload_1
fcmpg 
ifeq Label21
       goto Label22
Label21:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 2.44 == 2.44"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label22:
      ldc 2.0
       fstore_0
      ldc 3.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifeq Label23
       goto Label24
Label23:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "WRONG: 2.0 == 4.0"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/wrong F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/wrong F                                     ;pop value: assingment_node
Label24:
      ldc 2.44
       fstore_0
      ldc 2.44
       fstore_1
       fload_0
       fload_1
fcmpg 
ifne Label25
       goto Label26
Label25:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "WRONG: 2.44 != 2.44"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      getstatic TypeScriptProgram/wrong F                                     ;identifier
      ldc 1.0
      fadd
      putstatic TypeScriptProgram/wrong F                                     ;pop value: assingment_node
Label26:
      ldc 2.0
       fstore_0
      ldc 3.0
       fstore_1
       fload_0
       fload_1
fcmpg 
ifne Label27
       goto Label28
Label27:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "CORRECT: 2.0 != 3.0"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
Label28:
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "total test:"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc 14.0
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       ldc "total test passed:"
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      ldc 14.0
      getstatic TypeScriptProgram/wrong F                                     ;identifier
      fsub
      putstatic TypeScriptProgram/result F                                     ;pop value: assingment_node
       getstatic    java/lang/System/out Ljava/io/PrintStream;
       getstatic     TypeScriptProgram/result F
      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V

    return

.limit locals 100
.limit stack 16
.end method
