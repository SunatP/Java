import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MyTester{
    public static void main(String[] args){

        String testMode = "small";
        boolean wantTrain = false;

        StopWatch clock = new StopWatch();
        clock.start();
        System.out.println("Training the recommender...");
        BaseMovieRecommender r = new SimpleMovieRecommender();
        try {
            r.loadData(  "small/movies.csv",  "small/users.train.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!wantTrain){
            // r.loadModel(testMode + "/" + testMode + ".simple.model");
            r.loadModel("small/small.simple.model");
            Evaluator e = new Evaluator(r, "small/users.test.csv");
            e.evaluate(2010, 2015, 20, "TestArea/"+ testMode + ".simple.result");
            System.out.println(e.toString());
        }else{
            r.trainModel("TestArea/"+ testMode + ".small.model");
        }

        clock.stop();
        System.out.println("Time Used: "+clock.toString());

        if(false){
            for(Integer ID : r.getAllUsers().keySet()){
                System.out.println(r.getAllUsers().get(ID));
            }
        }

        if(false){
            for(Double userMean : ((SimpleMovieRecommender) r).getMeanRatingMatrix().values()){
                System.out.println(userMean);
            }
        }

        if(false){
            writeSimilarityMatrix(r);
        }
    }

    private static void writeSimilarityMatrix(BaseMovieRecommender r){
        ArrayList<Integer> userArrayList = new ArrayList<>(r.getAllUsers().keySet());
        StringBuilder matrixContentBuilder = new StringBuilder();
        Collections.sort(userArrayList);
        for(Integer uid1 : userArrayList){
            for(Integer uid2 : userArrayList){
                matrixContentBuilder.append(String.format("% 9f", ((SimpleMovieRecommender) r).getSimilarityMatrix().get(uid1).get(uid2)) + "\n");
            }
            //matrixContentBuilder.append("\n");
        }

        try{
            FileUtils.writeStringToFile(new File("TestArea/sim_matrix.txt"), matrixContentBuilder.toString());
        }catch(IOException k){
            k.printStackTrace();
            System.out.print( "error" );
        }
    }
}
