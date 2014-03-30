package Parsing;

%%

%class GW2Lexer
%byaccj

%{
  private GW2Parser yyparser;

  public GW2Lexer(java.io.Reader r, GW2Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NL			=	\n | \r | \r\n

ID			=	\[[A-Za-z0-9+/]{8}\]

NUM			=	[0-9]+
WORD		=	[A-Za-z][A-Za-z0-9 ]+


%%

	/* newline */


	\t+			{}

	{NL}		{ return GW2Parser.NL; }

	{NUM}		{ yyparser.yylval = new GW2ParserVal(Integer.parseInt(yytext())); return GW2Parser.NUM; }
	
	{WORD}		{ yyparser.yylval = new GW2ParserVal(yytext()); return GW2Parser.WORD; }
	
	{ID}		{ yyparser.yylval = new GW2ParserVal(yytext().substring(1, 9)); return GW2Parser.ID; }
	
	[~]			{ return '~';}
	[!]			{ return '!';}
	
	.			{ System.err.println("Unexpected "+ yytext()); }