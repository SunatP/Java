import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SimpleMovieRecommender implements BaseMovieRecommender{

    Map<Integer, Movie> movies;
    static Map<Integer, User> users;

    /**
     * Fetches Movies from a file
     * @param movieFilename String of relative path and Name of the file
     * @return Map of MovieID and Movie
     */
    @Override
    public Map<Integer, Movie> loadMovies(String movieFilename){
        Map<Integer, Movie> moviesMap = new HashMap<>();    /* The Map */

        //Reading file by using Internal Library
        String contentBuilder = "";//                       Path is find directory
            //                           Files from libs    Path from libs
        try{//                         |--------(1)-------||----(2)--|  String name
            //                                                       |------(3)-----|
            contentBuilder = new String(Files.readAllBytes(Paths.get(movieFilename)));
            // BufferedReader br = new BufferedReader( new FileReader( movieFilename ) ); // replace by using String and read all file by Files IO
        }catch(IOException e){
            e.printStackTrace();
        }
        String[] lines = contentBuilder.split("\n");        /* Separating Each lines into String */

        //                                                  Pattern for CSV; For example
        //                                        101529,"Brass Teapot, The (2012)",Comedy|Fantasy|Thriller
        //                                           (1) , |---(2)---|    (3)       ||----(4)----|
        Pattern eachLinePattern = Pattern.compile("(\\d+),[\\\"?]?(.*)\\s\\((\\d+)\\)[\\\"?]?,(.+)");

        //Fields Variable for Movie Datatype
        int movieID, year;
        String mTitle, mGenres;

        //Pattern for the forth group of eachLinePattern; For example
        //comedy|Fantasy|Thriller
        //  (1)    (2)      (3)
        Pattern categoriesPattern = Pattern.compile("([^|]+)");
        Matcher matcher;

        for(int i = 1; i < lines.length; i++){
            matcher = eachLinePattern.matcher(lines[i]);
            if(!matcher.find()){
                continue;
            }

            movieID = Integer.parseInt(matcher.group(1));       /* Assigns group 1 into movieID field variable */
            mTitle = matcher.group(2);       /* Assigns group 2 into movieTitle field variable */
            year = Integer.parseInt(matcher.group(3));      /* Assigns group 3 into year field variable */
            mGenres = matcher.group(4);     /* Assigns group 4 into movieGenres field variable */
            moviesMap.put(movieID, new Movie(movieID, mTitle, year));    /* Stores all fields into the Map (the Key is movieID, and the value is a movie object) */

            matcher = categoriesPattern.matcher(mGenres);       /* Sets the pattern for the Categories String */

            if(mGenres.equals("(no genres listed)")){   /* If there is no categories to be assigned */
                continue;
            }

            //Assigns each categories to each Movie Object by using its movieID
            while(matcher.find()){
                moviesMap.get(movieID).addTag(matcher.group());        //now moviesMap will get MovieID to addTag by using Matcher
            }
        }

        return moviesMap;
    }

    /**
     * Fetches Users from a file
     * @param userFilename String of relative path and Name of the file
     * @return Map of UserID and User
     */
    @Override
    public Map<Integer, User> loadUsers(String userFilename){
        Map<Integer, User> usersMap = new HashMap<>();
        //Reading file by using StringBuilder (Unused)
        StringBuilder contentBuilder = new StringBuilder();
        // This method to read file is different from what is implemented loadMovies()
        // Because of inconsistencies of the \r and \n in each line (Some line has only "\n", some has "\r\n")
        try(Stream<String> stream = Files.lines(Paths.get(userFilename), StandardCharsets.UTF_8)){
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }catch(IOException e){
            e.printStackTrace();
        }
        String[] line = contentBuilder.toString().split("\n");        /* Separating Each lines into String */
        //Pattern for the each line of Rating.csv; For example
        //                           668,   108940, 2.5, 1391840917
        //                           (1)      (2)    (3)  (4)
        String eachLinePattern = "([\\d]+),([\\d]+),(.*),(\\d+)";
        Pattern pattern = Pattern.compile(eachLinePattern);

        //Fields Variable for Rating DataType
        int userID;
        int movieId;
        double rating;
        long timestamp;

        for(int i = 1; i < line.length; i++){
            Matcher m = pattern.matcher(line[i]);
            if(m.matches()){
                userID = Integer.parseInt(m.group(1));      /*  Assigns group 1 into userID field variable */
                movieId = Integer.parseInt(m.group(2));     /* Assigns group 2 into movieId field variable */

                boolean isAvailableInDataBase = movies.containsKey(movieId) && movieId == movies.get(movieId).getID();
                if(!isAvailableInDataBase){       /* If movie ID is not in the map skip it */
                    continue;
                }
                rating = Double.parseDouble(m.group(3));        /* Assigns group 3 into rating field variable */
                timestamp = Long.parseLong(m.group(4));      /* Assigns group 4 into timestamp field variable */

                if(rating > 0){
                    if(usersMap.get(userID) == null){
                        usersMap.put(userID, new User(userID));
                    }
                    usersMap.get(userID).addRating(movies.get(movieId), rating, timestamp);
                }
            }
        }
        return usersMap;

        // You can use this comment to load User Data
//         Map<Integer,User> uMap = new HashMap<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(userFilename));
//            String line = "";
//            String pstr = "([0-9]+),([0-9]+),([0-9]\\.[0-9]),([0-9]+)"; // regex
//            Pattern p = Pattern.compile(pstr);
//            Matcher m;
//            br.readLine();
//            while ((line = br.readLine()) != null) {
//                m = p.matcher(line);
//                if (m.matches()) {
//                    int uid = Integer.parseInt(m.group(1));
//                    int mid = Integer.parseInt(m.group(2));
//                    double rating =  Double.parseDouble(m.group(3));
//                    long timestamp = Long.parseLong(m.group(4));
//                    if(uMap.get(uid) == null) {
//                        uMap.put(uid , new User(uid));
//                        //usersMap.get(userID).addRating(movies.get(movieId), rating, timestamp);
//                        uMap.get(uid).addRating( movies.get( uid ),rating,timestamp );
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void loadData(String movieFilename, String userFilename){
        movies = loadMovies(movieFilename); // movies map will load data to store in Map
        users = loadUsers(userFilename); // users map will load data to store in Map
    }

    @Override
    public Map<Integer, Movie> getAllMovies() {
        if (movies != null) { // if movie not equal to null
            return movies;    // return to Map
        }
        return new TreeMap<>(); // if movie null create TreeMap to sort
        // return movies  you can use this return if you don't need to sort
    }

    @Override
    public Map<Integer, User> getAllUsers(){
        if (users != null) { // if user not equal to null
            return users;    // return to Map
        }
        return new TreeMap<>(); // to sort by using Treemap
        // return users you can use this return if you don't need to sort
    }
//------------------------------------------------------------------------------ Train Model -------------------------------------------------------------------------------//
    public void userMap() // use in trainmodel method
    {
        StringBuilder modelBuilder = new StringBuilder(); // Create StringBuilder to make text


        // Sorts the elements inside the set of UserID
        ArrayList<Integer> userIDArrayList = new ArrayList<>(users.keySet());
        Collections.sort(userIDArrayList);
        int countUser = 0;
        for (int i = 0; i < userIDArrayList.size(); i++) {
            Integer userID = userIDArrayList.get( i );
            modelBuilder.append( countUser++ + "=" + users.get( userID ).uid ); // Like a Print data out
            if (!userID.equals( userIDArrayList.get( userIDArrayList.size() - 1 ) )) {
                modelBuilder.append( ", " ); // add comma to split data
            } else {
                modelBuilder.append( "}\n" );// add bracket and new line
            }

        }
    }

    public void movieMap() // use in trainmodel method
    {
        StringBuilder modelBuilder = new StringBuilder();
        ArrayList<Integer> movieIDArrayList = new ArrayList<>(movies.keySet()); // movies Map will allow to use keySet in ArrayList by create new one
        Collections.sort(movieIDArrayList);
        int countMovie = 0;
        for (int i = 0; i < movieIDArrayList.size(); i++) {
            Integer movieID = movieIDArrayList.get( i );
            modelBuilder.append( countMovie++ + "=" + movies.get( movieID ).mid );
            if (!movieID.equals( movieIDArrayList.get( movieIDArrayList.size() - 1 ) )) {     /* If the Movie ID is not the last element of the sorted MovieID ArrayList */
                modelBuilder.append( ", " );
            } else {
                modelBuilder.append( "}\n" );
            }
        }
    }

    public void RatingMatrix() // use in trainmodel method
    {
        StringBuilder modelBuilder = new StringBuilder();
        ArrayList<Integer> userIDArrayList = new ArrayList<>(users.keySet());
        ArrayList<Integer> movieIDArrayList = new ArrayList<>(movies.keySet()); // movies Map will allow to use keySet in ArrayList by create new one
        Collections.sort(userIDArrayList);
        for (int i = 0; i < userIDArrayList.size(); i++) {
            Integer userID = userIDArrayList.get( i );
            //System.out.print("UserID: " + userID);
            for (int j = 0; j < movieIDArrayList.size(); j++) {
                Integer movieID = movieIDArrayList.get( j );
                if (users.get( userID ).ratings.keySet().contains( movieID )) {       /* If the User does rate the Movie */
                    modelBuilder.append( users.get( userID ).ratings.get( movieID ).rating + " " );
                } else {      /* If the User does not rate the Movie */
                    modelBuilder.append( "0.0 " );
                }
            }
            // Appends the Mean of Rating which a user has given
            modelBuilder.append( users.get( userID ).getMeanRating() + "\n" );
        }
    }

    public void UserSimMatrix() // use in trainmodel method
    {
        StringBuilder modelBuilder = new StringBuilder();
        ArrayList<Integer> userIDArrayList = new ArrayList<>(users.keySet());
        ArrayList<Integer> movieIDArrayList = new ArrayList<>(movies.keySet()); // movies Map will allow to use keySet in ArrayList by create new one
        Collections.sort(userIDArrayList);
        for (int i = 0; i < userIDArrayList.size(); i++) {
            Integer userID_I = userIDArrayList.get( i );
            for (int j = 0; j < userIDArrayList.size(); j++) {
                Integer userID_J = userIDArrayList.get( j );
                modelBuilder.append( similarity( userID_I, userID_J ) + " " );
            }
            modelBuilder.append( "\n" );
        }
    }

    @Override
    public void trainModel(String modelFilename){
        // Instantiates StringBuilder, the StringBuilder acts as a container for the Whole String
        StringBuilder modelBuilder = new StringBuilder();


        // Sorts the elements inside the set of UserID
        ArrayList<Integer> userIDArrayList = new ArrayList<>(users.keySet()); // Create ArrayList to store data
        Collections.sort(userIDArrayList); // ArrayList will allow to sort by using Collections sort
        // Appends the Total user number
        modelBuilder.append("@NUM_USERS " + users.size() + "\n");

        // Appends the User map
        modelBuilder.append("@USER_MAP {");
        userMap(); // This is method can be remove

        // Sorts the elements inside the set of MovieID
        ArrayList<Integer> movieIDArrayList = new ArrayList<>(movies.keySet()); // movies Map will allow to use keySet in ArrayList by create new one
        Collections.sort(movieIDArrayList);

        // Appends the Total movie number
        modelBuilder.append("@NUM_MOVIES "+ movies.size() + "\n");

        // Appends the Movie map
        modelBuilder.append("@MOVIE_MAP {");
        movieMap(); // This is method can remove

        // Appends the Rating Matrix
        // From Matrix Let's; Row: Each User
        //                    Col: Each Movie (+ There is Mean Rating of the User after the last movie rating)
        modelBuilder.append("@RATING_MATRIX\n");
        RatingMatrix(); // This is method can remove

        // Appends
        modelBuilder.append("@USERSIM_MATRIX\n");
        UserSimMatrix(); // This is method to calculate USimMatrix it's can be remove

        try{
            FileUtils.writeStringToFile(new File(modelFilename), modelBuilder.toString()); // Write data output to specific file
        }catch(IOException e){
            e.printStackTrace(); // if cannot try go catch and print error point
        }
    }

//------------------------------------------------------------------------------ Train Model -------------------------------------------------------------------------------//

    @Override
    public void loadModel(String modelFilename){
        String modelContent = null;
        try{
            modelContent = FileUtils.readFileToString(new File(modelFilename)); // Use FileUtils to Read all String data
        }catch(IOException e){
            e.printStackTrace();
        }
        // Find the line with "@USERSIM_MATRIX", then get its the line number of the line below it (Get the first line of the matrix)
        int similarityMatrixFirstLine = 0;
        String[] modelContentLines = modelContent.split("\n");
        for(int i = 0; i < modelContent.length(); i++){
            if(modelContentLines[i].equals("@USERSIM_MATRIX")){
                similarityMatrixFirstLine = i + 1;
                break;
            }
        }

        // Sort the userID by Ascending Order
        ArrayList<Integer> userIDArrayList = new ArrayList<>(users.keySet());
        Collections.sort(userIDArrayList);

        String similarityLine;
        for (int i = 0; i < userIDArrayList.size(); i++) {
            Integer userID = userIDArrayList.get( i );      /* Iterates through the array of User ID */
            int j = 0;
            similarityLine = modelContentLines[similarityMatrixFirstLine++];
            String[] similarityLine_Array = similarityLine.split( " " );

            similarityMatrix.put( userID, new HashMap<>() );
            for (int i1 = 0; i1 < userIDArrayList.size(); i1++) {
                int userID_nested = userIDArrayList.get( i1 );
                similarityMatrix.get( userID ).put( userID_nested, Double.valueOf( similarityLine_Array[j++] ) );
            }
        }
        // Find the line with "@USERSIM_MATRIX", then get its the line number of the line below it (Get the first line of the matrix)
        int ratingMatrixFirstLine = 0;
        for(int i = 0; i < modelContent.length(); i++){
            if(modelContentLines[i].equals("@RATING_MATRIX")){
                ratingMatrixFirstLine = i + 1;
                break;
            }
        }

        // Sort the movieID by Ascending Order
        ArrayList<Integer> movieIDArrayList = new ArrayList<>(movies.keySet());
        Collections.sort(movieIDArrayList);

       String ratingLine;
        for (int i = 0; i < userIDArrayList.size(); i++) {
            Integer userID = userIDArrayList.get( i );      /* Iterates through the array of Movie ID */
            ratingLine = modelContentLines[ratingMatrixFirstLine++];
            String[] ratingLine_Array = ratingLine.split( " " );

            ArrayList<Double> ratingArrayList = new ArrayList<>();
            for (int k = 0; k <= movies.size(); k++) {
                ratingArrayList.add( Double.valueOf( ratingLine_Array[k] ) );
            }
            meanRatingMatrix.put( userID, ratingArrayList.get( ratingArrayList.size() - 1 ) );
        }
    }

    private HashMap<Integer, HashMap<Integer, Double>> similarityMatrix = new HashMap<>();
    //      User ID, Map of   UserID, Similarity Value
    // The map to store value of similarity read from the file
    private HashMap<Integer, Double> meanRatingMatrix = new HashMap<>();
    //              UserID,    Mean Rating Value
    public HashMap<Integer, HashMap<Integer, Double>> getSimilarityMatrix(){
        return similarityMatrix;
    }

    public HashMap<Integer, Double> getMeanRatingMatrix(){
        return meanRatingMatrix;
    }

    @Override
    public double predict(Movie movie, User user){
        //TODO: !!! FIX THIS !!! - In small testcase, there is an error when compared to given result!!
        double result;
        double remainder = 0;
        double denominator = 0;
        double similarity;

        if(meanRatingMatrix.get(user.uid) != null){
            for(Integer userID : users.keySet()){
                if(userID != user.uid && users.get(userID).ratings.keySet().contains(movie.mid)){
                    User currentUser = users.get(userID);
                    similarity = similarityMatrix.get(user.uid).get(currentUser.uid);
                    remainder += similarity * (currentUser.ratings.get(movie.mid).rating - meanRatingMatrix.get(currentUser.uid));
                    denominator += Math.abs(similarity);
                }
            }

            if(denominator == 0){
                result = meanRatingMatrix.get(user.uid);
            }else{
                result = meanRatingMatrix.get(user.uid) + (remainder / denominator);
            }

            if(result > 5){
                result = 5.0;
            }else if(result < 0.5){
                result = 0.5;
            }
            return result;
        }else{
            return user.getMeanRating();
        }
    }

    @Override
    public List<MovieItem> recommend(User u, int fromYear, int toYear, int K){ // in page 3 of PDF file it's about reccomend
        List<MovieItem> movieItemList = new ArrayList<>();
        for(Integer movieID : movies.keySet()){ // step 2 For each movie 𝑖 ∈ 𝐼∗, compute 𝑝 u,i.
            if(movies.get(movieID).year >= fromYear && movies.get(movieID).year <= toYear){
                movieItemList.add(new MovieItem(movies.get(movieID), predict(movies.get(movieID), u))); //step 1 Collect all the movie released during fromYear to toYear à 𝐼∗
            }
        }
        Collections.sort(movieItemList); // step 3 Rank the movies
        if(movieItemList.size() > K){ // if in step 4
            movieItemList.subList(K, movieItemList.size() - 1).clear(); // step 4 If|𝐼∗| > K, return the top K movies.Otherwise,return all the movies.
        }if(movieItemList.size() < K)
        {
            return movieItemList;
        }
        return movieItemList.subList( 0,K );
    }

    private double similarity(int userID1, int userID2){

        //TODO: FIXED
        double similarity;

        User u1 = users.get(userID1);
        User u2 = users.get(userID2);
        if(userID1 == userID2){
            return similarity = 1;
        }



        TreeSet<Integer> commonRatedMovies = new TreeSet<>();
        commonRatedMovies.addAll(Sets.intersection(u1.ratings.keySet(), u2.ratings.keySet()));

        //Find the remainder
        double remainder = 0;
        double denominatorUser1 = 0;
        double denominatorUser2 = 0;
        for(int movieID : commonRatedMovies){
            remainder += ((u1.ratings.get(movieID).rating - u1.getMeanRating()) * (u2.ratings.get(movieID).rating - u2.getMeanRating()));
            denominatorUser1 += Math.pow(u1.ratings.get(movieID).rating - u1.getMeanRating(),2); //* (u1.ratings.get(movieID).rating - u1.getMeanRating());
            denominatorUser2 += Math.pow(u2.ratings.get(movieID).rating - u2.getMeanRating(),2); // * (u2.ratings.get(movieID).rating - u2.getMeanRating());
        }
        double denominator = (Math.sqrt(denominatorUser1 * denominatorUser2));
        if(denominator != 0){
            similarity = remainder / denominator;
        }else{
            similarity = 0.0;
        }
        return similarity;
    }

    public ArrayList<Double> ratingToArrayList(Collection<Rating> ratingSet){
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        for(Rating rating : ratingSet){
            doubleArrayList.add(rating.rating);
        }
        return doubleArrayList;
    }
}
