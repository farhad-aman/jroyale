public class BattleTimer
{
    private int time; 

    private final int stepLimit;

    private int stepValue;

    public BattleTimer(int time)
    {
        this.time = time;
        this.stepLimit = GameManager.FPS;
        this.stepValue = 0;
    }

    public int getTime()
    {
        return time;
    }

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
