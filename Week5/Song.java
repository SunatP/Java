/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class Song {
    public  float HOURS = 60 * 60 * 1000;
    public  float MINS = 1;
    public  float SECS = 60;

    /**
     *
     */
    public  String title;

    /**
     *
     */
    public  String artist;

    /**
     *
     */
    public  String album;
    private final double duration;
   
//this is constructor method: public Song(String title, double duration)
    public Song(String title, double duration) { //information of song including title (String), and duration in minutes (double)
        this.title = title;
        this.duration = duration;

    }

    /**
     *
     * @return
     */
    public String getTitle() {
    return title;
        
    }

     double getDuration() {
        return duration; // Get Duration In Minute
       
    }

    double getDurationInsec() {
        
       // String[] S = duration
       
        return Math.floor(duration)*60+(Math.floor(duration*100%100)*MINS);
       //return (duration*SECS)+((duration-duration)*MINS); // Get Duration In Seconds
    }
    @Override
    public String toString() {
		return getTitle()+","+getDuration()+" minutes"+" ("+getDurationInsec()+" seconds)";
	}
    
}
