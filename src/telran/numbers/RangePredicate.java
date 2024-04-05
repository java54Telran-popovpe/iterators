package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range {
	
	private Predicate<Integer> predicate;
	

	protected RangePredicate(int min, int max) {
		super(min, max);
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator();
	}	
	
	public void setPredicate(  Predicate<Integer> predicate) {
		this.predicate = predicate;
	}
	
	public static RangePredicate getRange( int min, int max ) {
		checkMinMax(min, max);
		return new RangePredicate( min, max );
	}
	
	private class RangePredicateIterator implements Iterator<Integer>{
		
		private Integer currentElement = min;
		
		public RangePredicateIterator( ) {
			findNextValidElement();
		}
		
		@Override
		public boolean hasNext() {
			return currentElement <= max;
		}
		
		@Override
		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Integer elementToReturn = currentElement++;
			findNextValidElement();
			return elementToReturn;
		}

		//trying to find the next iterator element, taking into account predicate value end set 
		//currentElement field on it
		//the search starts from current currentElementField including it
		//this method used in constructor and next() methods
		private void findNextValidElement() {
			while( currentElement <= max &&  predicate != null && predicate.negate().test(currentElement) ) {
				currentElement++;
			}
		}
	}
}
