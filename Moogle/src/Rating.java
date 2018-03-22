import java.util.Map;
import java.util.Objects;

// Name:
// Student ID:
// Section: 

public class Rating {
	public Movie m;
	public User u;
	public double rating;	//rating can be [0, 5]
	public long timestamp;	//timestamp tells you the time this rating was recorded
	private Double avgRating = -1.0;
	private Map<Integer, Rating> ratings = null;	//mapping userID -> rating
	
	public Rating(User _u, Movie _m, double _rating, long _timestamp)
        {
			if(_rating > 5 || _rating < 0) {
				return;
			}
		// YOUR CODE GOES HERE
                this.u = _u;
                this.m = _m;
                this.rating = _rating;
                this.timestamp = _timestamp;
                
	}
	public double calMeanRating()
	{

		double sum = 0;
		for(int i = 0; i < ratings.size(); i++){
			sum += ratings.get(i).rating;
		}
		avgRating = sum / ratings.size();
		return avgRating;
	}

	public Double getMeanRating()
	{

		double result = 0;
		for(Rating r: ratings.values())
		{
			avgRating += r.rating;
		}
		if(ratings.size() > 0) avgRating /= (double)ratings.size();

		return result+avgRating;

	}

	public void addRating(User user, Movie movie, double rating, long timestamp) {
		Rating r = ratings.get(new Rating(user , movie , rating , timestamp));
		if(r == null)
		{	r = new Rating(user,movie, rating, timestamp);
			ratings.put(user.getID(), r);
		}
		if (r != null)
		{
			r = new Rating(user,movie, rating, timestamp);
			ratings.put(user.getID(), r);
		}
		else
		{
			if(r.timestamp < timestamp)
			{
				r.rating = rating;
				r.timestamp = timestamp;
			}
		}

	}

	public Map<Integer, Rating> getRating(){

		// YOUR CODE GOES HERE

		return ratings;
	}

   
	
	
        @Override
	public String toString() {
		return "[uid: " + u.getID() +" mid: "+m.getID() +" rating: "+rating+"/5 timestamp: "+timestamp+"]";
	}
	@Override
	public int hashCode(){
		return Objects.hash(m, u, rating, timestamp);
	}
}
