import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Playlist {

	private ArrayList<Song> songs;
	private Song[] songs2;
	private static final int EXPAND_THRESHOLD = 4;
	private int expandBy = 2;
	private int expandFrequency = 0;

	public Playlist() {
		// TODO: Initialize the songs field.
		songs = new ArrayList<Song>();
	}

	public Playlist(String filename) throws IOException {
		// TODO: Initialize the songs field and add song from file using addSongs.
		songs = new ArrayList<Song>();
		addSongs(filename);
	}

	public int getNumSongs() {
		return songs.size();
	}

	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}

	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}

	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}

	public boolean addSong(int index, Song song) {
		// TODO: Update the Lab 3 method.
		if (index < 0 || song == null || index > songs.size()) {
			return false;
		}
		else {
			if (index == songs.size()) {
				songs.add(song);
				return true;
			}
			songs.add(index, song);
			return true;
		}
	}

	public int addSongs(Playlist playlist) {
		// TODO: Update the Lab 3 method.
		if (playlist != null) {
			int z = 0;
			songs2 = playlist.getSongs();

			for (int i = 0; i < songs2.length; ++i) {
				if(songs2[i] != null) {
					songs.add(songs2[i]);
					++z;
				}
			}
			return z;
		} 
		return 0;
	}

	public int addSongs(String filename) throws IOException {
		// TODO: Read a file
		Scanner readFile = new Scanner(new File(filename));
		while (readFile.hasNextLine()) {
			Song songFromFile = new Song(readFile.nextLine());
			songs.add(songFromFile);
		}
		readFile.close();
		return songs.size();
	}

	public void expand() {
		// We implemented this method in Lab3
		// If we have already expanded the playlist more than (or equal) the threshold
		// double the expand step

		if (expandFrequency >= EXPAND_THRESHOLD) {
			expandBy *= 2;
		}
		// Keep track of the number of times the playlist was expanded
		expandFrequency++;
		// Actual expansion of the playlist
		songs2 = Arrays.copyOf(songs2, songs2.length + expandBy);
	}

	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}

	public Song removeSong(int index) {
		// TODO: Update the Lab 3 method.
		if (songs == null || index > songs.size() - 1 || index < 0 || songs.size() == 0) {
			return null;
		}
		return 	songs.remove(index);
	}

	public void saveSongs(String filename) throws IOException {
		// TODO: Write in a file
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(toString());
		bw.close();
	}

	//Return the total time of all the Songs as an array of integers. Use the same format as the time field in the Song class.
	public int[] getTotalTime() {
		// TODO: Return the total time
		int[] totalTime = new int[3];
		int seconds = 0, minutes = 0, hours = 0;
		for(int i = 0; i < songs.size(); ++i) {
			int[] time = songs.get(i).getTime();
			if(time.length == 3) {
				seconds = seconds + time[0];
				minutes = minutes + time[1];
				hours = hours + time[2];
			}
			else if(time.length == 2) {
				seconds = seconds + time[0];
				minutes = minutes + time[1];
			}
			else if(time.length == 1) {
				seconds = seconds + time[0];
			}
		}
			totalTime[0] = seconds % 60;
			totalTime[1] = (minutes + (seconds / 60)) % 60;
			totalTime[2] = hours + (minutes + (seconds / 60)) / 60;
			if(totalTime[2] == 0) {
				if(totalTime[1] == 0) {
					return Arrays.copyOf(totalTime, 1);
				}
				else {
					return Arrays.copyOf(totalTime, 2);
				}
			}
		return totalTime.clone();
	}

	@Override
	public String toString() {
		// TODO: Return the String
		String reallyLongString = "";
		for (int i = 0; i < songs.size(); ++i) {
			if(i != 0) {
				reallyLongString = reallyLongString + System.lineSeparator();
			}
			reallyLongString = reallyLongString + songs.get(i).toString();
		}
		return reallyLongString;
	}
}
