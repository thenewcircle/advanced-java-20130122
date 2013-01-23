package com.marakana.list;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ImmutableListTest {

	private static final Random RANDOM = new Random();

	@Test
	public void listDecompositionMustBeDualOfListComposition() {
		for (int i = 0; i < 100; i++) {
			Integer head = RANDOM.nextInt();
			ImmutableList<Integer>
				tail = arbitraryList(),
				composed = tail.prepend(head);
			assertSame(head, composed.head);
			assertSame(tail, composed.tail);
		}
	}
	
	private static ImmutableList<Integer> arbitraryList() {
		return RANDOM.nextBoolean()
			? arbitraryList().prepend(RANDOM.nextInt())
			: new ImmutableList<Integer>();
	}
}
