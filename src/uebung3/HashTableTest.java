package uebung3;

import org.junit.Test;

import pr.MakeItSimple;

import static org.junit.Assert.*;

import static pr.MakeItSimple.*;
import java.lang.invoke.MethodHandles;

import uebung3.Element;

public class HashTableTest {
    String s1 = "abcdefghijk";
    String s2 = "abcdefghijklmnop";
    String s3 = "abcdefghijklmnopqrst";
    String s4 = "abcdefghijklmnopqrstuvwxyz";
    String s5 = "Melissa Etheridge";
    String s6  = "abc";
    String s7  = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaz";
    String s8  = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaay";
    String s9  = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaax";
    
    
    Element ms1 = new StringElement(s1);
    Element ms2 = new StringElement(s2);
    Element ms3 = new StringElement(s3);
    Element ms4 = new StringElement(s4);
    Element ms5 = new StringElement(s5);
    Element ms6 = new StringElement(s6);
    Element ms7 = new StringElement(s7);
    Element ms8 = new StringElement(s8);
    Probing p = new QuadraticProbing();
    HashTable ht = new HashTable(5, p);

    @Test
    public void testsOnEmptyHT() {
        assertEquals(0, ht.size());
        assertNull (ht.get(ms1));
        assertEquals(false, ht.contains(ms1));
        assertEquals(false, ht.containskey(s1));
        assertEquals(true, ht.isEmpty());
        ht.clear();
    }
    
    @Test
    public void testsOnHT() throws Exception {
        
        assertEquals(true, ht.isEmpty());

        assertNull ( ht.put(ms1, s1));
        assertNull (ht.put(ms2, s2));
        assertNull (ht.put(ms3, s3));
        assertNull (ht.put(ms4, s4));
        
        assertEquals(s1, ht.put(ms1, s1+s2));   // returns old value when same key is inserted 
        assertEquals(4, ht.size());
        assertEquals(s2, ht.get(ms2));
        assertEquals(s4, ht.get(ms4));
        assertEquals(s1+s2, ht.get(ms1));
        assertEquals(false, ht.contains(s1));
        assertEquals(true, ht.contains(s1+s2));
        assertNull (ht.get(s6));
        assertEquals(false, ht.containskey(ms5));
        assertEquals(true, ht.containskey(ms2));
        assertEquals(false, ht.isEmpty());
        
        assertEquals(true, ht.remove(ms1));
        assertEquals(false, ht.remove(ms5));
        
        assertEquals(false, ht.contains(s1+s2));
        assertEquals(true, ht.contains(s3));

        assertEquals(3, ht.size());
        assertEquals(true, ht.remove(ms2));
        assertEquals(true, ht.remove(ms3));
        assertEquals(true, ht.remove(ms4));
        
        assertEquals(0, ht.size());
        
        assertNull (ht.put(ms1, s1));
        assertNull (ht.put(ms2, s2));
        assertNull (ht.put(ms3, s3));
        assertNull (ht.put(ms4, s4));
        assertNull (ht.put(ms6, s4));
       
        assertEquals(5, ht.size());
        ht.clear();
        assertEquals(0, ht.size());
        
        assertNull (ht.put(ms1, s1));
        assertEquals(1, ht.size());
        assertNull (ht.put(ms2, s2));
        assertEquals(2, ht.size());

    }
    
	@Test
	public void testsOnSongFile() throws Exception {
		    
		    String filename = MethodHandles.lookup().lookupClass().getResource("songs.txt").getPath();
		    
		    HashTable songsTable = new HashTable (5);
			
			String[] songs = MakeItSimple.readStringArray(filename);
			
			for (String songString : songs) {
				String[] parts = SongsHashTable.split(songString, ';');
				String[] artists = new String[parts.length - 2];
				for (int i = 2; i < parts.length; i++){
					artists[i - 2] = parts[i];
				}
				Element artist = new StringElement (artists[0]);   // artist[0] = Künstler_1, artist[1] = Künstler_2
				Element singleSong = new StringElement (parts[0]); // parts[0] = Titel
				
				Song song = new SongImplementation(parts[0], artists, parts[1]); // parts[1] = Album
				
	            songsTable.put(singleSong, song);
	            
			}
			
        assertEquals(2810, songsTable.size());  // 2928 songs 118 duplicates`
        
		String [] artists = {"Journey"};
		
		
        
        assertEquals(true, songsTable.containskey(new StringElement("After The Fall")));
        assertNotNull (songsTable.get(new StringElement("After The Fall")));
        assertEquals(true, songsTable.contains (new SongImplementation("After The Fall", artists, "Frontiers")));
        
        artists[0] = "Pink Floyd";  // Title "Time" is a duplicate key

        assertEquals(true, songsTable.contains (new SongImplementation("Time", artists, "Ivor Wynne (Live In London June 28, 1975)")));
        assertEquals(false, songsTable.contains (new SongImplementation("Time", artists, "Pulse")));
        assertEquals(true, songsTable.containskey(new StringElement("Time")));
        assertNotNull (songsTable.get(new StringElement("Time")));

	}

        @Test
        public void testsOnLongStrings() throws Exception {
        	
            HashTable ht = new HashTable(1);
            
            assertEquals(true, ht.isEmpty());
            
            assertNull ( ht.put(ms7, s7));
            assertEquals(1, ht.size());  

            assertNull (ht.put(ms8, s8));
            assertEquals(2, ht.size());
            
            assertEquals(true, ht.containskey(ms7));
            assertEquals(true, ht.containskey(ms8));
            
            assertEquals(true, ht.contains (s7));
            assertEquals(false, ht.contains (s9));

        
	}

    
    
}
