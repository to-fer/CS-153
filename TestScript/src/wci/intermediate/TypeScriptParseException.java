package wci.intermediate;

import java.util.ArrayList;

import wci.frontend.ParseException;

public class TypeScriptParseException extends ParseException{
	
	ParseException baseEx;
	
	public TypeScriptParseException(ParseException p){
		super(p.currentToken, p.expectedTokenSequences, p.tokenImage);
	}
	
	public ArrayList<String> getExpectedTokens() {
		ArrayList<String> result = new ArrayList<String>();
		int maxSize = 0;
		for (int i = 0; i < expectedTokenSequences.length; i++) {
			if (maxSize < expectedTokenSequences[i].length) {
		        maxSize = expectedTokenSequences[i].length;
		      }
		      for (int j = 0; j < expectedTokenSequences[i].length; j++) {
		        result.add(tokenImage[expectedTokenSequences[i][j]]);
		      }
		}
		return result;
	}
	
	@Override
	public String getMessage() {
		String message = "";
		message += "-----------ERROR----------- \n";
		message += "LOCATION: line " + currentToken.next.beginLine + ", column " + currentToken.next.beginColumn+'\n';
		message += "ENCOUNTERED: "+currentToken.image+'\n';
		message += "EXPECTED ONE OF THE FOLLOWING: \n";
		if(getExpectedTokens().size() == 1) {
			message += "      "+getExpectedTokens().get(0) + '\n';
		}
		else if(getExpectedTokens().size() > 1) {
			for(String exp: getExpectedTokens()) {
				message += "      "+exp + '\n';
			}
		}
		
		return message;
		
	}


}
