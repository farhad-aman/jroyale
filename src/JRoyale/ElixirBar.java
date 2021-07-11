public class ElixirBar 
{
    private final int elixirLimit = 10;

    private int elixir;
    
    private BattleTimer battleTimer;

    private int stepLimit = 80;

    private int stepValue;

    public ElixirBar(int initElixir, BattleTimer battleTimer)
    {
        this.elixir = initElixir;
        this.battleTimer = battleTimer;
    }

    public void step()
    {
        if(battleTimer.getTime() == 60)
        {
            stepLimit = 40;
        }
        if(stepValue >= stepLimit)
        {
            stepValue = 0;
            if(elixir < elixirLimit)
            {
                elixir++;
            }
        }
        else
        {
            stepValue++;
        }
    }

    public int getElixir()
    {
        return elixir;
    }
}
