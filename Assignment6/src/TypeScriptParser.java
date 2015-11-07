/* Generated By:JavaCC: Do not edit this line. TypeScriptParser.java */
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
	TypeScriptParser is the class that is doing the parsing of
	the program file (not implemented)
*/
public class TypeScriptParser implements TypeScriptParserConstants {
    public static void main(String [] args) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(args[0]));
                String content = new String(encoded, StandardCharsets.UTF_8);
        java.io.StringReader sr = new java.io.StringReader(content);
        TypeScriptParser parser = new TypeScriptParser(sr);
        try {
            parser.Expression();
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

  static final public void Statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LEFT_BRACE:
      Compound_stmt();
      break;
    case VAR:
      Assignment();
      break;
    case NOT:
    case MINUS:
    case PLUS:
    case LEFT_PARAN:
    case STRING_LITERAL:
    case IDENTIFIER:
    case NUM:
      Expression();
      jj_consume_token(SEMICOLON);
      break;
    case IF:
      If_stmt();
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Compound_stmt() throws ParseException {
    jj_consume_token(LEFT_BRACE);
    Statement_list();
    jj_consume_token(RIGHT_BRACE);
  }

  static final public void Statement_list() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
      case IF:
      case NOT:
      case MINUS:
      case PLUS:
      case LEFT_PARAN:
      case LEFT_BRACE:
      case STRING_LITERAL:
      case IDENTIFIER:
      case NUM:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      Statement();
    }
  }

  static final public void If_stmt() throws ParseException {
    if (jj_2_1(2)) {
         System.out.println("IF_STATEMENT START");
      If_part();
      Else_part();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IF:
        If_part();
         System.out.println("IF_STATEMENT END");
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void If_part() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(LEFT_PARAN);
    Expression();
    jj_consume_token(RIGHT_PARAN);
    Statement();
  }

  static final public void Else_part() throws ParseException {
    jj_consume_token(ELSE);
    Statement();
  }

  static final public void IdentifierType() throws ParseException {
    identifier();
    jj_consume_token(COLON);
    typeSignature();
  }

  static final public void ArgumentList() throws ParseException {
    jj_consume_token(LEFT_PARAN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      IdentifierType();
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_2;
        }
        jj_consume_token(COMMA);
        IdentifierType();
      }
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    jj_consume_token(RIGHT_PARAN);
  }

  static final public void PureFunction() throws ParseException {
     System.out.println("PureFunction START");
    jj_consume_token(LEFT_BRACE);
    Statement_list();
    jj_consume_token(RETURN);
    Expression();
    jj_consume_token(SEMICOLON);
    jj_consume_token(RIGHT_BRACE);
     System.out.println("PureFunction END");
  }

  static final public void SideEffectFunction() throws ParseException {
     System.out.println("SideEffectFunction START");
    jj_consume_token(LEFT_BRACE);
    Statement_list();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case RETURN:
      jj_consume_token(RETURN);
      jj_consume_token(SEMICOLON);
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(RIGHT_BRACE);
     System.out.println("SideEffectFunction END");
  }

  static final public void FunctionDeclaration() throws ParseException {
     System.out.println("FunctionDeclaration START");
    jj_consume_token(FUNCTION);
    identifier();
    ArgumentList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COLON:
      jj_consume_token(COLON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case NUMBER:
      case STRING:
        typeSignature();
        PureFunction();
        break;
      case VOID:
        jj_consume_token(VOID);
        SideEffectFunction();
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case LEFT_BRACE:
      SideEffectFunction();
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     System.out.println("FunctionDeclaration END");
  }

  static final public void Assignment() throws ParseException {
    AssignmentVar();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQ:
      jj_consume_token(EQ);
      Expression();
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    jj_consume_token(SEMICOLON);
         System.out.println("ComplexAssignmentStatement ENDS");
  }

  static final public void AssignmentVar() throws ParseException {
    jj_consume_token(VAR);
    IdentifierType();
  }

  static final public void typeSignature() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
   System.out.println("Type: number");
      jj_consume_token(NUMBER);
   System.out.println("Type: boolean");
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
   System.out.println("Type: string");
      break;
    case STRING:
      jj_consume_token(STRING);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

// all the Expression 
  static final public void Expression() throws ParseException {
    if (jj_2_2(2)) {
   System.out.println("SimpleExpression STARTS");
      SimpleExpression();
   System.out.println("SimpleExpression ENDS");
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOT:
      case MINUS:
      case PLUS:
      case LEFT_PARAN:
      case STRING_LITERAL:
      case IDENTIFIER:
      case NUM:
        SimpleExpression();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOUBLE_EQ:
          jj_consume_token(DOUBLE_EQ);
          break;
        case NOT_EQ:
          jj_consume_token(NOT_EQ);
          break;
        case LT:
          jj_consume_token(LT);
          break;
        case LE:
          jj_consume_token(LE);
          break;
        case GT:
          jj_consume_token(GT);
          break;
        case GE:
          jj_consume_token(GE);
          break;
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        SimpleExpression();
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void SimpleExpression() throws ParseException {
    if (jj_2_3(2)) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MINUS:
      case PLUS:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
          jj_consume_token(PLUS);
          break;
        case MINUS:
          jj_consume_token(MINUS);
          break;
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      term();
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MINUS:
        case PLUS:
        case OR:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_3;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
          jj_consume_token(PLUS);
          break;
        case MINUS:
          jj_consume_token(MINUS);
          break;
        case OR:
          jj_consume_token(OR);
          break;
        default:
          jj_la1[15] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        term();
      }
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOT:
      case LEFT_PARAN:
      case STRING_LITERAL:
      case IDENTIFIER:
      case NUM:
        term();
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void term() throws ParseException {
    if (jj_2_4(2)) {
      factor();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MOD:
        case MUL:
        case DIV:
        case AND:
          ;
          break;
        default:
          jj_la1[17] = jj_gen;
          break label_4;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MUL:
          jj_consume_token(MUL);
          break;
        case DIV:
          jj_consume_token(DIV);
          break;
        case MOD:
          jj_consume_token(MOD);
          break;
        case AND:
          jj_consume_token(AND);
          break;
        default:
          jj_la1[18] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        factor();
      }
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOT:
      case LEFT_PARAN:
      case STRING_LITERAL:
      case IDENTIFIER:
      case NUM:
        factor();
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void factor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
      factor();
      break;
    case IDENTIFIER:
      identifier();
      break;
    case NUM:
      number();
      break;
    case STRING_LITERAL:
      string();
      break;
    case LEFT_PARAN:
      jj_consume_token(LEFT_PARAN);
      Expression();
      jj_consume_token(RIGHT_PARAN);
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void identifier() throws ParseException {
    jj_consume_token(IDENTIFIER);
  }

  static final public void number() throws ParseException {
    jj_consume_token(NUM);
  }

  static final public void string() throws ParseException {
    jj_consume_token(STRING_LITERAL);
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_3R_12() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(27)) {
    jj_scanpos = xsp;
    if (jj_scan_token(32)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) {
    jj_scanpos = xsp;
    if (jj_scan_token(54)) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_10()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_11()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_8() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  static private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(29)) {
    jj_scanpos = xsp;
    if (jj_scan_token(28)) return true;
    }
    return false;
  }

  static private boolean jj_3R_16() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3R_17()) return true;
    }
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(LEFT_PARAN)) return true;
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_14()) {
    jj_scanpos = xsp;
    if (jj_scan_token(57)) {
    jj_scanpos = xsp;
    if (jj_scan_token(58)) {
    jj_scanpos = xsp;
    if (jj_scan_token(56)) {
    jj_scanpos = xsp;
    if (jj_3R_15()) return true;
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3_3() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_7()) jj_scanpos = xsp;
    if (jj_3R_8()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_9()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3R_12()) return true;
    }
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(29)) {
    jj_scanpos = xsp;
    if (jj_scan_token(28)) {
    jj_scanpos = xsp;
    if (jj_scan_token(53)) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_5() {
    if (jj_scan_token(IF)) return true;
    if (jj_scan_token(LEFT_PARAN)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public TypeScriptParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x30400c00,0x30400c00,0x800,0x0,0x0,0x80000,0x11c000,0x0,0x40000000,0x1c000,0x0,0x30400000,0x30000000,0x30000000,0x30000000,0x30000000,0x400000,0x9000000,0x9000000,0x400000,0x400000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x7004400,0x7004400,0x0,0x40,0x2000000,0x0,0x0,0x4002,0x0,0x0,0x950030,0x7000400,0x0,0x0,0x200000,0x200000,0x7000400,0x400001,0x400001,0x7000400,0x7000400,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[4];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public TypeScriptParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public TypeScriptParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new TypeScriptParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
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
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public TypeScriptParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new TypeScriptParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public TypeScriptParser(TypeScriptParserTokenManager tm) {
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
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(TypeScriptParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
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
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[62];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 62; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 4; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
