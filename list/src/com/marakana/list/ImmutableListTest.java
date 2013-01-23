package com.marakana.list;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ImmutableListTest {

	private static final Random RANDOM = new Random();

	private static final Function<Integer, Integer> DOUBLE = new Function<Integer, Integer>() {
		public Integer apply(Integer value) {
			return value * 2;
		}
	};

	private static final Function<Object, String> TO_STRING = new Function<Object, String>() {
		public String apply(Object value) {
			return value.toString();
		}
	};

	@Test
	public void doubleMustTransform() {
		ImmutableList<Integer>
			l1 = new ImmutableList<Integer>().prepend(3).prepend(2).prepend(1),
			l2 = new ImmutableList<Integer>().prepend(6).prepend(4).prepend(2);
		assertEquals(l2, l1.transform(DOUBLE));
	}

	@Test
	public void toStringMustTransform() {
		ImmutableList<Integer> l1 = new ImmutableList<Integer>().prepend(3).prepend(2).prepend(1);
		ImmutableList<String> l2 = new ImmutableList<String>().prepend("3").prepend("2").prepend("1");
		assertEquals(l2, l1.transform(TO_STRING));
	}
	
	@Test
	public void listDecompositionMustBeDualOfListComposition() {
		for (int i = 0; i < 100; i++) {
			Integer head = RANDOM.nextInt();
			ImmutableList<Integer> tail = arbitraryList(), composed = tail
					.prepend(head);
			assertSame(head, composed.head);
			assertSame(tail, composed.tail);
		}
	}

	private static ImmutableList<Integer> arbitraryList() {
		return RANDOM.nextBoolean() ? arbitraryList().prepend(RANDOM.nextInt())
				: new ImmutableList<Integer>();
	}
}
