// Name:
// Student ID:
// Section: 

import com.google.common.collect.BiMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SimpleMovieSearchEngine implements BaseMovieSearchEngine {
	public Map<Integer, Movie> movies;
	public Map<Integer, User> user;
	public Map<Double, Double> ratings;
	String[] Split;
	private static BiMap<Integer, Integer> movKeyMap;
	@Override
	public Map<Integer, Movie> loadMovies(String movieFilename) {
		//File movieFile = new File(movieFilename);
		Map<Integer,Movie> mMap = new TreeMap<Integer, Movie>();
		//LineIterator Lineiter;
		//String line;
		String[] Split;
		//String[] tags;
		String line = "";

		//BufferedReader Lmovies = new BufferedReader(new FileReader(movieFilename));
		Map<Integer, Movie> movies = new TreeMap<Integer, Movie>();
		File movieFile = new File(movieFilename);
		LineIterator Lineiter;
		//String line;
		String[] Space;
		String[] tags;
		try {
			Lineiter = FileUtils.lineIterator(movieFile); // Iterate the file using LineIterator
		}
		catch (IOException e) {
			return null; // Return null if unable to open the file
		}

		Lineiter.nextLine(); // Skip the first line header of the file

		// Keep looping while there's another line in the file
		while (Lineiter.hasNext()) {
			line = Lineiter.nextLine();
			Space = line.split(","); // Split the line using ','
			int year = 0;
			Pattern pattern = Pattern.compile("\\(\\d{4}\\)"); // Regex pattern for (year)
			Matcher matcher = pattern.matcher(Space[1]);

			if (Space.length == 3) {
				if (matcher.find()) {
					year = Integer.parseInt(Space[1].substring(matcher.start() + 1, matcher.end() - 1));
				}
			}
			// The movie title contains ','
			else {
				Space = line.split("\""); // Split the line using '"'
				matcher = pattern.matcher(Space[1]);
				if (matcher.find()) {
					year = Integer.parseInt(Space[1].substring(matcher.start() + 1, matcher.end() - 1));
				}
				// Remove the excess ','
				Space[0] = Space[0].replace(",", "");
				Space[2] = Space[2].replace(",", "");
			}
			Movie m = new Movie(Integer.parseInt(Space[0]), Space[1].substring(0, Space[1].length() - 7), year);
			tags = Space[2].split("\\|"); // Split the tags using '|'
			for (String tag : tags) {
				m.addTag(tag); // Add each tag to the movie
			}
			mMap.put(m.getID(), m);
		}
		Lineiter.close();
		return mMap;
		/*while((line = Lmovies.readLine()) != null) {
				m = p.matcher(line);
				if(m.matches())
				{
					return null;
					/*Lmovies.readLine();
					int id = Integer.parseInt(m.group(1));
					int year = Integer.parseInt(m.group(2));
					Movie _Movie = new Movie(id ,movieFilename,year);
					this.movies.put(id,_Movie);
				}
				else
				{
					Lmovies.readLine();
					int id = Integer.parseInt(m.group(1));
					int year = Integer.parseInt(m.group(2));
					Movie _Movie = new Movie(id ,movieFilename,year);
					this.movies.put(id,_Movie);
					mMap.put(id, _Movie);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}

	@Override
	public void loadRating(String ratingFilename) {
		//String csvFile = "data-sample/ratings.csv";
		String line = "";


		/*try (BufferedReader br = new BufferedReader(new FileReader(ratingFilename))) {

			while ((line = br.readLine()) != null) {
				String eachLinePattern = "([\\d]+),([\\d]+),(.*),(\\d+)";
				Pattern pattern = Pattern.compile(eachLinePattern);
				int userID;
				int movieId;
				double rating;
				long timestamp;
				String[] nah = br.toString().split("[\\r?\\n]+");
				for(int i = 1; i < nah.length; i++){
					Matcher m = pattern.matcher(nah[i]);
					if(m.matches()){
						userID = Integer.parseInt(m.group(1));
						movieId = Integer.parseInt(m.group(2));
						rating = Double.parseDouble(m.group(3));
						timestamp = Long.parseLong(m.group(4));
						movies.get(movieId).addRating(new User(userID), movies.get(movieId), rating, timestamp);
						System.out.println(userID + movieId + rating + timestamp );
					}
				}
				// use comma as separator
				//String[] Movie = line.split(cvsSplitBy);

				//System.out.println(Movie[0]+","+Movie[1]+","+Movie[2]);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}*/
		BufferedReader br = null;
		//String Split = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(ratingFilename));
			while ((line = br.readLine()) != null) {
				String[] SPsplit = line.split(cvsSplitBy);
				//			Movie m = new Movie(Integer.parseInt(Split[0]), Split[1].substring(0, Split[1].length() - 7), y);
				System.out.println(SPsplit[0]+","+SPsplit[1]+","+SPsplit[2]+","+SPsplit[3]);
               //this.ratings.put();
			}

		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			// YOUR CODE GOES HERE
	}


	@Override
	public void loadData(String movieFilename, String ratingFilename) {
		movies = loadMovies(movieFilename);
		this.loadRating(ratingFilename);

		StringBuilder contentBuilder = new StringBuilder();


	try(Stream<String> stream = Files.lines(Paths.get(movieFilename))){
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(contentBuilder);
		/*String csvFile = "data-micro/movies.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader lData = new BufferedReader(new FileReader(csvFile))) {

			while ((line = lData.readLine()) != null) {

				// use comma as separator
				String[] Movie = line.split(cvsSplitBy);
				lData.toString();
				//System.out.println();
				System.out.println(Movie[0]+","+Movie[1]+","+Movie[2]);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} //*/
		//movies = loadMovies(movieFilename);
		//rMap = loadRating(ratingFilename);


	// YOUR CODE GOES HERE
			
	}

	@Override
	public Map<Integer, Movie> getAllMovies() {
		Map<Integer,Movie> gAllMovie = new TreeMap<Integer, Movie>();
		if (movies != null) {
			return movies;
		}
		else if(movies == null)
		{
			return gAllMovie;
		}
		return gAllMovie;
	}

	@Override
	public List<Movie> searchByTitle(String title, boolean exactMatch) {
		List<Movie> mList = new ArrayList<Movie>();

		// YOUR CODE GOES HERE
		/*for (int mKey : movKeyMap.values()) {
			if (exactMatch != false && title == null) {
				//mList.add(new MovieItem(mMap.get(mKey), predict(mMap.get(mKey), u)));	// add the movie in the year range
				return mList;
			}
		}
		return mList;*/
		Search.clear();
		for (Movie m1 : inside) {
			if (m1.getTitle()== null) {
				inside.add(m1);
				//inside.add();
			}
		}

		return mList;
	}

	private static List<Movie> inside = new ArrayList<Movie>();
	private static List<Movie> Search = new ArrayList<Movie>();
	@Override
	public List<Movie> searchByTag(String tag) {

		// YOUR CODE GOES HERE
		
		return null;
	}

	@Override
	public List<Movie>searchByYear(int year) {
		List<Movie> mList = new ArrayList<Movie>();
		boolean chk = false;
		if (this.movies.isEmpty())
		{
			return null;
		}
		for (int i = 0 ; i < this.movies.size();i++)
		{
			if (this.movies.get(i).getID() == year)
			{
				chk =true;
				break;
			}
			if (chk)
			{
				return mList;
			}
			else
			{
				return mList;
			}
		}
		// YOUR CODE GOES HERE
		
		return mList;
	}

	@Override
	public List<Movie> advanceSearch(String title, String tag, int year) {
		boolean chk = false;
		List<Movie> mList = new ArrayList<Movie>();
				//mList = Search(title,tag,year);
		Pattern reg = Pattern.compile("([0-9]+),([\\w]+),([\\w]+)");
		for(int i=0;i<this.movies.size();i++) {
			if(this.movies.get(i).getTitle() == title ||this.movies.get(i).getTitle() == tag || this.movies.get(i).getID() == year) {
				chk = true;
			}
			if (chk)
			{
				return mList;
			}
			else
			{
				return mList;
			}
		//if(this.movies)
		// YOUR CODE GOES HERE


	}
	/*public List<MyBean> search(List<MyBean> lstBeans, String method, String regExp) {
		List<MyBean> lstMatch = new ArrayList<>(lstBeans.size());
		Pattern pattern = Pattern.compile(regExp);
		for (MyBean bean : lstBeans) {

			if (method.equals("categoryName")) {
				String name = bean.getCategoryName();
				if (pattern.matcher(name).matches()) {
					lstMatch.add(bean);
				}
			}
		}
		return lstMatch;*/
		return mList;
	}
//List<MyBean> results = select(myList, having(on(MyBean.class).getCategoryName(), org.hamcrest.Matchers.containsString("Dog H")));
	@Override
	public List<Movie> sortByTitle(List<Movie> unsortedMovies, boolean asc) {

		// YOUR CODE GOES HERE
		
		return null;
	}

	@Override
	public List<Movie> sortByRating(List<Movie> unsortedMovies, boolean asc) {

		// YOUR CODE GOES HERE
		
		return null;
	}

}
