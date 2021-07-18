public abstract class Bot 
{
    private final String username;

    private Deck deck;

    private final int level;

    int difficulty;

    public Bot(String username, Deck deck, int difficulty) 
    {
        this.username = username;
        this.deck = deck;
        this.difficulty = difficulty;
        if(difficulty == 1)
        {
            this.level = 1;
        }
        else if(difficulty == 2)
        {
            this.level = 3;
        }
        else
        {
            this.level = 5;
        }
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

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    public int getLevel()
    {
        return level;
    }

    public abstract void step();
}
