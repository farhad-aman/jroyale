/**
 * this abstract class contains concept of bot in game
 * @version 1.0
 */
public abstract class Bot 
{
    /**
     * the name of the bot
     */
    private final String username;

    /**
     * the deck of the bot
     */
    private Deck deck;

    /**
     * the level of the bot
     */
    private final int level;

    /**
     * the difficulty of the bot
     */
    int difficulty;

    /**
     * creates a new bot
     * @param username
     * @param deck
     * @param difficulty
     */
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

    /**
     * @return name of the bot
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @return difficulty of the bot
     */
    public int getDifficulty() 
    {
        return difficulty;
    }

    /**
     * @return deck of the bot
     */
    public Deck getDeck()
    {
        return deck;
    }

    /**
     * sets deck of the bot
     * @param deck
     */
    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    /**
     * @return level of the bot
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * the main step that done every frame
     */
    public abstract void step();
}
