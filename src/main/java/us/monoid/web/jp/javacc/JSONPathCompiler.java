/* Generated By:JJTree&JavaCC: Do not edit this line. JSONPathCompiler.java */
package us.monoid.web.jp.javacc;

import us.monoid.json.*;

public class JSONPathCompiler/*@bgen(jjtree)*/implements JSONPathCompilerTreeConstants, JSONPathCompilerConstants {/*@bgen(jjtree)*/
  protected JJTJSONPathCompilerState jjtree = new JJTJSONPathCompilerState();public static class JSONPathExpr extends AbstractJSONExpr {
    JSONPathExpr(int i) {
      super (i);
    }
    public void dump(String prefix) {
      System.out.println(prefix + toString());
      if (children != null && children.length > 0) {
        System.out.println(prefix + " [");
        for (int i = 0; children != null && i < children.length; ++i) {
          System.out.print(prefix + " (" + (i + 1) + ")");
          SimpleNode c = (SimpleNode) children [i];
          if (c == null) {
            System.out.println("null");
          } else {
            c.dump(prefix + " ");
          }
        }
        System.out.println(prefix + " ]");
      }
    }

    public Object eval(Object o) throws JSONException {
      if (o == null) return null;
      switch (this.id) {
        case JJTEXPRESSION: {
          if (getChildrenCount() == 0) return null;
          for (int i = 0; o != null && i < getChildrenCount(); ++i) {
            o = at(i).eval(o);
          }
          return o;
        }
        case JJTARRAY: {
          if (this.value instanceof Integer) {
          int index = Integer.class.cast(this.value);
          if (o instanceof JSONArray) {
            JSONArray array = JSONArray.class.cast(o);
            return array.get(index);
           } else {
            return null;
           }
         } else { // AST contains a selector expression. evaluate it against all objects in array                        if (o instanceof JSONArray) {
                JSONArray array = JSONArray.class.cast(o);
                // match first strategy. only one item is returned                Object result = null;
                match:
                for (int i = 0, len = array.length(); i < len; i++) {
                  Object item = array.get(i);
                  boolean test = at(0).test(item);
                          if (test) {
                            result = item; // evaluation can continue on this item                                break;
                         }
                }
                return result;
                }
         }
        }
        case JJTOBJECT: {
          if (o instanceof JSONObject) {
            JSONObject json = JSONObject.class.cast(o);
            return json.get(this.value.toString());
          }
          return null;
        }

        default : System.err.println("Doesn't handle " + this.id);
        break;
      }
      return null;
    }
  }

  static SimpleNode jjtCreate(int id)
  {
    return new JSONPathExpr(id);
  }

  public static void main(String args []) throws Exception
  {
    try
    {
      java.io.StringReader r = new java.io.StringReader(args [0]);
      JSONPathCompiler app = new JSONPathCompiler(r);
      JSONPathExpr x = JSONPathExpr.class.cast(app.expr());
      x.dump(" ");
      JSONObject json = new JSONObject("{ \u005c"a\u005c": [" +
      "{\u005c"Hello\u005c": \u005c"World\u005c"},{\u005c"Hello\u005c": \u005c"JB\u005c"}]" +
      ", \u005c"b\u005c": null, \u005c"c\u005c": { \u005c"d\u005c": 1234 }}");
      Object o = x.eval(json);
      System.err.println("Compiling <code>" + args [0] + "</code>");
      System.err.println("Eval <code>" + json + "</code>");
      System.err.println("Result is:<code>" + o + "</code>");
    } catch (Exception err) {
      err.printStackTrace();
    }
  }

  final public JSONPathExpr expr() throws ParseException {
 /*@bgen(jjtree) EXPRESSION */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPEN_ARRAY:
        array();
        break;
      case IDENTIFIER:
        object();
        break;
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
        case OPEN_ARRAY:
          ;
          break;
        default:
          jj_la1[1] = jj_gen;
          break label_1;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case OPEN_ARRAY:
          array();
          break;
        case DOT:
          jj_consume_token(DOT);
          object();
          break;
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(0);
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
  }

  final public void array() throws ParseException {
 /*@bgen(jjtree) ARRAY */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTARRAY);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token i;
    try {
      jj_consume_token(OPEN_ARRAY);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER:
        i = jj_consume_token(INTEGER);
    jjtn000.value = new Integer(i.image);
        break;
      case NOT:
      case IDENTIFIER:
      case 22:
        selector();

        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(CLOSE_ARRAY);
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
  }

  final public void selector() throws ParseException {
 /*@bgen(jjtree) selector */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTSELECTOR);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      term();
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case OR:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_2;
        }
        jj_consume_token(OR);
        term();
      }
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
  }

  final public void term() throws ParseException {
 /*@bgen(jjtree) term */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTTERM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      neg();
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AND:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_3;
        }
        jj_consume_token(AND);
        neg();
      }
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
  }

  final public void neg() throws ParseException {
 /*@bgen(jjtree) neg */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTNEG);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token n = null;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOT:
        n = jj_consume_token(NOT);
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      part();
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          if (n != null) {
            jjtn000.value = Boolean.FALSE;
          }
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
  }

  final public void part() throws ParseException {
 /*@bgen(jjtree) part */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTPART);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        predicate();
        break;
      case 22:
        jj_consume_token(22);
        selector();
        jj_consume_token(23);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
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
  }

  final public void predicate() throws ParseException {
 /*@bgen(jjtree) predicate */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTPREDICATE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token field, test, string = null, number = null;
    try {
      field = jj_consume_token(IDENTIFIER);
      test = jj_consume_token(OP);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CNAME:
        string = jj_consume_token(CNAME);
        break;
      case INTEGER:
        number = jj_consume_token(INTEGER);
        break;
      case NUMBER:
        number = jj_consume_token(NUMBER);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          if (number != null) {
          jjtn000.value = new Predicates.Operator(field.image, test.image, new Double(number.image));
         } else {
          jjtn000.value = new Predicates.Operator(field.image, test.image, string.image.substring(1,string.image.length()-1));
        }
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void object() throws ParseException {
 /*@bgen(jjtree) OBJECT */
  JSONPathExpr jjtn000 = (JSONPathExpr)JSONPathCompiler.jjtCreate(JJTOBJECT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token name;
    try {
      name = jj_consume_token(IDENTIFIER);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    jjtn000.value = name.image;
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  /** Generated Token Manager. */
  public JSONPathCompilerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[9];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x180000,0x100200,0x100200,0x485000,0x400,0x800,0x1000,0x480000,0xc040,};
   }

  /** Constructor with InputStream. */
  public JSONPathCompiler(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public JSONPathCompiler(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new JSONPathCompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public JSONPathCompiler(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new JSONPathCompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public JSONPathCompiler(JSONPathCompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(JSONPathCompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[24];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 24; i++) {
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

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
