import java.io.UnsupportedEncodingException
import java.util.Vector
import java.util.regex.Matcher
import java.util.regex.Pattern
import org.apache.commons.codec.binary.Base64
/**
*
* @author Sunat Praphanwong 6088130
*/
internal class Tweet {
  var encrypted = false
  var tweetID:Long = -1		//e.g. 128590282278187009
  var timestamp = -1		//e.g. 20111024
  var text:String = null		//e.g. @BossGotCash: Shoutout to the guy who got world record of 1 million mileage on his Honda accord today.. Without changing engine, @AliMo103
  var uid:String = null	//e.g. @ephan331are
  // ------------------------- BONUS -----------------------------
  // include from start of line and character
  //GCleanText = GCleanText.replaceAll(":+","");
  // include comma and replace with ""
  // \d is mean for digit and \w for any word character ^ for start of line $ end of line 
  // everything of sqaure bracket and include digit number
  // everything in sqaure bracket
  val cleanText:String
  get() {
    val GCleanText = this.text
    GCleanText = GCleanText.replace(("@[^ ]+").toRegex(), "")
    GCleanText = GCleanText.replace(("^ ").toRegex(), "")
    GCleanText = GCleanText.replace(("#[^ ]+").toRegex(), "")
    GCleanText = GCleanText.replace(("http:[^ ]+").toRegex(), "")
    GCleanText = GCleanText.replace((",").toRegex(), "")
    GCleanText = GCleanText.replace(("[^\\d\\w]").toRegex(), " ")
    GCleanText = GCleanText.replace((" [\\d]+ ").toRegex(), "")
    GCleanText = GCleanText.replace(("[ ]+").toRegex(), " ")
    GCleanText = GCleanText.toLowerCase()
    return GCleanText
  }
  /**
	 * Return a XML representation of the tweet
	 * @return
	 */
  fun toXmlString():String {
    val str = StringBuilder()
    str.append("<tweet>\n")
    str.append("\t<tid>").append(tweetID).append("</tid>\n")
    str.append("\t<timestamp>").append(timestamp).append("</timestamp>\n")
    str.append("\t<uid>").append(uid).append("</uid>\n")
    str.append("\t<text>").append(text).append("</text>\n")
    str.append("</tweet>")
    return str.toString()
  }
  /**
	 * Return an encrypted tweet
	 * @return
	 */
  fun encrypt():Tweet {
    if (text == null) return null
    if (encrypted) return this
    val t = this.clone()
    t.text = Base64.encodeBase64String(text.toByteArray())
    t.encrypted = true
    return t
  }
  /**
	 * Return a decrypted tweet
	 * @return
	 */
  fun decrypt():Tweet {
    if (text == null) return null
    if (!encrypted) return this
    val t = this.clone()
    val decrypted = Base64.decodeBase64(text)
    try
    {
      t.text = String(decrypted, "UTF-8")
      t.encrypted = false
      return t
    }
    catch (e:UnsupportedEncodingException) {
      e.printStackTrace()
    }
    return null
  }
  /**
	 * Return a copy of this tweet
	 */
  fun clone():Tweet {
    val t = Tweet()
    t.encrypted = this.encrypted
    t.text = this.text
    t.timestamp = this.timestamp
    t.tweetID = this.tweetID
    t.uid = this.uid
    return t
  }
  companion object {
    /**
	 * parseTweet() is a static method that takes a String representation of a tweet.
	 * It then parses and extracts information from such a string, puts the information
	 * in a new Tweet object and returns it. If the input string is null, malformed, or
	 * does not contain all the tweet elements, it simply returns null.
	 * A valid input string has the following format:
	 * <tweetID>,<timestamp>,�[<uid>]<text>�
	 * Example of the input String:
	 * 128590282278187009,20111024,"[@ephan331are]@BossGotCash: Shoutout to the guy who got world record of 1 million mileage on his Honda accord today.. Without changing engine, @AliMo103"
	 */
    fun parseTweet(str:String):Tweet {
      if (str == null) return null
      val pStr = "([0-9]+),([0-9]+),\"\\[(@[^\\]]+)\\]([^\"]+)"	//<--Implement your regular expression here
      val p = Pattern.compile(pStr)
      val m = p.matcher(str)
      val t:Tweet = null
      if (m.find())
      {
        t = Tweet()
        t.tweetID = java.lang.Long.parseLong(m.group(1))
        t.timestamp = Integer.parseInt(m.group(2))
        t.uid = m.group(3)
        t.text = m.group(4)
        //INSERT YOUR CODE HERE
        //e.g. t.tweetID = Long.parseLong(m.group(1)); ...
      }
      return t
    }
  }
  //--------------------------------------------------------------
}
object Tester {
  @JvmStatic fun main(args:Array<String>) {
    val data = ("128590282278187009,20111024,\"[@ephan331are]@BossGotCash: Shoutout to the guy who got world record of 1 million mileage on his Honda accord today.. Without changing engine, @AliMo103\"\n"
                + "128623927290372100,20111025,\"[@HoehnInfiniti]Hahaha just saw an Asian with camo seat covers in a Honda accord\"\n"
                + "128681224905560064,20111025,\"[@MichaelG_101]@CarlaLicavoli RT @Yahoo: 1990 Honda Accord goes 1 million miles. Owner, a former mechanic, swears transmission... http://t.co/i7MFXHqU\"\n"
                + "128681480309321728,20111025,\"[@Armaiti_Iris]@funkmasterflex: #IFWT Man Shows Stamina By Driving His Honda Accord For One Million Miles http://t.co/Vt2ujcgz I got the same car lol\"\n"
                + "128815286773227520,20111025,\"[@ashmichalak]Just heard on the radio a guy in Maine hit 1 Million miles on his Honda Accord! Ok you and me Joseph, we know our target! 750k to go!\"\n"
                + "128832896390803457,20111025,\"[@AndrewStRomain]White Honda accord...I drive that. The sport of baseball...I play that. Chicken Alfredo my mom makes...I eat that #muteSpitah\"\n"
                + "128833282073821185,20111025,\"[@WestCoastISH]@andrewmarks21 white Honda accord..I drive that. The sport of baseball...I play that. Chicken Alfredo my mom makes...I eat that #muteSpitah\"\n"
                + "128983018374299648,20111025,\"[@hollatmike]GUEST POST: 1990 Honda Accord Owner in Ohio Tops 1 Million Miles [Video] http://t.co/cQM81tpz\"\n"
                + "197789132511129600,20120502,\"[@Tyler_Ferrari]Co-Presidents of #AccordGang Pocket Chapter lol ?#wet #honda #accord #dubs @yeff_kt @VALHEEM! http://t.co/vAU10qOs\"\n"
                + "197826490900029440,20120502,\"[@davidciani]Warlord of rap that'll bash you with a two by four board and smash into your Honda accord with a four door ford\"\n"
                + "197830290423554049,20120502,\"[@courtneykenny]Just raced a black guy in my diesel. Safe to say his little Honda Accord didn't stand a chance #SmokedHim\"\n"
                + "197846717494853633,20120503,\"[@MyLifeIzzaMovie]@nicoleematteo I got a 2009 Honda accord coupe !! http://t.co/vJBJg2fL\"\n"
                + "150669766858772480,20111224,\"[@To_Seize]Damn this guy cant park he drives a honda accord and he's takin up 2 parking spots smh\"\n"
                + "197898526599020544,20120503,\"[@infinitinews]FOR SALE: 2000 Honda Accord EX, 108K Miles, $6900. Details @ http://t.co/SVzndAhD\"\n"
                + "150758963502264320,20111225,\"[@jwizzy24]Looking at cars online :) debating between a 2012 maxima or a 2012 honda accord coupe hmmm #newwheels\"\n")
    val lines = data.split(("\\n").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
    val tweets = Vector<Tweet>()	//
    for (line in lines)
    {
      line = line.trim({ it <= ' ' })
      if (line.isEmpty()) continue
      val t = Tweet.parseTweet(line)
      if (t != null) tweets.add(t)
    }
    println(tweets.get(3).toXmlString())
    println(tweets.get(3).encrypt().toXmlString())
    println(tweets.get(3).encrypt().decrypt().toXmlString())
    //For Bonus
    println(tweets.get(8).cleanText)
    println(tweets.get(13).cleanText)
  }
}
