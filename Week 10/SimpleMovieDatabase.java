/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class SimpleMovieDatabase {
	public Map<Integer, Movie> movies = new HashMap<>();
	
	public void importMovies(String movieFilename)
	{	
            File Movie = new File(movieFilename);

            String line = "";
            String pstr = "([0-9]+),([^,]+),([^\n]+)";//YOUR CODE GOES HERE
            Pattern pat = Pattern.compile(pstr); 
            LineIterator Lineiter;
            Matcher match ;
            String[] Space;
            String[] tags;
            try {
			Lineiter = FileUtils.lineIterator(Movie); // Iterate the file using LineIterator
                        while (Lineiter.hasNext()) 
                        {   
                            line = Lineiter.nextLine();
                            match = pat.matcher(line);
                            if(match.find())
                            {
                                if(match.group(2).trim().equals(""))
                                {
                                    continue;
                                }
                                Movie movies = new Movie(Integer.parseInt(match.group(1)),match.group(2));
                                tags = match.group(3).split("\\|");
                                for(String tag : tags)
                                {
                                    movies.tags.add(tag);
                                }
                                this.movies.put(Integer.parseInt(match.group(1)), movies);
                            }
                    
                        }
		}
            catch (IOException e) {
			e.printStackTrace(); // Try with another
		}
		
	}
	
	
	//-------------------BONUS----------------------
	public List<Movie> searchMovies(String query) 
	{
            List<Movie> Search = new ArrayList<>();
		for (Movie movies : movies.values()) {
                    if(movies.title.toLowerCase().contentEquals(query.toLowerCase()))
                    {
                        Search.add(movies);
                    }
			
                }
		return Search;
	}
	
	public List<Movie> getMoviesByTag(String tag)
	{
		List<Movie> Tag = new ArrayList<>();
		for (Movie movieee : movies.values()) {
                    for (Iterator<String> it = movieee.tags.iterator(); it.hasNext();) {
                        String tags = it.next();
                        if(tags.equals(tag))
                        {
                            Tag.add(movieee);
                            break;
                        }
                    }
			
                }
		return Tag;
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
