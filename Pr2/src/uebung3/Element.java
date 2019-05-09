package uebung3;

public interface Element {

	/**
	 * Compares one Element to the other.<br>
	 * <br>
	 * A simple way to remember which way around the comparision is:<br>
	 * If you call this Function, it will look something like this. Depending on
	 * which side is <b>GREATER</b> it will return the following.
	 * 
	 * <pre>
	 * elem.compareTo(other);
	 *   -1     0        1
	 * </pre>
	 *
	 * @param other
	 *            the other element
	 * @return this > other: negative<br>
	 *         this == other: zero<br>
	 *         this < other: positive<br>
	 */
	int compareTo(Element other);

	/**
	 * clones this Element
	 *
	 * @return a new Object with the same value
	 */
	Object clone();

	/**
	 * print out useful information of this {@link Element} like it's value
	 */
	void print();

	/**
	 * returns itself with <code>
	 * return this;
	 * </code>
	 */
	Element getVal();
	@Override
	int hashCode();
	
	@Override
	boolean equals(Object o);
}
