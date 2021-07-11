import java.util.ArrayList;

public class Battle 
{
    private Tile[][] tiles;
    private Player player;
    private Bot bot;
    private Arena playerField;
    private Arena enemyField;
    private ArrayList<Card> yourCards;
    private ArrayList<Card> enemyCards;
    private ScoreBoard scoreBoard;
    private BattleTimer battleTimer;
    private ElixirBar yourExlixirBar;
    private ElixirBar enemyExlixirBar;
    
    public Battle()
    {
        this.player = GameManager.getInstance().getCurrentPlayer();
        bot = player.getPreferredBot();
        scoreBoard = new ScoreBoard();
        battleTimer = new BattleTimer(180, scoreBoard);
        yourExlixirBar = new ElixirBar(4, battleTimer);
        enemyExlixirBar = new ElixirBar(4, battleTimer);
        tiles = new Tile[playerField.getRows()][playerField.getColumns()];
    }

    public void stepAll() {
        //moving every creature
    }
    /**
     * @return the number indicating the flow of the game//-2->tower bonus must be calculated to find the winner//-1->bot won the game//0->game is on process//1->player won the game
     * */
    public int getStatus() {
        if(scoreBoard.getEnemyStars() == 3)
            return -1;
        else if(scoreBoard.getYourStars() == 3)
            return 1;
        else if(battleTimer.getTime() == 0)
            return -2;

        return 0;
    }
}
