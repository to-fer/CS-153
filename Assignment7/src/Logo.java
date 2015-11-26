/* Generated By:JJTree&JavaCC: Do not edit this line. Logo.java */
import java.io.*;

public class Logo/*@bgen(jjtree)*/implements LogoTreeConstants, LogoConstants {/*@bgen(jjtree)*/
  protected static JJTLogoState jjtree = new JJTLogoState();public static void main(String[] args)
    throws Exception
  {
    Reader reader = new FileReader(args[0]);
    Logo parser = new Logo(reader);

    ASTProgram program = parser.Program();
    program.dump(">");
 }

  static final public ASTProgram Program() throws ParseException {
    trace_call("Program");
    try {
                        /*@bgen(jjtree) Program */
  ASTProgram jjtn000 = new ASTProgram(JJTPROGRAM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
      try {
        label_1:
        while (true) {
          try {
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case FORWARD:
              MoveForward();
                       System.out.println("Processed Move FORWARD");
              break;
            case RIGHT:
              TurnRight();
                       System.out.println("Processed Turn RIGHT");
              break;
            case ERROR:
              Error();
                       handleError(token);
              break;
            default:
              jj_la1[0] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
          } catch (ParseException ex) {
      handleError(ex.currentToken);
          }
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case FORWARD:
          case RIGHT:
          case ERROR:
            ;
            break;
          default:
            jj_la1[1] = jj_gen;
            break label_1;
          }
        }
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
   {if (true) return jjtn000;}
      } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
      } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("Program");
    }
  }

  static final public void MoveForward() throws ParseException {
    trace_call("MoveForward");
    try {
                      /*@bgen(jjtree) MoveForward */
  ASTMoveForward jjtn000 = new ASTMoveForward(JJTMOVEFORWARD);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
      try {
        jj_consume_token(FORWARD);
        jj_consume_token(DIGITS);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EOL:
          jj_consume_token(EOL);
          break;
        case 0:
          jj_consume_token(0);
          break;
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
      }
    } finally {
      trace_return("MoveForward");
    }
  }

  static final public void TurnRight() throws ParseException {
    trace_call("TurnRight");
    try {
                    /*@bgen(jjtree) TurnRight */
  ASTTurnRight jjtn000 = new ASTTurnRight(JJTTURNRIGHT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
      try {
        jj_consume_token(RIGHT);
        jj_consume_token(DIGITS);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EOL:
          jj_consume_token(EOL);
          break;
        case 0:
          jj_consume_token(0);
          break;
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
      }
    } finally {
      trace_return("TurnRight");
    }
  }

  static final public void Error() throws ParseException {
    trace_call("Error");
    try {
                /*@bgen(jjtree) Error */
  ASTError jjtn000 = new ASTError(JJTERROR);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
      try {
        jj_consume_token(ERROR);
      } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
      }
    } finally {
      trace_return("Error");
    }
  }

  static String handleError(Token token) throws ParseException {
    trace_call("handleError");
    try {
  System.out.println("*** ERROR: Line " + token.beginLine +
                     " after \u005c"" + token.image + "\u005c"");

  Token t;

  do {
    t = getNextToken();
  } while (t.kind != EOL);

  jjtree.popNode();
  return t.image;
    } finally {
      trace_return("handleError");
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public LogoTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x4c,0x4c,0x21,0x21,};
   }

  /** Constructor with InputStream. */
  public Logo(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Logo(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LogoTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Logo(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LogoTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Logo(LogoTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LogoTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      trace_token(token, "");
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
      trace_token(token, " (in getNextToken)");
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[7];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 4; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 7; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  static private int trace_indent = 0;
  static private boolean trace_enabled = true;

/** Enable tracing. */
  static final public void enable_tracing() {
    trace_enabled = true;
  }

/** Disable tracing. */
  static final public void disable_tracing() {
    trace_enabled = false;
  }

  static private void trace_call(String s) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Call:   " + s);
    }
    trace_indent = trace_indent + 2;
  }

  static private void trace_return(String s) {
    trace_indent = trace_indent - 2;
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Return: " + s);
    }
  }

  static private void trace_token(Token t, String where) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Consumed token: <" + tokenImage[t.kind]);
      if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
        System.out.print(": \"" + t.image + "\"");
      }
      System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
    }
  }

  static private void trace_scan(Token t1, int t2) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Visited token: <" + tokenImage[t1.kind]);
      if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
        System.out.print(": \"" + t1.image + "\"");
      }
      System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

}
