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
            String botDifficulty = "";
            if(bot.getDifficulty() == 1)
            {
                botDifficulty = "easy";
            }
            else if(bot.getDifficulty() == 2)
            {
                botDifficulty = "normal";
            }
            else
            {
                botDifficulty = "hard";
            }
            player.addBattleResult(new BattleResult(player.getUsername(), botDifficulty, scoreBoard.getPlayerStars(), scoreBoard.getBotStars()));
            player.setXp(player.getXp() + 200);
            return 1;
        }
        else if(scoreBoard.getBotStars() == 3)
        {
            String botDifficulty = "";
            if(bot.getDifficulty() == 1)
            {
                botDifficulty = "easy";
            }
            else if(bot.getDifficulty() == 2)
            {
                botDifficulty = "normal";
            }
            else
            {
                botDifficulty = "hard";
            }
            player.addBattleResult(new BattleResult(player.getUsername(), botDifficulty, scoreBoard.getPlayerStars(), scoreBoard.getBotStars()));
            player.setXp(player.getXp() + 70);
            return -1;
        }
        else if(battleTimer.getTime() == 0)
        {
            if(scoreBoard.getPlayerStars() > scoreBoard.getBotStars())
            {
                String botDifficulty = "";
                if(bot.getDifficulty() == 1)
                {
                    botDifficulty = "easy";
                }   
                else if(bot.getDifficulty() == 2)
                {
                    botDifficulty = "normal";
                }
                else
                {
                    botDifficulty = "hard";
                }
                player.addBattleResult(new BattleResult(player.getUsername(), botDifficulty, scoreBoard.getPlayerStars(), scoreBoard.getBotStars()));
                player.setXp(player.getXp() + 200);
                return 1;
            }
            else if(scoreBoard.getBotStars() > scoreBoard.getPlayerStars())
            {
                String botDifficulty = "";
                if(bot.getDifficulty() == 1)
                {
                    botDifficulty = "easy";
                }   
                else if(bot.getDifficulty() == 2)
                {
                    botDifficulty = "normal";
                }
                else
                {
                    botDifficulty = "hard";
                }
                player.addBattleResult(new BattleResult(player.getUsername(), botDifficulty, scoreBoard.getPlayerStars(), scoreBoard.getBotStars()));
                player.setXp(player.getXp() + 70);
                return -1;
            }
            else if(scoreBoard.getTotalPlayerTowersHP() >= scoreBoard.getTotalBotTowersHP())
            {
                String botDifficulty = "";
                if(bot.getDifficulty() == 1)
                {
                    botDifficulty = "easy";
                }   
                else if(bot.getDifficulty() == 2)
                {
                    botDifficulty = "normal";
                }
                else
                {
                    botDifficulty = "hard";
                }
                player.addBattleResult(new BattleResult(player.getUsername(), botDifficulty, scoreBoard.getPlayerStars(), scoreBoard.getBotStars()));
                player.setXp(player.getXp() + 200);
                return 1;
            }
            else
            {
                String botDifficulty = "";
                if(bot.getDifficulty() == 1)
                {
                    botDifficulty = "easy";
                }   
                else if(bot.getDifficulty() == 2)
                {
                    botDifficulty = "normal";
                }
                else
                {
                    botDifficulty = "hard";
                }
                player.addBattleResult(new BattleResult(player.getUsername(), botDifficulty, scoreBoard.getPlayerStars(), scoreBoard.getBotStars()));
                player.setXp(player.getXp() + 70);
                return -1;
            }
        }
        return 0;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Bot getBot()
    {
        return bot;
    }

    public Arena getArena() 
    {
        return arena;
    }

    public ArrayList<Card> getPlayerCardsQueue()
    {
        return playerCardsQueue;
    }

    public ArrayList<Card> getBotCardsQueue()
    {
        return botCardsQueue;
    }

    public ScoreBoard getScoreBoard()
    {
        return scoreBoard;
    }

    public BattleTimer getBattleTimer()
    {
        return battleTimer;
    }

    public ElixirBar getPlayerElixirBar()
    {
        return playerElixirBar;
    }

    public ElixirBar getBotElixirBar()
    {
        return botElixirBar;
    }
}
