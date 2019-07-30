package uebung4New;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pr.MakeItSimple.*;

import java.lang.invoke.MethodHandles;

import org.junit.Before;
import org.junit.Test;

import pr.MakeItSimple;

//@RunWith(Suite.class)
//@SuiteClasses({})

public class SortsTest {

	Comparable[] arrayToSort;

	public boolean sorted(Comparable[] array) {
		for (int i = 1; i < array.length; i++)
			if (array[i - 1].compareTo(array[i]) > 0)
				return false;
		return true;

	}

	public static String[] split(String text, char delimiter) {
		// z�hlen, wie viele Trennzeichen im Eingabe-String vorhanden sind
		int numberOfDelimiters = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == delimiter)
				numberOfDelimiters++;
		}

		// Array gem�� der Anzahl der Trennzeichen im Eingabe-String anlegen.
		// Es befinden sich jeweils ein Teil-String links und rechts eines Trennzeichens
		// daher +1.
		String subStrings[] = new String[numberOfDelimiters + 1];

		int substringCount = 0;
		String subString = "";

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (c == delimiter) {
				// falls Trennzeichen gefunden, aktuellen Teil-String ins Ergebnis �bernehmen
				subStrings[substringCount] = subString;
				substringCount++;
				subString = "";
			} else {
				subString += c;
			}
		}

		// letzten Teil-String mit aufnehmen
		subStrings[substringCount] = subString;
		return subStrings;
	}

	@Before
	public void setUp() throws Exception {

		System.out.println(System.getProperty("java.class.path"));

		String filename = MethodHandles.lookup().lookupClass().getResource("songs.txt").getPath();

		String[] songs = MakeItSimple.readStringArray(filename); // all songs
																	// from file

		arrayToSort = new Comparable[songs.length];
		int j = 0;

		for (String songString : songs) {
			String[] parts = split(songString, ';');
			String[] artists = new String[parts.length - 2];
			for (int i = 2; i < parts.length; i++)
				artists[i - 2] = parts[i];

			Element artist = new StringElement(artists[0]); // artist[0] =
															// K�nstler_1,
															// artist[1] =
															// K�nstler_2
			Element singleSong = new StringElement(parts[0]); // parts[0] =
																// Titel

			arrayToSort[j++] = new SongImplementation(parts[0], artists, parts[1]);
			// parts[1] = Album
		}
	}

	@Test
	public void testsOnSongFile() throws Exception {

		assertEquals(2928, arrayToSort.length); // 2928 songs 118 duplicates
		new ShellSort().sort(arrayToSort); // start ShellSort << change sort method here
		// Testen, ob Array richtig und vollst�ndig sortiert wurde
		assertEquals(2928, arrayToSort.length); // 2928 songs 118 duplicates
		assertTrue(sorted(arrayToSort));

	}

}
