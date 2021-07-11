public class BattleTimer
{
    private int time; 

    private ScoreBoard scoreBoard;

    private final int stepLimit = 40;

    private int stepValue;

    public BattleTimer(int time, ScoreBoard scoreBoard)
    {
        this.time = time;
        this.scoreBoard = scoreBoard;
        stepValue = 0;
    }

    public int getTime()
    {
        return time;
    }

    public void step()
    {
        if(scoreBoard.getYourStars() == 3)
        {
            //Battle Finishes
        }
        else if(scoreBoard.getEnemyStars() == 3)
        {
            //Battle Finishes
        }
        else
        {
            if(stepValue >= stepLimit)
            {
                stepValue = 0;
                time--;
            }
            else
            {
                stepValue++;
            }
            if(time == 0)
            {
                if(scoreBoard.getYourStars() > scoreBoard.getEnemyStars())
                {
                    //Battle Finishes
                }
                else if(scoreBoard.getEnemyStars() > scoreBoard.getYourStars())
                {
                    //Battle Finishes
                }
                else if(scoreBoard.getYourStars() == scoreBoard.getEnemyStars())
                {
                    //Battle Finishes
                }
            }
        }
    }
}
