package main.ReversePolishNotation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalcEngineStrTest {
	
	CalcEngineStr s;

	@BeforeEach
	void setUp() throws Exception {
		s = new CalcEngineStr();
	}

	@Test
	void test1() {
		assertEquals("14", s.evaluateIntFix("(3+4)*2"));
	}
	
	@Test
	void test2() {
		assertEquals("11", s.evaluateIntFix("3+4*2"));
	}
	
	@Test
	void test3() {
		assertEquals("14", s.evaluateIntFix("3*4+2"));
	}
	
	@Test
	void test4() {
		assertEquals("27", s.evaluateIntFix("3*3*3"));
	}
	
	@Test
	void test5() {
		assertEquals("3", s.evaluateIntFix("15/3-2"));
	}
	
	@Test
	void test6() {
		assertEquals("-3", s.evaluateIntFix("2-15/3"));
	}
	
	@Test
	void test7() {
		assertEquals("0", s.evaluateIntFix("3*0"));
	}
	
	@Test
	void test8() {
		assertEquals("3", s.evaluateIntFix("(4-3)*(5-2)"));
	}
	
	@Test
	void test9() {
		assertEquals("7", s.evaluateIntFix("3*2+1"));
	}
	
	@Test
	void test10() {
		assertEquals("10", s.evaluateIntFix("2+4*2"));
	}
	
	@Test
	void test11() {
		assertEquals("8", s.evaluateIntFix("2*6/2+2"));
	}
	
	@Test
	void test12() {
		assertEquals("-2", s.evaluateIntFix("(2-3)*2"));
	}
	
	@Test
	void test13() {
		assertEquals("2", s.evaluateIntFix("2*(2-1)"));
	}
	
	@Test
	void test14() {
		assertEquals("-1", s.evaluateIntFix("(2+2)-5"));
	}
	
	@Test
	void test15() {
		assertEquals("8", s.evaluateIntFix("2*2+2*2"));
	}

}
