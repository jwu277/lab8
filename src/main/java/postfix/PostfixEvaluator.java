package postfix;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {
	
	private String arithmeticExpr;

	private double value;
	private boolean evaluated = false;

	private Stack<Double> valueStack = new Stack<Double>();
	
	/**
	 * This is the only constructor for this class.
	 * It takes a string that represents an arithmetic expression
	 * as input argument.
	 * 
	 * @param expr is a string that represents an arithmetic expression 
	 * <strong>in Postfix notation</strong>.
	 */
	public PostfixEvaluator( String expr ) {
		arithmeticExpr = expr;
	}
	
	/**
	 * This method evaluates the arithmetic expression that 
	 * was passed as a string to the constructor for this class.
	 * 
	 * @return the value of the arithmetic expression
	 * @throws MalformedExpressionException if the provided expression is not
	 * 	a valid expression in Postfix notation
	 */
	double eval( ) throws MalformedExpressionException {

	    if (evaluated == true) {
	        return value;
        }
		
		Scanner scanner = new Scanner(arithmeticExpr);

        Token currToken;

	    while (!scanner.isEmpty()) {
	        currToken = scanner.getToken();
            scanner.eatToken();
	        processToken(currToken);
        }

        if (valueStack.isEmpty()) {
            throw new MalformedExpressionException();
        }

        double topVal = valueStack.pop();

        if (!valueStack.isEmpty()) {
            throw new MalformedExpressionException();
        }

        value = topVal;
        evaluated = true;

		return value;

	}

	private void processToken(Token tkn) throws MalformedExpressionException {

	    if (tkn.isDouble()) {
	        valueStack.push(tkn.getValue());
        }
        else {
            performOperation(tkn.getName());
        }

    }

    private void performOperation(String s) throws MalformedExpressionException {

        try {
            switch (s) {

                case "+":
                    valueStack.push(valueStack.pop() + valueStack.pop());
                    break;
                case "-":
                    valueStack.push(-valueStack.pop() + valueStack.pop());
                    break;
                case "*":
                    valueStack.push(valueStack.pop() * valueStack.pop());
                    break;
                case "/":
                    valueStack.push(1 /valueStack.pop() * valueStack.pop());
                    break;
                default:
                    throw new MalformedExpressionException();

            }
        }
        catch (EmptyStackException e) {
            throw new MalformedExpressionException();
        }

    }
	
}