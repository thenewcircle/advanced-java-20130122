package com.marakana.demo;

public class Variance {

	public static class Box<E> {
		private E element;

		public Box(E element) {
			this.element = element;
		}

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}
	}

	public static class A {};
	public static class B extends A {};
	public static class C extends B {};

	public static B getBFromABox(Box<? extends B> box) {
		return box.getElement();
	}

	public static void putBInABox(Box<? super B> box) {
		box.setElement(new B());
	}

	public static void covarianceDemo() {
		Box<C> cbox = new Box<C>(new C());
		getBFromABox(cbox);
	}

	public static void contravarianceDemo() {
		Box<A> abox = new Box<A>(new A());
		putBInABox(abox);
	}

	public static void invarianceDemo() {
		Box<B> box = new Box<B>(new B());
		putBInABox(box);
		getBFromABox(box);
	}

}
