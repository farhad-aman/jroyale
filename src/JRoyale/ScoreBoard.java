/**
 * this class contains score board of the battle
 * @version 1.0
 */
public class ScoreBoard 
{
    /**
     * the number of player stars
     */
    private int playerStars;

    /**
     * the number of bot stars
     */
    private int botStars;

    /**
     * the total hp of player towers
     */
    private int totalPlayerTowersHP;

    /**
     * the total hp of bot towers
     */
    private int totalBotTowersHP;

    /**
     * the step of score board that done every frame and updates score board
     */
    public void step()
    {
        playerStars = 0;
        botStars = 0;
        totalPlayerTowersHP = 0;
        totalBotTowersHP = 0;
        Arena arena = GameManager.getInstance().getBattle().getArena();
        if(arena.getBotKing().isEliminated())
        {
            playerStars = 3;
        }
        else if(arena.getPlayerKing().isEliminated())
        {
            botStars = 3;
        }
        else
        {
            if(arena.getPlayerUpPrincess().isEliminated())
            {
                botStars++;
            }
            if(arena.getPlayerDownPrincess().isEliminated())
            {
                botStars++;
            }
            if(arena.getBotUpPrincess().isEliminated())
            {
                playerStars++;
            }
            if(arena.getBotDownPrincess().isEliminated())
            {
                playerStars++;
            }
        }
        totalPlayerTowersHP += Math.max(0, arena.getPlayerKing().getHP());
        totalPlayerTowersHP += Math.max(0, arena.getPlayerUpPrincess().getHP());
        totalPlayerTowersHP += Math.max(0, arena.getPlayerDownPrincess().getHP());
        totalBotTowersHP += Math.max(0, arena.getBotKing().getHP());
        totalBotTowersHP += Math.max(0, arena.getBotUpPrincess().getHP());
        totalBotTowersHP += Math.max(0, arena.getBotDownPrincess().getHP());
    }

    /**
     * @return number of player stars
     */
    public int getPlayerStars()
    {
        return playerStars;
    }

    /**
     * @return number of bot stars
     */
    public int getBotStars()
    {
        return botStars;
    }

    /**
     * @return total hp of player towers
     */
    public int getTotalPlayerTowersHP()
    {
        return totalPlayerTowersHP;
    }

    /**
     * @return total hp of bot towers
     */
    public int getTotalBotTowersHP()
    {
        return totalBotTowersHP;
    }
}
