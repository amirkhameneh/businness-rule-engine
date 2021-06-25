package com.hm.rules.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hm.rules.model.dom.Operation;
import com.hm.rules.model.dom.Token;



@Service
public class ExpressionEvaluatorServiceImpl implements ExpressionEvaluatorService {
	
	public static final List<String> DIGITS = Arrays.asList("1","2","3","4","5","6","7","8","9","0");
	public static final List<String> LOGICAL_OPERATORS = Arrays.asList("&&","||");
	public static final List<String> CONDITIONAL_OPERATORS = Arrays.asList("==",">=","!=","<=","<<",">>","~~");
	


	
	
	@Override
	public List<Operation> compileExpression(String expression) {
		
		List<Operation> executionSequence = new ArrayList();
		complieSubExpression(executionSequence,parseExpression(expression));
		
		return executionSequence;
	}

	@Override
	public int complieSubExpression(List<Operation> executionSequence, List<Token> expression) {
		
		truncateParentheses( expression);
		
	    List<List<Token>> terms = new ArrayList();	     
	    List<String> operators= new ArrayList();
	    getFirstLevelTermsOfParsedExpression(expression,terms,operators);
	     
	     	     
		List<Integer> CompoundTermsLastIndex= new ArrayList();
		for (List<Token> t :terms) {
			if (t.size()>3 && t.size()>0) {  
				CompoundTermsLastIndex.add(complieSubExpression(executionSequence ,t));	 
			 }else {
				CompoundTermsLastIndex.add(0);	  
			 }
		}


		appendToBinaryTreeOperationsCollection(terms ,operators ,CompoundTermsLastIndex ,executionSequence);
	    
	    return executionSequence.size()-1;
	}
	

	@Override
	public  boolean validateExpressionParentheses(List<Token> expression) {

	     int countOfLeftMinusRightParentheses=0;
	     
	     for (Token t :expression) {
    		 if (t.getType()=="LP" ) {
    			 countOfLeftMinusRightParentheses++;
		 	}else if (t.getType()=="RP" ){
		 		countOfLeftMinusRightParentheses--;
		 	}
    		 if (countOfLeftMinusRightParentheses<0) {
	    		 return false;
    		 }
    	 }
    	
	     
	     if (countOfLeftMinusRightParentheses!=0) {
	    	 return false;
	     }else {
	    	 return true;
	     }
	}
	
	@Override
	public List<Token> parseExpression(String expression) {
		
		List<Token> parsedExpression = new ArrayList();
		char currentChar;
		String currentTwoChar;
		
		int lastTokenindex=999999999;
		
		for (int i = 0; i < expression.length(); i++) {
			
			currentChar = expression.charAt(i) ;
			
			if (i<expression.length()-1) {
				currentTwoChar =  String.valueOf(currentChar)+String.valueOf(expression.charAt(i+1)) ;
			}else {
				currentTwoChar = "";
		    }	
			
			if (currentChar == '(' || currentChar == ')'||currentChar == ' '||
				LOGICAL_OPERATORS.contains(currentTwoChar)||
				CONDITIONAL_OPERATORS.contains(currentTwoChar)) {
				
				if (i> lastTokenindex && expression.substring(lastTokenindex+1 ,i) != "") {
					parsedExpression.add(new Token(expression.substring(lastTokenindex+1 ,i),"EX"));
				}
				
			}
				
			
			if (currentChar == '(') {
				parsedExpression.add(new Token("(","LP"));
				lastTokenindex = i; 
			}else if(currentChar == ')') {
				parsedExpression.add(new Token(")","RP"));
				lastTokenindex = i; 
			}else if(currentChar == ' ') {
				lastTokenindex = i; 
			}else if(LOGICAL_OPERATORS.contains(currentTwoChar)) {
				parsedExpression.add(new Token(currentTwoChar,"LO"));
				 i++;
				lastTokenindex = i; 
			}else if(CONDITIONAL_OPERATORS.contains(currentTwoChar)) {
				parsedExpression.add(new Token(currentTwoChar,"CO"));
				 i++;
				lastTokenindex = i; 
			}
		 
		}
		
		return parsedExpression;

	}

    public void truncateParentheses(List<Token> expression) {
    	List<Token> truncatedExpression;
    	while((expression.get(0).getType()=="LP" && expression.get(expression.size()-1).getType()=="RP" )){
    		truncatedExpression = new ArrayList();
    		truncatedExpression = expression.subList(1, expression.size()-1);
  		     if  (validateExpressionParentheses(truncatedExpression)) {
  		    	 expression.remove(0);
		    	 expression.remove(expression.size()-1);
		    	 
  		     }else {
  		    	 break;
  		     }
  		    	 
	     }
    }

	@Override
	public boolean validateExpression(String expression) {
		// TODO Auto-generated method stub
		return false;
	} 
	
	public void getFirstLevelTermsOfParsedExpression(List<Token> expression , List<List<Token>> terms , List<String> operators) {
		
		List<Token> term = new ArrayList();
		
		int parenthesesCounter=0;
	    int cursor=0;
	    
	     for (Token t:expression) {
	    	 if (t.getType()=="LP") {
	    		 parenthesesCounter++;
	    	 }else if (t.getType()=="RP") {
	    		 parenthesesCounter--;
	    	 } 
	    	 
	    	 if (t.getType()=="LO" && parenthesesCounter==0) {
	    		 truncateParentheses( term);
	    		 operators.add(t.getName());
	    		 terms.add(term);
	    		 term = new ArrayList();
	    		 
	    	 }else if (cursor==expression.size()-1) {
	    		 term.add(t);
	    		 truncateParentheses( term);
	    		 terms.add(term);
	    	 }else {
	    		 term.add(t);
	    		
	    	 }
	    	 
	    	 
	    	 cursor++;
	     }
	}
	
	private void appendToBinaryTreeOperationsCollection(List<List<Token>> terms,List<String> operators,List<Integer> CompoundTermsLastIndex,List<Operation> executionSequence) {
		int termsCursor=0;    
	    for (List<Token> t :terms) {
	    	if (termsCursor==0) {
	    		if (CompoundTermsLastIndex.get(termsCursor)==0) {
	    			executionSequence.add(new Operation( 0, t.get(0).getName(), 
	    		 										0, t.get(2).getName(),t.get(1).getName() )); 
	    		} else {
	    			executionSequence.add(new Operation( CompoundTermsLastIndex.get(termsCursor), null, 
								0, null,null )); 	    			
	    		}
	    	}else {
	    		if (CompoundTermsLastIndex.get(termsCursor)==0) {
	    			executionSequence.add(new Operation( 0, t.get(0).getName(), 
								0, t.get(2).getName(),t.get(1).getName() )); 
	    			executionSequence.add(new Operation( executionSequence.size()-1, null, 
	    					executionSequence.size()-2, null,operators.get(termsCursor-1)  )); 
	    			
	    			
	    		}else {
	    			executionSequence.add(new Operation( CompoundTermsLastIndex.get(termsCursor), null, 
	    					executionSequence.size()-1,null,operators.get(termsCursor-1))); 

	    		}
	    	}
	    			 
	    		 

	    	termsCursor++; 
	     }
	}
	
}
