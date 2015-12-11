cd jasmin_exe
for i in *.j; do
  echo "\n\n"
  echo "###############  START TEST  ###################"
  echo "COMPILING $i into .class file"
  java -jar ../jasmin.jar $i
  echo "DECOMPILING generated class file into .java file"
  y=${i%.*}
  java -jar ../cfr.jar $y.class
  echo "RUNNING THE CLASS FILE"
  java $y
  echo "###############  TEST DONE #####################"
  echo "\n\n"
done
