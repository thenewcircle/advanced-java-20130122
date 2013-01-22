package com.marakana.list;

import java.util.ArrayList;
import java.util.List;

public class ImmutableList<E> {

	private final List<E> list;

	public ImmutableList() {
		this.list = new ArrayList<E>();
	}

	private ImmutableList(List<E> list) {
		this.list = list;
	}

	public ImmutableList<E> prepend(E element) {
		List<E> list = new ArrayList<E>();
		list.add(element);
		list.addAll(this.list);
		return new ImmutableList<E>(list);
	}

	public E head() {
		return list.isEmpty() ? null : list.get(0);
	}

	public ImmutableList<E> tail() {
		return list.size() < 1 ? null : new ImmutableList<E>(list.subList(1, list.size()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}

}
