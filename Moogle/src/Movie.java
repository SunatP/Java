// Name: Sunat Praphanwong
// Student ID: 6088130
// Section: 1C

import java.util.*;

public class Movie {
	private int mid = -1;
	private String title ;
	private int year = -1;
	private Set<String> tags ;
	private Map<Integer, Rating> ratings = null;	//mapping userID -> rating
	private Double avgRating ;
	//additional

	public Movie(int _mid, String _title, int _year)
	{
		// YOUR CODE GOES HERE
		this.mid = _mid;
		this.title =  _title;
		this.year = _year;
		ratings = new HashMap<Integer, Rating>();
		tags = new HashSet<String>();
		avgRating = 0.0;
	}

	public int getID()
	{

		// YOUR CODE GOES HERE
		return mid;
	}
	public String getTitle()
	{

		// YOUR CODE GOES HERE
		return title;
	}

	public Set<String> getTags()
	{

		// YOUR CODE GOES HERE
		return tags;
	}

	public void addTag(String tag)
	{
		tags.add(tag);
		// YOUR CODE GOES HERE
	}

	public int getYear()
	{

		// YOUR CODE GOES HERE
		return year;
	}

	@Override
	public String toString(){
		return "[movieID: " + mid + ":" + title + " (" + year + ") " + tags + "] -> avg rating: " + getMeanRating();
	}

	public double calMeanRating()
	{

		double sum = 0;
		int count = 0;
		for(Integer key: ratings.keySet()) {
			sum += ratings.get(key).rating;
			count++;
		}
		this.avgRating = sum/count;
		return this.avgRating;
	}

	public Double getMeanRating()
	{

		//double result = 0;
		/*for(Rating r: ratings.values())
		{
			avgRating += r.rating;
		}
		if(ratings.size() > 0) avgRating /= (double)ratings.size();*/
		this.avgRating = calMeanRating();
		return avgRating;

	}

	public void addRating(User user, Movie movie, double rating, long timestamp) {
		/*Rating r = ratings.get(new Rating(user , movie , rating , timestamp));
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
		}*/
		ratings.put(user.uid, new Rating(new User(user.uid), new Movie(this.mid, this.title, this.year), rating,timestamp));
	}

	public Map<Integer, Rating> getRating(){

		// YOUR CODE GOES HERE

		return ratings;
	}

}
