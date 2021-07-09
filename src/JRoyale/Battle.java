import java.util.ArrayList;

public class Battle 
{
    private Player player;
    
    private BattleField playerField;
    
    private BattleField enemyField;
    
    private ArrayList<Card> yourCards;
    
    private ArrayList<Card> enemyCards;
    
    private ScoreBoard scoreBoard;
    
    private Timer timer;
    
    private ElixirBar yourExlixirBar;
    
    private ElixirBar enemyExlixirBar;
    
    public Battle(Player player)
    {
        this.player = player;
        scoreBoard = new ScoreBoard();
        timer = new Timer(180, scoreBoard);
        yourExlixirBar = new ElixirBar(4, timer);
        enemyExlixirBar = new ElixirBar(4, timer);
    }
}
