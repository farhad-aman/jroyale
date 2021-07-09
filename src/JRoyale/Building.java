public abstract class Building extends Card
{
    private final double range;

    public Building(String id, int cost, double range) 
    {
        super(id, cost);
        this.range = range;
    }

    public double getRange()
    {
        return range;
    }
    
}
