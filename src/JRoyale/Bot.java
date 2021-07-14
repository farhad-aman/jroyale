public abstract class Bot 
{
    private final String username;

    private Deck deck;

    int difficulty;

    public Bot(String username, Deck deck, int difficulty) 
    {
        this.username = username;
        this.deck = deck;
        this.difficulty = difficulty;
    }

    public String getUsername()
    {
        return username;
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
