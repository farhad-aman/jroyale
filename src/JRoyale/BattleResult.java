public class BattleResult 
{
    private String username;

    private String botDifficulty;

    private String winner;

    private int yourScore;

    private int enemyScore;

    public BattleResult(String username, String botDifficulty, int yourScore, int enemyScore)
    {
        this.username = username;
        this.botDifficulty = botDifficulty;
        this.yourScore = yourScore;
        this.enemyScore = enemyScore;

        if(yourScore > enemyScore)
        winner = username;
        else
        winner = botDifficulty + " Bot";
    }

    public String getUsername()
    {
        return username;
    }

    public String getBotDifficulty()
    {
        return botDifficulty;
    }

    public int getYourScore()
    {
        return yourScore;
    }

    public int getEnemyScore()
    {
        return enemyScore;
    }

    public String getWinner()
    {
        return winner;
    }
}
