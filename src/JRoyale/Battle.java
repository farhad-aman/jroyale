import java.util.ArrayList;
import java.util.Collections;

public class Battle 
{
    private Player player;

    private Bot bot;
    
    private Arena arena;
    
    private ArrayList<Card> playerCardsQueue;
    
    private ArrayList<Card> botCardsQueue;
    
    private ScoreBoard scoreBoard;
    
    private BattleTimer battleTimer;
    
    private ElixirBar playerElixirBar;
    
    private ElixirBar botElixirBar;
    
    public Battle(Bot bot)
    {
        this.player = GameManager.getInstance().getCurrentPlayer();
        this.bot = bot;

        arena = new Arena();

        playerCardsQueue = new ArrayList<Card>();
        for(Card c : player.getDeck().getCards())
        {
            playerCardsQueue.add(c);
        } 
        Collections.shuffle(playerCardsQueue);

        botCardsQueue = new ArrayList<Card>();
        for(Card c : bot.getDeck().getCards())
        {
            botCardsQueue.add(c);
        } 
        Collections.shuffle(botCardsQueue);

        scoreBoard = new ScoreBoard();
        battleTimer = new BattleTimer(180);

        playerElixirBar = new ElixirBar(4, battleTimer);
        botElixirBar = new ElixirBar(4, battleTimer);
    }

    public int step() 
    {
        //moving every creature
        return getStatus();
    }

    /**
     * @return the number indicating the flow of the game////-1->bot won the game//0->game is on process//1->player won the game
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
}
