package uebung3;

public class SongImplementation implements Song, Comparable<Song> {
	
	private String name;
	private String[] artists;
	private String albumName;
	
	public SongImplementation(String name, String[] artists, String albumName) {
		this.name = name;
		this.artists = artists;
		this.albumName = albumName;
	}
	
	@Override
	public int compareTo(Song s) {
		return this.name.compareTo(s.getSongName());
	}
	
	@Override
	public String toString() {
		String s = name + "\n";
		s += "  Künstler: ";
		for (int i = 0; i < artists.length - 1; i++)
			s += artists[i] + ", ";
		s += artists[artists.length - 1] + "\n";
		s += "  Album:    " + albumName;
		return s;
	}

	@Override
	public String getSongName() {
		return name;
	}

	@Override
	public String[] getArtists() {
		return artists;
	}

	@Override
	public String getAlbumName() {
		return albumName;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof SongImplementation){
			return this.toString().equals(((SongImplementation)o).toString());
		}
		return false;
	}

}
