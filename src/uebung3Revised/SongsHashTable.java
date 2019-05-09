package uebung3Revised;

import java.lang.invoke.MethodHandles;

import pr.MakeItSimple;
import uebung3.Song;
import uebung3.SongImplementation;

public class SongsHashTable {

	public static String[] split(String text, char delimiter) {
		// zählen, wie viele Trennzeichen im Eingabe-String vorhanden sind
		int numberOfDelimiters = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == delimiter)
				numberOfDelimiters++;
		}
		
		// Array gemäß der Anzahl der Trennzeichen im Eingabe-String anlegen.
		// Es befinden sich jeweils ein Teil-String links und rechts eines Trennzeichens daher +1.
		String subStrings[] = new String[numberOfDelimiters + 1];
		
		int substringCount = 0;
		String subString = "";
		
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			
			if (c == delimiter) {
				// falls Trennzeichen gefunden, aktuellen Teil-String ins Ergebnis übernehmen
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
	
	public static Element[] returnArrayOfElementSongnames(String filename){
		
		String file = MethodHandles.lookup().lookupClass().getResource(filename).getPath();
		String[] songs = MakeItSimple.readStringArray(file);
		
		Element[] toBeReturned = new StringElement[songs.length];
		
		int i = 0;
		
		for (String songString : songs) {
			String[] parts = split(songString, ';');
			
			toBeReturned[i] = new StringElement (parts[0]);
			i++;
			
		}
		
		return toBeReturned;
		
	}
	
	public static Song[] returnArrayOfSong(String filename) {
		String file = MethodHandles.lookup().lookupClass().getResource(filename).getPath();
		String[] songs = MakeItSimple.readStringArray(file);
		
		Song[] toBeReturned = new SongImplementation[songs.length];
		
		int j = 0;
		
		for (String songString : songs) {
			String[] parts = split(songString, ';');
			String[] artists = new String[parts.length - 2];
			for (int i = 2; i < parts.length; i++)
				artists[i - 2] = parts[i];
			
			Song song = new SongImplementation(parts[0], artists, parts[1]);
//			
			toBeReturned[j] = song;
			j++;
			
		}
		
		return toBeReturned;
		
	}
	
	public static void main(String[] args) {
	    
	    HashTable<Element,Song> songsTable = new MyHashTable<> (4,new QuadraticProbing());

		Song[] songs = returnArrayOfSong("songs.txt");
		Element[] songNames = returnArrayOfElementSongnames("songs.txt");
		System.out.println(songs.length);
		
		for (int i = 0; i < songs.length; i++) {
            
			songsTable.put(songNames[i],songs[i]);
            
		}
		System.out.println(songsTable.getStat());
		System.out.println("Einträge in Hashtable: " + songsTable.size());
		System.out.println("Kollisionen in Hashtable: " +((MyHashTable<Element, Song>) songsTable).getStat());
		
		String [] artists = {"Journey"};
		
//		Song song = new SongImplementation("After The Fall",artists,"Journey");
		System.out.println(songsTable.get(new StringElement("After The Fall")));
		System.out.println(songsTable.containsKey(new StringElement("After The Fall")));
		System.out.println(songsTable.contains( new SongImplementation("After The Fall",artists,"Frontiers")));

		
	}

}