import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
// Name: Sunat Praphanwong
// Student ID: 6088130
// Section: 1C

public class User implements Comparable<User>{
	public int uid = 0;
	Map<Integer,Rating> ratings = null;

	public User(int _id){

		this.uid = _id;
		ratings = new HashMap<>();

	}

	public int getID(){

		// YOUR CODE GOES HERE

		return uid;
	}


	@Override
	public int compareTo(User _uid) {
		return (new Integer(uid)).compareTo(_uid.uid);
	}
	@Override
	public int hashCode(){

		return Objects.hash(uid);
	}
}
