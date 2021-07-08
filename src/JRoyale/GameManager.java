public class GameManager 
{
    private static GameManager gameManager = new GameManager();

    private Player currentPlayer;

    private GameManager()
    {

    }

    public static GameManager getInstance()
    {
        return gameManager;
    }


    public void login(String username, String password)
    {
        //DB
    }

    public void signUp(String username, String password, String confirmPassword)
    {
        //DB
    }

    public void setCurrentPlayer(Player player)
    {
        this.currentPlayer = player;
    }
}
