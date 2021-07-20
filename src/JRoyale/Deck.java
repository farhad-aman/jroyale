import java.util.HashSet;

/**
 * this class contains the deck for a player or bot
 * @version 1.0
 */
public class Deck 
{
    /**
     * the list of cards in deck
     */
    private HashSet<Card> cards;
    
    /**
     * creates a new empty deck
     */
    public Deck()
    {
        this.cards = new HashSet<>();
    }

    /**
     * creates a new deck with given list of cards
     * @param cards
     */
    public Deck(HashSet<Card> cards)
    {
        if(cards.size() == 8)
        {
            this.cards = cards;
        }
        else
        {
            cards = new HashSet<>();
        }
    }

    /**
     * adds a new card to the deck
     * @param card
     */
    public void addCard(Card card)
    {
        if(cards.size() < 8)
        {
            cards.add(card);
        }
    }

    /**
     * @return list of cards
     */
    public HashSet<Card> getCards() 
    {
        return cards;
    }

    /**
     * sets cards of the deck
     * @param cards
     */
    public void setCards(HashSet<Card> cards)
    {
        if(cards.size() == 8)
        {
            this.cards = cards;
        }
    }

    @Override
    public String toString() 
    {
        String s = "";
        for(Card c : cards)
        {
            s += c.getId() + " - ";
        }
        return s;
    }
}
