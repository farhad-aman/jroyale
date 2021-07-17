public class ScoreBoard 
{
    private int playerStars;

    private int botStars;

    private int totalPlayerTowersHP;

    private int totalBotTowersHP;

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

    public int getPlayerStars()
    {
        return playerStars;
    }

    public int getBotStars()
    {
        return botStars;
    }

    public int getTotalPlayerTowersHP()
    {
        return totalPlayerTowersHP;
    }

    public int getTotalBotTowersHP()
    {
        return totalBotTowersHP;
    }
}
