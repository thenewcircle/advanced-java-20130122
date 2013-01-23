package com.marakana.list;

public final class ImmutableList<E> {

	public final E head;
	public final ImmutableList<E> tail;

	public ImmutableList() {
		this.head = null;
		this.tail = null;
	}

	private ImmutableList(E head, ImmutableList<E> tail) {
		this.head = head;
		this.tail = tail;
	}

	public ImmutableList<E> prepend(E element) {
		return new ImmutableList<E>(element, this);
	}

	public <E2> ImmutableList<E2> transform(Function<? super E, ? extends E2> fn) {
		return tail == null
			? new ImmutableList<E2>()
			: new ImmutableList<E2>(fn.apply(head), tail.transform(fn));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ImmutableList))
			return false;
		ImmutableList other = (ImmutableList) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (tail == null) {
			if (other.tail != null)
				return false;
		} else if (!tail.equals(other.tail))
			return false;
		return true;
	}

}
