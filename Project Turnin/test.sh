cd jasmin_exe
for i in *.j; do
  echo "COMPILING $i into .class file"
  java -jar ../jasmin.jar $i
  echo "DECOMPILING generated class file into .java file"
  java -jar ../cfr.jar TypeScriptProgram.class
  echo "RUNNING THE CLASS FILE"
  java TypeScriptProgram
done
