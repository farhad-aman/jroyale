public abstract class Bot 
{
    private Deck deck;

    int difficulty;

    public Bot(Deck deck, int difficulty) 
    {
        this.deck = deck;
        this.difficulty = difficulty;
    }

    public int getDifficulty() 
    {
        return difficulty;
    }

    public Deck getDeck()
    {
        return deck;
    }
}
