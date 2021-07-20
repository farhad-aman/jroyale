import java.util.ArrayList;
import java.util.Collections;

/**
 * this class contains the whole concept of battle in the game 
 * @version 1.0
 */
public class Battle 
{
    /**
     * the player of the battle
     */
    private Player player;

    /**
     * the bot of the battle
     */
    private Bot bot;
    
    /**
     * the arena of the battle
     */
    private Arena arena;
    
    /**
     * the cards queue of player deck
     */
    private ArrayList<Card> playerCardsQueue;
    
    /**
     * the cards queue of bot deck
     */
    private ArrayList<Card> botCardsQueue;
    
    /**
     * the scoreboard of the battle
     */
    private ScoreBoard scoreBoard;
    
    /**
     * the timer of the battle shown remaining time
     */
    private BattleTimer battleTimer;
    
    /**
     * the elixir bar of the player
     */
    private ElixirBar playerElixirBar;
    
    /**
     * the elixir bar of the bot
     */
    private ElixirBar botElixirBar;
    
    /**
     * creates a new battle
     * @param bot
     */
    public Battle(Bot bot)
    {
        this.player = GameManager.getInstance().getCurrentPlayer();
        this.bot = bot;

        arena = new Arena();

        playerCardsQueue = new ArrayList<Card>();
        for(Card c : player.getDeck().getCards())
        {
            playerCardsQueue.add(c);
            c.loadImages();
        } 
        Collections.shuffle(playerCardsQueue);

        botCardsQueue = new ArrayList<Card>();
        for(Card c : bot.getDeck().getCards())
        {
            botCardsQueue.add(c);
            c.loadImages();
        } 
        Collections.shuffle(botCardsQueue);

        scoreBoard = new ScoreBoard();
        battleTimer = new BattleTimer(180);

        playerElixirBar = new ElixirBar();
        botElixirBar = new ElixirBar();
    }

    /**
     * the main step of the battle every element done its step in it
     * @return the status of the battle after this step
     */
    public int step() 
    {
        bot.step();
        arena.step();
        playerElixirBar.step();
        botElixirBar.step();
        scoreBoard.step();
        battleTimer.step();
        return getStatus();
    }

    /**
     * @return the number indicating the flow of the game//-1->bot won the game//0->game is on process//1->player won the game
     * */
    private int getStatus() 
    {
        if(scoreBoard.getPlayerStars() == 3)
        {
            return 1;
        }
        else if(scoreBoard.getBotStars() == 3)
        {
            return -1;
        }
        else if(battleTimer.getTime() == 0)
        {
            if(scoreBoard.getPlayerStars() > scoreBoard.getBotStars())
            {
                return 1;
            }
            else if(scoreBoard.getBotStars() > scoreBoard.getPlayerStars())
            {
                return -1;
            }
            else if(scoreBoard.getTotalPlayerTowersHP() >= scoreBoard.getTotalBotTowersHP())
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        return 0;
    }

    /**
     * @return player of the battle
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * @return bot of the battle
     */
    public Bot getBot()
    {
        return bot;
    }

    /**
     * @return arena of the battle
     */
    public Arena getArena() 
    {
        return arena;
    }

    /**
     * @return cards queue of player
     */
    public ArrayList<Card> getPlayerCardsQueue()
    {
        return playerCardsQueue;
    }

    /**
     * @return cards queue of bot
     */
    public ArrayList<Card> getBotCardsQueue()
    {
        return botCardsQueue;
    }

    /**
     * @return score board of the battle
     */
    public ScoreBoard getScoreBoard()
    {
        return scoreBoard;
    }

    /**
     * @return timer of the battle
     */
    public BattleTimer getBattleTimer()
    {
        return battleTimer;
    }

    /**
     * @return elixir bar of player
     */
    public ElixirBar getPlayerElixirBar()
    {
        return playerElixirBar;
    }

    /**
     * @return elixir bar of bot
     */
    public ElixirBar getBotElixirBar()
    {
        return botElixirBar;
    }
}
