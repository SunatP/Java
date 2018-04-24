/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;

public class SimpleMovieRecommender implements BaseMovieRecommender {

	private static Map<Integer, Movie> mMap;			// mid - Movie map
	private static Map<Integer, User> uMap;				// uid - User map
	private static double[][] ratingMatrix;				// User - Movie rating matrix
	private static double[][] similarityMatrix;			// User - User similarity matrix
	private static BiMap<Integer, Integer> usrKeyMap;	// index - uid bimap
	private static BiMap<Integer, Integer> movKeyMap;	// index - mid bimap
	private static double[] usrMeanRating;				// User mean rating array

	@Override
	public Map<Integer, Movie> loadMovies(String movieFilename) {
		Map<Integer, Movie> mMap = new TreeMap<Integer, Movie>();
		File movieFile = new File(movieFilename);
		LineIterator li;
		String line;
		String[] sp;
		String[] tags;
		try {
			li = FileUtils.lineIterator(movieFile); // Iterate the file using LineIterator
		}
		catch (IOException e) {
			return null; // Return null if unable to open the file
		}

		li.nextLine(); // Skip the first line header of the file

		// Keep looping while there's another line in the file
		while (li.hasNext()) {
			line = li.nextLine();
			sp = line.split(","); // Split the line using ','
			int year = 0;
			Pattern yp = Pattern.compile("\\(\\d{4}\\)"); // Regex pattern for (year)
			Matcher ym = yp.matcher(sp[1]);

			if (sp.length == 3) {
				if (ym.find()) {
					year = Integer.parseInt(sp[1].substring(ym.start() + 1, ym.end() - 1));
				}
			}
			// The movie title contains ','
			else {
				sp = line.split("\""); // Split the line using '"'
				ym = yp.matcher(sp[1]);
				if (ym.find()) {
					year = Integer.parseInt(sp[1].substring(ym.start() + 1, ym.end() - 1));
				}
				// Remove the excess ','
				sp[0] = sp[0].replace(",", "");
				sp[2] = sp[2].replace(",", "");
			}
			Movie m = new Movie(Integer.parseInt(sp[0]), sp[1].substring(0, sp[1].length() - 7), year);
			tags = sp[2].split("\\|"); // Split the tags using '|'
			for (String tag : tags) {
				m.addTag(tag); // Add each tag to the movie
			}
			mMap.put(m.mid, m);
		}
		li.close();
		return mMap;
	}

	@Override
	public Map<Integer, User> loadUsers(String ratingFilename) {
		Map<Integer, User> uMap = new TreeMap<Integer, User>();

		File ratingFile = new File(ratingFilename);
		LineIterator li;
		String line;
		String[] sp;

		try {
			li = FileUtils.lineIterator(ratingFile);
		}
		catch (IOException e) {
			return null;
		}

		li.nextLine();

		while (li.hasNext()) {
			line = li.nextLine();
			sp = line.split(",");
			int uid = Integer.parseInt(sp[0]);
			if (uMap.get(uid) == null) {
				User u = new User(uid); // Create new user if there's no user with this uid
				uMap.put(uid, u);
			}
			uMap.get(uid).addRating(getAllMovies().get(Integer.parseInt(sp[1])), Double.parseDouble(sp[2]),
					Long.parseLong(sp[3]));
		}
		li.close();
		return uMap;
	}

	@Override
	public void loadData(String movieFilename, String userFilename) {
		mMap = loadMovies(movieFilename);
		uMap = loadUsers(userFilename);
	}

	@Override
	public Map<Integer, Movie> getAllMovies() {
		if (mMap != null) {
			return mMap;
		}
		return new TreeMap<Integer, Movie>();
	}

	@Override
	public Map<Integer, User> getAllUsers() {
		if (uMap != null) {
			return uMap;
		}
		return new TreeMap<Integer, User>();
	}

	@Override
	public void trainModel(String modelFilename) {
		StringBuilder output = new StringBuilder();
		FileWriter outFile = null;
		try {
			File f = new File(modelFilename);
			outFile = new FileWriter(f);
		}
		catch (IOException e) {
			System.out.println("Unable to create file");
		}
		Map<Integer, Movie> movMap = getAllMovies();
		Map<Integer, User> usrMap = getAllUsers();
		int numUsr = usrMap.keySet().size();
		int numMov = movMap.keySet().size();
		double[] uMean = new double[numUsr]; // Mean rating for each user
		double s = 0; // Similarity
		double sNum = 0; // Numerator
		double sDenU = 0; // Denominator v
		double sDenV = 0; // Denominator v
		double sDen = 0; // Denominator
		int i = 0;
		int j = 0;
		double sum = 0;
		int count = 0;
		output.append("@NUM_USERS " + numUsr + "\n");
		output.append("@USER_MAP {");
		TreeSet<Integer> usrKeySet = new TreeSet<Integer>();
		usrKeySet.addAll(usrMap.keySet());
		TreeSet<Integer> movKeySet = new TreeSet<Integer>();
		movKeySet.addAll(movMap.keySet());

		i = 0;
		for (int uKey : usrKeySet) {
			output.append(i + "=" + uKey + ", ");
			i++;
		}
		output.delete(output.length() - 2, output.length());
		output.append("}\n@NUM_MOVIES " + numMov + "\n@MOVIE_MAP {");
		i = 0;
		for (int mKey : movKeySet) {
			output.append(i + "=" + mKey + ", ");
			i++;
		}
		output.delete(output.length() - 2, output.length());
		output.append("}\n@RATING_MATRIX\n");
		i = 0;
		for (int uKey : usrMap.keySet()) {
			sum = 0;
			count = 0;
			for (int mKey : movMap.keySet()) {
				if (usrMap.get(uKey).ratings.containsKey(mKey)) {
					output.append(usrMap.get(uKey).ratings.get(mKey).rating + " ");

				// Aggregate the rating of the current user
					sum += usrMap.get(uKey).ratings.get(mKey).rating;
					count++;
				}
				else {
					output.append("0.0 ");
				}
			}
			
			uMean[i] = sum / count;
			output.append(uMean[i] + " \n");
			i++;
		}
		output.append("@USERSIM_MATRIX\n");

		i = 0;
		j = 0;
		for (int u : usrKeySet) {
			j = 0;
			for (int v : usrKeySet) {
				s = 0;
				sNum = 0;
				sDenU = 0;
				sDenV = 0;
				// Loop through the set of the movie that both users have rated
				TreeSet<Integer> intersectMid = new TreeSet<Integer>();
				intersectMid.addAll(Sets.intersection(uMap.get(u).ratings.keySet(), uMap.get(v).ratings.keySet()));
				for (int m : intersectMid) {
					sNum += (uMap.get(u).ratings.get(m).rating - uMean[i]) * (uMap.get(v).ratings.get(m).rating - uMean[j]);
				//	sDenU += Math.pow((uMap.get(u).ratings.get(m).rating - uMean[i]), 2.0);
					sDenU += (uMap.get(u).ratings.get(m).rating - uMean[i]) * (uMap.get(u).ratings.get(m).rating - uMean[i]);
				//	sDenV += Math.pow((uMap.get(v).ratings.get(m).rating - uMean[j]), 2.0);
					sDenV += (uMap.get(v).ratings.get(m).rating - uMean[j]) * (uMap.get(v).ratings.get(m).rating - uMean[j]);
				}

				//sDen = Math.sqrt(sDenU) * Math.sqrt(sDenV);
					sDen = Math.sqrt(sDenU * sDenV);
				

				if (sDen != 0) {
					s = sNum / sDen;
				}
				else {
					s = 0.0;
				}
				if (u == v) {
					s = 1;
				}
				output.append(s + " ");
				j++;
			}
			output.append("\n");
			i++;
		}
		try {
			outFile.write(output.toString());
			outFile.close();
		}
		catch (IOException e) {
		}

	}

	@Override
	public void loadModel(String modelFilename) {
		File modelFile = new File(modelFilename);
		LineIterator li = null;
		int numUsr;
		int numMov;

		int i = 0;
		try {
			li = FileUtils.lineIterator(modelFile);
		}
		catch (IOException e) {
		}

		Pattern p = Pattern.compile("(\\d+)$");
		String line = li.nextLine();
		Matcher match = p.matcher(line);
		match.find();
		numUsr = Integer.parseInt(match.group(1));
		usrKeyMap = HashBiMap.create(numUsr);
		Pattern p2 = Pattern.compile("=\\d+");
		line = li.nextLine();
		match = p2.matcher(line);
		while (match.find()) {
			getUsrKeyMap().put(i, Integer.parseInt(line.substring(match.start() + 1, match.end())));
			i++;
		}
		line = li.nextLine();
		match = p.matcher(line);
		match.find();
		numMov = Integer.parseInt(line.substring(match.start(), match.end()));
		movKeyMap = HashBiMap.create(numMov);
		line = li.nextLine();
		match = p2.matcher(line);
		i = 0;
		while (match.find()) {
			movKeyMap.put(i, Integer.parseInt(line.substring(match.start() + 1, match.end())));
			i++;
		}

		li.nextLine(); // @RATING_MATRIX

		ratingMatrix = new double[numUsr][numMov];
		usrMeanRating = new double[numUsr];
		Pattern p3 = Pattern.compile("(-)?\\d+\\.\\d+");
		for (int usr = 0; usr < numUsr; usr++) {
			line = li.nextLine();
			match = p3.matcher(line);
			for (int mov = 0; mov < numMov; mov++) {
				match.find();
				ratingMatrix[usr][mov] = Double.parseDouble(line.substring(match.start(), match.end()));
			}
			match.find();
			usrMeanRating[usr] = Double.parseDouble(line.substring(match.start(), match.end()));
		}

		similarityMatrix = new double[numUsr][numUsr];
		li.nextLine(); // @USERSIM_MATRIX

		for (int usr1 = 0; usr1 < numUsr; usr1++) {
			line = li.nextLine();
			match = p3.matcher(line);
			for (int usr2 = 0; usr2 < numUsr; usr2++) {
				match.find();
				getSimilarityMatrix()[usr1][usr2] = Double.parseDouble(line.substring(match.start(), match.end()));
			}
		}
	}

	@Override
	public double predict(Movie m, User u) {
		double num = 0; // Numerator
		double den = 0; // Denominator
		int uid = u.uid;
		int uPos = 0;
		int mid = m.mid;
		int mPos = 0;
		double pRating = 0;

		if (getUsrKeyMap().values().contains(uid)) {
			uPos = getUsrKeyMap().inverse().get(uid);
			mPos = getMovKeyMap().inverse().get(mid);
			for (int r = 0; r < getUsrKeyMap().size(); r++) {
				if (ratingMatrix[r][mPos] >= 0.5 && ratingMatrix[r][mPos] <= 5.0 && r != uPos) {
					num += (getSimilarityMatrix()[uPos][r] * (ratingMatrix[r][mPos] - usrMeanRating[r]));
					den += Math.abs(getSimilarityMatrix()[uPos][r]);
				}
			}
			if (den == 0) {
				pRating = usrMeanRating[getUsrKeyMap().inverse().get(uid)];
			}
			else {
				pRating = usrMeanRating[getUsrKeyMap().inverse().get(uid)] + (num / den);
			}

			// 0.5 <= r <= 5.0 Bound
			if (pRating > 5) {
				pRating = 5.0;
			}
			else if (pRating < 0.5) {
				pRating = 0.5;
			}

			return pRating;
		}
		else {
			return u.getMeanRating();
		}

	}

	@Override
	public List<MovieItem> recommend(User u, int fromYear, int toYear, int K) {
		List<MovieItem> mList = new ArrayList<MovieItem>();
		
		// Loop through all the mid
		for (int mKey : movKeyMap.values()) {
			if (mMap.get(mKey).year >= fromYear && mMap.get(mKey).year <= toYear) {
				mList.add(new MovieItem(mMap.get(mKey), predict(mMap.get(mKey), u)));	// add the movie in the year range
			}
		}
		Collections.sort(mList);
		if (mList.size() < K) {
			return mList;
		}
		return mList.subList(0, K);
	}

	public double[][] getRatingMatrix() {
		return ratingMatrix;
	}

	public void setRatingMatrix(double[][] rm) {
		ratingMatrix = rm;
	}

	public BiMap<Integer, Integer> getMovKeyMap() {
		return movKeyMap;
	}

	public double[] getUsrMeanRating() {
		return usrMeanRating;
	}

	public void setUsrMeanRating(double[] mr) {
		usrMeanRating = mr;
	}

	public static BiMap<Integer, Integer> getUsrKeyMap() {
		return usrKeyMap;
	}

	public static void setUsrKeyMap(BiMap<Integer, Integer> um) {
		usrKeyMap = um;
	}

	public static double[][] getSimilarityMatrix() {
		return similarityMatrix;
	}

	public static void setSimilarityMatrix(double[][] sm) {
		similarityMatrix = sm;
	}

	public void setMovKeyMap(BiMap<Integer, Integer> map) {
		movKeyMap = map;
	}

}
