package runtime.plog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PArray extends PObject {

	private List<PObject> objects;

	public PArray(PObject... rest) {
		this(Arrays.asList(rest));
	}

	public PArray(List<PObject> list) {
		super(Builtin.parray);
		this.objects = new ArrayList<PObject>(list);
	}

	public void add(int index, PObject element) {
		objects.add(index, element);
	}

	public boolean add(PObject e) {
		return objects.add(e);
	}

	public boolean addAll(Collection<? extends PObject> c) {
		return objects.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends PObject> c) {
		return objects.addAll(index, c);
	}

	public void clear() {
		objects.clear();
	}

	public boolean contains(Object o) {
		return objects.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return objects.containsAll(c);
	}

	public boolean equals(Object o) {
		return objects.equals(o);
	}

	public PObject get(int index) {
		return objects.get(index);
	}

	public int hashCode() {
		return objects.hashCode();
	}

	public int indexOf(Object o) {
		return objects.indexOf(o);
	}

	public boolean isEmpty() {
		return objects.isEmpty();
	}

	public Iterator<PObject> iterator() {
		return objects.iterator();
	}

	public int lastIndexOf(Object o) {
		return objects.lastIndexOf(o);
	}

	public ListIterator<PObject> listIterator() {
		return objects.listIterator();
	}

	public ListIterator<PObject> listIterator(int index) {
		return objects.listIterator(index);
	}

	public PObject remove(int index) {
		return objects.remove(index);
	}

	public boolean remove(Object o) {
		return objects.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return objects.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return objects.retainAll(c);
	}

	public PObject set(int index, PObject element) {
		return objects.set(index, element);
	}

	public int size() {
		return objects.size();
	}

	public List<PObject> subList(int fromIndex, int toIndex) {
		return objects.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return objects.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return objects.toArray(a);
	}
	
	@Override
	public String toString() {
		return objects.toString();
	}

	public static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	public static <T> T[] concatAll(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

}
