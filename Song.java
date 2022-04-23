import java.util.Arrays;
import java.util.StringJoiner;

public class Song {

	private String title;
	private String artist;
	private int[] duration;
	private static final String INFO_DELIMITER = "; "; 
	private static final String TIME_DELIMITER = ":";

	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.duration = Arrays.copyOf(time, time.length);
	}

	/**
	 * Initializes a song given a string using split method
	 * 
	 * @param info a string that separates title and artist by ";", and hours and
	 *             minutes using ":"
	 * 
	 */
	public Song(String info) {

		// TODO: Initialize a Song by parsing a String. Use split method.
		String[] spitted = info.split(INFO_DELIMITER);
		title = spitted[0];
		artist = spitted[1];
		
		String[] timesplit = info.split(TIME_DELIMITER);
		if(timesplit.length == 1) {
			duration = new int[] {Integer.parseInt(spitted[2])};
		}
		else if (timesplit.length == 2) {
			String[] minutes = spitted[2].split(TIME_DELIMITER);
			duration = new int[] {Integer.parseInt(minutes[1]), Integer.parseInt(minutes[0])};
		}
		else if (timesplit.length == 3) {
			String[] minutes = spitted[2].split(TIME_DELIMITER);
			duration = new int[] {Integer.parseInt(minutes[2]), Integer.parseInt(minutes[1]),
					Integer.parseInt(minutes[0])};
		}
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int[] getTime() {
		return Arrays.copyOf(duration, duration.length);
	}

	@Override
	public String toString() {
		// TODO: Return a string. Use StringJoiner
		StringJoiner time = new StringJoiner (TIME_DELIMITER);
		for(int i = 0; i < duration.length; ++i) {
			time.add(String.format("%02d", duration[duration.length-1-i]));
		}
		return title + "; " + artist + "; " + time.toString();
	}
}
