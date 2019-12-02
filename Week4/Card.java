
package card;
/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class Card {
public String cardNotation;
public String card;
private String N1;
private String N2;

    public Card (String notation)
    {
        cardNotation = notation.substring(0,notation.length()-1);
        card = notation.substring(notation.length()-1);
    } 

     public String getDescription()
    {
         if(null == cardNotation)N1 = "Unknown" ;
        /*else if ("2A".equals(cardNotation))N1 = "Two Ace";
        else if ("3A".equals(cardNotation))N1 = "Three Ace";
        else if ("4A".equals(cardNotation))N1 = "Four Ace";
        else if ("5A".equals(cardNotation))N1 = "Five Ace";*/
        
        //else if ("2".equals(cardNotation))N1 = "Two";
        else switch (cardNotation) {
        case "2":
            N1 = "Two";
            break;
    /*else if ("2J".equals(cardNotation))N1 = "Two Jack";
    else if ("3J".equals(cardNotation))N1 = "Three Jack";
    else if ("4J".equals(cardNotation))N1 = "Four Jack";
    else if ("5J".equals(cardNotation))N1 = "Five Jack";
    else if ("2K".equals(cardNotation))N1 = "Two King";
    else if ("3K".equals(cardNotation))N1 = "Three King";
    else if ("4K".equals(cardNotation))N1 = "Four King";
    else if ("5K".equals(cardNotation))N1 = "Five King";
    else if ("2Q".equals(cardNotation))N1 = "Two Queen";
    else if ("3Q".equals(cardNotation))N1 = "Three Queen";
    else if ("4Q".equals(cardNotation))N1 = "Four Queen";
    else if ("5Q".equals(cardNotation))N1 = "Five Queen";
    else if ("2S".equals(cardNotation))N1 = "Two Spades";
    else if ("3S".equals(cardNotation))N1 = "Three Spades";
    else if ("4S".equals(cardNotation))N1 = "Four Spades";
    else if ("5S".equals(cardNotation))N1 = "Five Spades";*/
        case "3":
            N1 = "Three";
            break;
        case "4":
            N1 = "Four";
            break;
        case "5":
            N1 = "Five";
            break;
        case "J":
            N1 = "Jack";
            break;
        case "Q":
            N1 = "Queen";
            break;
        case "S":
            N1 = "Spades";
            break;
        case "6S":
            N1 = "Unknown";
            break;
        default:
            N1 = "Unknown" ;
            break;
    }
        
        if(card.equalsIgnoreCase("K"))
        {
            N2 = "King";
        }
        else if (card.equalsIgnoreCase("Q"))
        {
            N2 = "Queen";
        }
        else if (card.equalsIgnoreCase("A"))
        {
            N2 = "Ace";
        }
        else if(card.equalsIgnoreCase("J"))
        {
            N2 = "Jack";
        }
        else if(card.equalsIgnoreCase("S"))
        {
            N2 = "Spades";
        }
        //else if ("q".equals(cardNotation)) N1 = "Thank you! Good Bye!";
        else N2 = "Unknown" ;
        
        return N1+" " + N2;
    }
}
