public class ElixirBar 
{
    private final int elixirLimit = 10;

    private final double slowStep = 0.0125;

    private final double fastStep = 0.025;
    
    private double elixir;
    
    private int nextFrameTakeExir;

    public ElixirBar()
    {
        this.elixir = 6;
        this.nextFrameTakeExir = 0;
    }

    public void step()
    {
        if(nextFrameTakeExir > 0)
        {
            elixir -= nextFrameTakeExir;
        }
        if(elixir >= elixirLimit)
        {
            elixir = elixirLimit;
        }
        else
        {
            if(GameManager.getInstance().getBattle().getBattleTimer().getTime() <= 60)
            {
                elixir += fastStep;
            }
            else 
            {
                elixir += slowStep;
            }
        }
    }

    public boolean takeExir(int cost)
    {
        if(elixir < cost)
        {
            return false;
        }
        else
        {
            this.nextFrameTakeExir = cost;
            return true;
        }
    }

    public double getElixir()
    {
        return elixir;
    }
}
