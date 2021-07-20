/**
 * this class contains the time of the battle
 * @version 1.0
 */
public class BattleTimer
{
    /**
     * the limit that time changes in that point
     */
    private final int stepLimit;
    
    /**
     * the current time of the timer
     */
    private int time; 

    /**
     * the value that changes in every frame to reach limit
     */
    private int stepValue;

    /**
     * creates a new battle timer
     * @param time
     */
    public BattleTimer(int time)
    {
        this.time = time;
        this.stepLimit = GameManager.FPS;
        this.stepValue = 0;
    }

    /**
     * @return current time
     */
    public int getTime()
    {
        return time;
    }

    /**
     * main step of the timer done every frame
     */
    public void step()
    {
        if(stepValue >= stepLimit && time > 0)
        {
            stepValue = 0;
            time--;
        }
        else
        {
            stepValue++;
        }
    }
}
