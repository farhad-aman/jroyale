import java.util.HashSet;

public class Deck 
{
    private HashSet<Card> cards;
    
    public Deck()
    {
        this.cards = new HashSet<>();
    }

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

    public void addCard(Card card)
    {
        if(cards.size() < 8)
        {
            cards.add(card);
        }
    }

    public HashSet<Card> getCards() {
        return cards;
    }

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
