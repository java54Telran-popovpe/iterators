package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.numbers.RangePredicate;

class RangePredicateTest extends RangeTest {

	@Test
	void iterableIteratorTest() {
		Integer[] expected = { 10, 11, 12, 13, 14, 15 };
		RangePredicate range = RangePredicate.getRange(10, 15);
		assertArrayEquals( expected, toArrayFromIterable(new Integer[expected.length], range));
		RangePredicate rangeEvenOdd = RangePredicate.getRange(1, 7);
		Integer[] rangeOddexpected = { 1, 3, 5, 7 };
		Integer[] rangeEvenexpected = {2, 4, 6 };
		rangeEvenOdd.setPredicate( n -> n %2 !=0 );
		assertArrayEquals( rangeOddexpected, toArrayFromIterable(new Integer[rangeOddexpected.length], rangeEvenOdd));
		rangeEvenOdd.setPredicate( n -> n %2 == 0 );
		assertArrayEquals( rangeEvenexpected, toArrayFromIterable(new Integer[rangeEvenexpected.length], rangeEvenOdd));
		
	}
	@Test
	void iteratorIncorrectUsageTest() {
		RangePredicate rangePredicate = RangePredicate.getRange(1, 9);
		rangePredicate.setPredicate( n -> n % 10 == 0 );
		Iterator<Integer> it = rangePredicate.iterator();		
		assertThrowsExactly( NoSuchElementException.class, () -> it.next() );
	}

}
