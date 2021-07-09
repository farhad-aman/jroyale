public class ElixirBar 
{
    private final int elixirLimit = 10;

    private int elixir;
    
    private Timer timer;

    private int stepLimit = 80;

    private int stepValue;

    public ElixirBar(int initElixir, Timer timer)
    {
        this.elixir = initElixir;
        this.timer = timer;
    }

    public void step()
    {
        if(timer.getTime() == 60)
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
