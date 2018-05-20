/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class BinaryContextSearcher extends ContextSearcher {
    public BinaryContextSearcher(String filename)
    {
        super(filename);
    }

    /**
     *
     * @param query
     * @param window
     * @return
     */
    @Override
    public String find(Word query, int window)
    {
        String result=null;
        int low=0;
        int high = sortedWords.size() - 1;
        int mid;
        while(result==null&&low<=high)
        {
            mid = low+((high-low)/2);
            int compare =Word.compareTo(query,sortedWords.get(mid));
            if(compare == 0){
                while (sortedWords.get(mid-1).equals(query)) {
                    mid--;
                }
                result = getSnippet(sortedWords.get(mid),window);
            }
            else if(compare > 0)
                low = mid+1;
            else
                high = mid-1;
        }
        return result;





    }

}

