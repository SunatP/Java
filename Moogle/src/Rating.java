import java.util.Map;

// Name: Sunat Praphanwong
// Student ID: 6088130
// Section: 1C

public class Rating {
	public Movie m;
	public User u;
	public double rating;	//rating can be [0, 5]
	public long timestamp;	//timestamp tells you the time this rating was recorded
	private Double avgRating =0.0;
	private Map<Integer, Rating> ratings = null;	//mapping userID -> rating
	
	public Rating(User _u, Movie _m, double _rating, long _timestamp)
	{

		// YOUR CODE GOES HERE
                this.u = _u;
                this.m = _m;
                this.rating = _rating;
                this.timestamp = _timestamp;
                
	}


        @Override
		public String toString() {
			return "[uid: " + u.getID() + " mid: " + m.getID() + " rating: " + rating + "/5 timestamp: " + timestamp + "]";
		}
}
