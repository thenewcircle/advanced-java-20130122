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
			Square
				s1 = arbitrarySquare(),
				s2 = new Square(s1.getWidth());
			assertEquals(s2, s1);
		}
	}

	@Test
	public void setRectangleWidthShouldChangeOnlyWidth() {
		for (int i = 0; i < 100; i++) {
			Rectangle r = arbitraryRectangle();
			int
				width = RANDOM.nextInt(50),
				height = r.getHeight();
			r.setWidth(width);
			assertEquals(width, r.getWidth());
			assertEquals(height, r.getHeight());
		}
	}

	@Test
	public void squaresMustBeSquare() {
		for (int i = 0; i < 100; i++) {
			Square s = arbitrarySquare();
			int side = RANDOM.nextInt(50);
			s.setWidth(side);
			assertEquals(side, s.getWidth());
			assertEquals(side, s.getHeight());
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
