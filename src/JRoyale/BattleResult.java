/**
 * this class contains a battle result
 * @version 1.0
 */
public class BattleResult 
{
    /**
     * the username of player
     */
    private String username;

    /**
     * the difficulty of bot
     */
    private String botDifficulty;

    /**
     * the winner of the battle
     */
    private String winner;

    /**
     * the score of player
     */
    private int yourScore;

    /**
     * the score of enemy
     */
    private int enemyScore;

    /**
     * creates a new battle result
     * @param username
     * @param botDifficulty
     * @param yourScore
     * @param enemyScore
     */
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

    /**
     * @return player username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @return bot difficulty
     */
    public String getBotDifficulty()
    {
        return botDifficulty;
    }

    /**
     * @return player score
     */
    public int getYourScore()
    {
        return yourScore;
    }

    /**
     * @return enemy score
     */
    public int getEnemyScore()
    {
        return enemyScore;
    }

    /**
     * @return winner of the battle
     */
    public String getWinner()
    {
        return winner;
    }
}
