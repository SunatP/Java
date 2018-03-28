// Name: Sunat Praphanwong
// Student ID: 6088130
// Section: 1C

import com.google.common.collect.BiMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleMovieSearchEngine implements BaseMovieSearchEngine {
	public Map<Integer, Movie> movies;
	public Map<Integer, User> user;
	public Map<Double, Double> ratings;
	String[] Split;
	private static BiMap<Integer, Integer> movKeyMap;
	@Override
	public Map<Integer, Movie> loadMovies(String movieFilename) {
		//File movieFile = new File(movieFilename);
	}
	@Override
	public void loadRating(String ratingFilename) {
		//String csvFile = "data-sample/ratings.csv";
		


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

		// YOUR CODE GOES HERE
	}


	@Override
	public void loadData(String movieFilename, String ratingFilename) {
		movies = loadMovies(movieFilename);
		loadRating(ratingFilename);

		StringBuilder contentBuilder = new StringBuilder();


	/*try(Stream<String> stream = Files.lines(Paths.get(movieFilename))){
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(contentBuilder);*/
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
		/*Map<Integer,Movie> gAllMovie = new TreeMap<Integer, Movie>();
		if (movies != null) {
			return movies;
		}
		else if(movies == null)
		{
			return gAllMovie;
		}
		return gAllMovie;*/
		return movies;
	}

	@Override
	public List<Movie> searchByTitle(String title, boolean exactMatch) {
		List<Movie> result = new ArrayList<Movie>();
		for (Integer key : movies.keySet()) {
			String temp = movies.get(key).getTitle().toLowerCase();
			if (temp.contains(title)) {
				result.add(movies.get(key));
			}
		}
		return result;
	}

	private static List<Movie> inside = new ArrayList<Movie>();
	private static List<Movie> Search = new ArrayList<Movie>();
	@Override
	public List<Movie> searchByTag(String tag) {

		// YOUR CODE GOES HERE
		List<Movie> result = new ArrayList<Movie>();
		for(Integer key: movies.keySet()) {
			Set<String> list = movies.get(key).getTags();
			for(String x : list) {
				if(x.contains(tag)) {
					result.add(movies.get(key));
				}
			}
		}
		return result;

	}

	@Override
	public List<Movie>searchByYear(int year) {
		List<Movie> mList = new ArrayList<Movie>();
		boolean chk = false;
		List<Movie> result = new ArrayList<Movie>();
		for(Integer key: movies.keySet()) {
			if(movies.get(key).getYear()==year) {
				result.add(movies.get(key));
			}
		}
		return result;
		/*if (this.movies.isEmpty())
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
		}*/
		// YOUR CODE GOES HERE


	}

	@Override
	public List<Movie> advanceSearch(String title, String tag, int year) {
		boolean chk = false;
		List<Movie> mList = new ArrayList<Movie>();
		List<Movie> result = new ArrayList<Movie>();
		for(Integer key: movies.keySet()) {
			if(title == null) {
				Set<String> list = movies.get(key).getTags();
				for(String Slist : list) {
					if(Slist.contains(tag)) {
						if(movies.get(key).getYear()==year) {
							result.add(movies.get(key));
						}
					}
				}
			}else if(tag == null) {
				String temp = movies.get(key).getTitle().toLowerCase();
				if(temp.contains(title)) {
					if(movies.get(key).getYear()==year) {
						result.add(movies.get(key));
					}
				}
			}else if(year == -1) {
				Set<String> list = movies.get(key).getTags();
				String temp = movies.get(key).getTitle().toLowerCase();
				for(String Slist : list) {
					if(temp.contains(title)) {
						if(Slist.contains(tag)) {
							result.add(movies.get(key));
						}
					}
				}
			}else {
				Set<String> list = movies.get(key).getTags();
				String temp = movies.get(key).getTitle().toLowerCase();
				for(String Slist : list) {
					if(temp.contains(title)) {
						if(Slist.contains(tag)) {
							if(movies.get(key).getYear() == year)
								result.add(movies.get(key));
						}
					}
				}
			}
		}
		return result;
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

	}
	//List<MyBean> results = select(myList, having(on(MyBean.class).getCategoryName(), org.hamcrest.Matchers.containsString("Dog H")));
	@Override
	public List<Movie> sortByTitle(List<Movie> SortWoWTitle, boolean SortBT) {
/*public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
				+ " Value : " + entry.getValue());
        }
    }*/

		// YOUR CODE GOES HERE
		if(SortBT == true) {
			//List<String> sortedNames = names.stream().sorted().collect(Collectors.toList());
			//List<Movie> sortedName = SortWoWTitle.stream().sorted().collect(Collectors.toList());
			SortWoWTitle.sort(Comparator.comparing(Movie::getTitle));
			return SortWoWTitle;
		}else if(SortBT == false) {
			SortWoWTitle.sort(Comparator.comparing(Movie::getTitle).reversed());
			return SortWoWTitle;
		}
		return null;
	}
	@Override
	public List<Movie> sortByRating(List<Movie> FindRatemax2min, boolean SortBR){
		// YOUR CODE GOES HERE
		if(SortBR) {
			FindRatemax2min.sort(Comparator.comparingDouble(Movie::getMeanRating));
		}else if(SortBR == false) {
			FindRatemax2min.sort(Comparator.comparingDouble(Movie::getMeanRating).reversed());
		}
		return FindRatemax2min;
	}


}