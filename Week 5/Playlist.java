
import java.util.ArrayList;
import java.util.Collections;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
class Playlist {



    public static long HOURS = 60 * 60 * 1000;
   public static long MINS = 60 * 1000;
   public static long SECS = 1000;
   private int count;
   public String playlistName;
   private ArrayList<Song> playlist = new ArrayList<>();
	private final String name;
	private double totalduration;
	private int index;

    
    //String[] Playlist = new String[] {"Chawna","ChawBaan","ChawThai"};
    public Playlist(String name) {
        this.playlistName = name;
      //this.playlist = new ArrayList<>();
       this.name = name;
        this.totalduration = 0;
       
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addSong(Song song ) {
        /*playlist.add(a);
        totalduration += a.getDurationInsec();*/
        playlist.add( song);
	}
    
    public void addSongByIndex(Song song,int index) {
        /*this.song = song;
       String[] Playlist = new String[] {"Chawna","ChawBaan","ChawThai"};*/
       //this.playlist.add(index, a);
       try{
           playlist.add(index,song);
       }catch(IndexOutOfBoundsException e ) {
           System.out.println("Error: Couldn't add song at index " + index);
          // System.out.println("Error: Couldn't add song at index ");
       } return;
       /*if (index >= 0) {
			playlist.add(index, a);
		}
       else
       {
           playlist.remove(index);
       }*/
        /*if(count == songs.length) {
         System.out.println("ERROR: Collection is full. Songs were not added to the Playlist.");
      }
      songs[count] = a;
      count++;*/
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
    public void removeSongByTitle(String title) {
int Count  = 0;
                for(int i = 0 ; i< playlist.size(); i++ )
                {
                    if(playlist.get(i).getTitle() == null ? title == null : playlist.get(i).getTitle().equals(title)){
                        playlist.remove(i);
                        count++;
                    }
                    
                }if(count == 0){
                 System.out.println("Error: The title is not found ");
                // System.out.println("Error: The index is invalid");
                }/*try{
           playlist.remove(index);
       }(IndexOutOfBoundsException e ) {
           System.out.println("Error: The index is invalid" + title);
       } */	
//if (playlist.get(index).getTitle() == title) {
		//	playlist.remove(playlist.get(index).getTitle());
		//}
	}
 public void removeSongByIndex(int index) {
	try{
           playlist.remove(index);
       }catch(IndexOutOfBoundsException e ) {
               System.out.println("Error: The title is not found ");
          System.out.println("Error: The index is invalid");
       }
    }
	public void moveUp(int position) {
            Collections.swap(playlist, position, position -1);
    }
     
	public void moveDown(int position) {
               Collections.swap(playlist, position, position +1);
	}
        public void showPlaylist(){
          double Duration =0;
          for(int i = 0; i<playlist.size();i++){
              System.out.println("[" + i +"]"+ playlist.get(i).toString());
              Duration += playlist.get(i).getDuration();
              if((Duration - Math.floor(Duration) >= 0.9))
              {
                  Duration++;
                  Duration -= 0.6;
              }
          }
            System.out.println("Total Duration is " + String.format("%.2f", Duration)+ " minutes");
        }
}
        


