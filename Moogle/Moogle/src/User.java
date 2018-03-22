import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
// Name:
// Student ID:
// Section:

public class User implements Comparable<User>{
	public int uid = 0;
	Map<Integer,Rating> ratings = null;

	public User(int _id){

		this.uid = _id;
		ratings = new HashMap<>();

	}

	public int getID(){

		// YOUR CODE GOES HERE

		return uid;
	}

	public String toString()
	{
		StringBuilder  s = new StringBuilder();
		s.append("[user: "+uid+" rates "+ratings.size()+" movies]\n");
		for(Rating rat: ratings.values())
		{
			s.append("\t"+rat+"\n");
		}

		return s.toString();
	}

	public double getMeanRating()
	{
		double result = 0;
                result = ratings.values().stream().map((r) -> r.rating).reduce(result, (accumulator, _item) -> accumulator + _item);
		if(ratings.size() > 0) result /= (double)ratings.size();
		if(ratings.getClass() == null)
		{
			return ratings.values().stream().map((r) -> r.rating).reduce(result, (accumulator, _item) -> accumulator + _item);
		}

		return result;
	}

	public void addRating(User _u,Movie movie, double rating, long timestamp)
	{
		Rating r = ratings.get(_u.uid);
		if(r == null)
		{	r = new Rating(_u,movie, rating, timestamp);
			ratings.put(movie.getID(), r);
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

	@Override
	public int compareTo(User _uid) {
		return (new Integer(uid)).compareTo(_uid.uid);
	}
	@Override
	public int hashCode(){

		return Objects.hash(uid);
	}
}
