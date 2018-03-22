// Name:
// Student ID:
// Section: 

import java.util.*;

public class Movie {
	private int mid = -1;
	private String title ;
	private int year = -1;
	private Set<String> tags ;
	private Map<Integer, Rating> ratings = null;	//mapping userID -> rating
	private Double avgRating = -1.0;
	//additional

	public Movie(int _mid, String _title, int _year)
	{
		// YOUR CODE GOES HERE
		this.mid = _mid;
		this.title =  _title;
		this.year = _year;
		ratings = new HashMap<>();
		tags = new HashSet<>();
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
		return "[movieID: " + mid + ":" + title + " (" + year + ") " + tags + "] -> avg rating: " + avgRating;
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

		//double result = 0;
		for(Rating r: ratings.values())
		{
			avgRating += r.rating;
		}
		if(ratings.size() > 0) avgRating /= (double)ratings.size();

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
		ratings.put(user.getID(), new Rating(user, movie, rating, timestamp));
	}

	public Map<Integer, Rating> getRating(){

		// YOUR CODE GOES HERE

		return ratings;
	}
	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o == null || getClass() != o.getClass()){
			return false;
		}
		Movie movie = (Movie) o;
		return mid == movie.mid &&
				year == movie.year &&
				Objects.equals(title, movie.title) &&
				Objects.equals(tags, movie.tags) &&
				Objects.equals(ratings, movie.ratings) &&
				Objects.equals(avgRating, movie.avgRating);
	}
	@Override
	public int hashCode(){

		return Objects.hash(mid, title, year, tags, ratings, avgRating);
	}
}
