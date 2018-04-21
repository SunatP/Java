/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.util.HashSet;
import java.util.Set;

public class Movie {
    public int mid = -1;
    public String title = null;
    public int year = -1;
    public Set<String> tags = null;
    public Movie(int _mid, String _title, int _year)
    {
        mid = _mid;
        title = _title;
        year = _year;
        tags = new HashSet<>();
    }

    public int getID() {
        return mid;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTag(String tag)
    {
        tags.add(tag);
    }

    public String toString()
    {
        return "[mid: "+mid+":"+title+" ("+year+") "+tags+"]";
    }

}
