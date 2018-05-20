/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class LinearContextSearcher extends ContextSearcher {

    public LinearContextSearcher(String filename) {
        super(filename);
    }

    @Override
    public String find(Word query, int window)
    {
            for(int i=0;i<sortedWords.size();i++)
            {

                if(Word.compareTo(query,sortedWords.get(i)) == 0)
                {

                    return getSnippet(sortedWords.get(i),window);
                }

            }

                return null;

    }
}
