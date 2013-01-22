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
	public void equalSquaresMustBeEqual() {
		for (int i = 0; i < 100; i++) {
			// TODO
		}
	}

	@Test
	public void setRectangleWidthShouldChangeOnlyWidth() {
		for (int i = 0; i < 100; i++) {
			// TODO
		}
	}

	@Test
	public void squaresMustBeSquare() {
		for (int i = 0; i < 100; i++) {
			// TODO
		}
	}

	private Rectangle arbitraryRectangle() {
		return new Rectangle(RANDOM.nextInt(50), RANDOM.nextInt(50));
	}

	private Square arbitrarySquare() {
		return new Square(RANDOM.nextInt(50));
	}
}
