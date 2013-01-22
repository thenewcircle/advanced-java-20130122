package com.marakana.shapes;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ShapesTest {

	private static final Random RANDOM = new Random();

	@Test
	public void equalRectanglesMustBeEqual() {
		for (int i = 0; i < 100; i++) {
			Rectangle
				r1 = arbitraryRectangle(),
				r2 = new Rectangle(r1.getWidth(), r1.getHeight());
			assertEquals(r2, r1);
		}
	}

	@Test
	public void copyRectangleWithWidthShouldChangeOnlyWidth() {
		for (int i = 0; i < 100; i++) {
			Rectangle
				r1 = arbitraryRectangle(),
				r2 = r1.copyWithWidth(RANDOM.nextInt(50));
			assertEquals(r1.getHeight(), r2.getHeight());
		}
	}

	private Rectangle arbitraryRectangle() {
		return RANDOM.nextBoolean()
			? new Rectangle(RANDOM.nextInt(50), RANDOM.nextInt(50))
			: arbitrarySquare();
	}

	private Square arbitrarySquare() {
		return new Square(RANDOM.nextInt(50));
	}
}
