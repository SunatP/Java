/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class SimpleMovieDatabase {
	public Map<Integer, Movie> movies = new HashMap<Integer, Movie>();
	
	
	public void importMovies(String movieFilename)
	{
		File movieFile = new File(movieFilename);
		LineIterator li;
		Pattern mPattern = Pattern.compile("([\\d]+),([^,]+),([^\n]+)");
		Matcher m;
		String line;
		String[] tags;
		try {
			li = FileUtils.lineIterator(movieFile);
			
			while(li.hasNext()) {
				line = li.nextLine();
				m = mPattern.matcher(line);
				if(m.find()) {
					if(m.group(2).trim().equals("")) {
						continue;
					}
					Movie movie = new Movie(Integer.parseInt(m.group(1)),m.group(2));
					tags = m.group(3).split("\\|");
					for (String tag : tags) {
						movie.tags.add(tag);
					}
					movies.put(Integer.parseInt(m.group(1)), movie);
				}
			}
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	//-------------------BONUS----------------------
	public List<Movie> searchMovies(String query) 
	{
		ArrayList<Movie> searchResult = new ArrayList<Movie>();
		for(Movie m : movies.values()) {
			if (m.title.toLowerCase().contains(query.toLowerCase())) {
				searchResult.add(m);
			}
		}
		return searchResult;
	}
	
	public List<Movie> getMoviesByTag(String tag)
	{
		ArrayList<Movie> tagResult = new ArrayList<Movie>();
		for(Movie m : movies.values()) {
			for (String t : m.tags) {
				if (t.equals(tag)) {
					tagResult.add(m);
					break;
				}
			}
		}
		return tagResult;
	}
	
	
	public static void main(String[] args)
	{
		SimpleMovieDatabase mdb = new SimpleMovieDatabase();
		mdb.importMovies("lab11_movies.txt");
		System.out.println("Done importing "+mdb.movies.size()+" movies");
		int[] mids = new int[]{139747, 141432, 139415, 139620, 141305};
		for(int mid: mids)
		{
			System.out.println("Retrieving movie ID "+mid+": "+mdb.movies.get(mid));
		}
		
		//Uncomment for bonus
		System.out.println("\n////////////////////////// BONUS ///////////////////////////////");
		String[] queries = new String[]{"america", "thai", "thailand"};
		for(String query: queries)
		{
			System.out.println("Results for movies that match: "+query);
			for(Movie m: mdb.searchMovies(query))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		
		String[] tags = new String[]{"Musical", "Action", "Thriller"};
		for(String tag: tags)
		{
			System.out.println("Results for movies in category: "+tag);
			for(Movie m: mdb.getMoviesByTag(tag))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		
		
	}

}
