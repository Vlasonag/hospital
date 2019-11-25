package model.comparartor;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import model.entity.Note;

public class NoteComparator implements Comparator<Note>{

	@Override
	public int compare(Note a, Note b) {
		long t1 = a.getDate().getTime();
	    long t2 = b.getDate().getTime();
	    if(t2 > t1)
            return 1;
	    else if(t1 > t2)
            return -1;
	    else
            return 0;
		
	}

	public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0, Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingDouble(
			ToDoubleFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsFirst(Comparator<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsLast(Comparator<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Comparator<Note> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Note> thenComparing(Comparator<? super Note> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U extends Comparable<? super U>> Comparator<Note> thenComparing(
			Function<? super Note, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> Comparator<Note> thenComparing(
			Function<? super Note, ? extends U> arg0, Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Note> thenComparingDouble(
			ToDoubleFunction<? super Note> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Note> thenComparingInt(ToIntFunction<? super Note> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<Note> thenComparingLong(ToLongFunction<? super Note> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
