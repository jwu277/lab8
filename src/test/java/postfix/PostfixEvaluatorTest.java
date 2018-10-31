package postfix;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostfixEvaluatorTest {

    @Test(expected = MalformedExpressionException.class)
    public void test0() throws MalformedExpressionException {
        new PostfixEvaluator("").eval();
    }

	@Test
	public void test1() throws MalformedExpressionException {
	    assertEquals(7, new PostfixEvaluator("5 2 +").eval(), 0);
	}

    @Test
    public void test2() throws MalformedExpressionException {
        assertEquals(8, new PostfixEvaluator("5 2 + 7 - 8 +").eval(), 0);
    }

    @Test
    public void test3() throws MalformedExpressionException {
        assertEquals(-40, new PostfixEvaluator("5 2 + 7 - 8 + 0 5 - *").eval(), 0);
    }

    @Test(expected = MalformedExpressionException.class)
    public void test4() throws MalformedExpressionException {
        assertEquals(-40, new PostfixEvaluator("5 2 + 7 - 8 + 0 5 - * +").eval(), 0);
    }

    @Test(expected = MalformedExpressionException.class)
    public void test5() throws MalformedExpressionException {
        new PostfixEvaluator("+").eval();
    }

    @Test(expected = MalformedExpressionException.class)
    public void test6() throws MalformedExpressionException {
	    new PostfixEvaluator("5 6.7 {").eval();
    }

    @Test(expected = MalformedExpressionException.class)
    public void test7() throws MalformedExpressionException {
	    new PostfixEvaluator("7 0 4 *").eval();
    }

    @Test
    public void test8() throws MalformedExpressionException {
	    assertEquals(-4, new PostfixEvaluator("2 1 3 - *").eval(), 0.00001);
    }

}
