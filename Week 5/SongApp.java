/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sunat Praphanwong 6088130
 */

public class SongApp {
	public static void main(String[] args) {
		Playlist myPlaylist = new Playlist("My Favorite Songs Playlist");
		
		System.out.println("Welcome to SongAPP");
		System.out.println("\nAdd songs --------------------------");
                System.out.println("My Favorite Songs Playlist");
		myPlaylist.addSong(new Song("Perfect", 4.21));
		myPlaylist.addSong(new Song("How long", 3.30));
		myPlaylist.addSongByIndex(new Song("End Game", 4.11), 0);
		myPlaylist.addSongByIndex(new Song("Anywhere", 3.35), 2);
		myPlaylist.showPlaylist();
	
		System.out.println("\nRearrange songs ---------------------");
                System.out.println("My Favorite Songs Playlist");
		myPlaylist.moveUp(1);
		myPlaylist.moveDown(2);
		myPlaylist.showPlaylist();
		
		System.out.println("\nRemove songs -----------------------");
                System.out.println("My Favorite Songs Playlist");
		myPlaylist.removeSongByTitle("End Game");
		myPlaylist.removeSongByIndex(2);
		myPlaylist.showPlaylist();
		
		System.out.println("\nCheck error ------------------------");
		myPlaylist.addSongByIndex(new Song("Find you", 3.38), 3);
		myPlaylist.removeSongByTitle("Find you");
		myPlaylist.removeSongByIndex(3);
	}
	
}
