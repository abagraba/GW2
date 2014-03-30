%{
import java.util.LinkedList;
import java.io.Reader;
import java.io.IOException;

import Market.*;

%}
      
      
%token	NL
%token	ID
%token	NUM		
%token	WORD


%%

accept		:	recipelist				{ Initializer.init();}
			;

recipelist	:	itemlist recipe
			|	recipelist recipe
			|	recipelist NL
			;

itemlist	:
			|	itemlist item
			|	itemlist NL
			;
			
item		:	ID WORD NL				{Initializer.addItem($2.sval, $1.sval);}
			;
		
recipehead	:	WORD NL					{name = $1.sval; inputs.clear();}
			;
			
recipebody	:	recipehead ingredient
			|	recipebody ingredient
			;
			
recipe		:	recipebody product		{Initializer.addRecipe(name, inputs.toArray(new Input[inputs.size()]), output); if (priority){Initializer.prioritize(name); priority = false;}}
			;
		
ingredient	:	NUM WORD NL				{inputs.add(new Input(Initializer.getItem($2.sval), $1.ival));}
			;
			
			
product		:	'~' NUM WORD NL			{output = new SingleOutput(Initializer.getItem($3.sval), $2.ival);}
			|	priority
			;
priority	:	'!' NUM WORD NL			{output = new SingleOutput(Initializer.getItem($3.sval), $2.ival); priority = true;}
			;
%%
	
	public static boolean debug;
	private GW2Lexer lexer;
	String name = "";
	private LinkedList<Input> inputs = new LinkedList<Input>();
	boolean priority;
	Output output;

	private int yylex () {
		int token = -1;
		try {
			yylval = new GW2ParserVal(0);
			token = lexer.yylex();
			//	System.out.println(yyname[token]);
		}
		catch (IOException e) {
			System.err.println("IO error :"+e);
		}
		return token;
	}


	public void yyerror (String error) {
		System.err.println ("Error: " + error);
	}


	public GW2Parser(Reader r) {
		lexer = new GW2Lexer(r, this);
	}

	
