public abstract class Spell extends Card
{
    private final double radius;

    public Spell(String id, int cost, int radius) 
    {
        super(id, cost);
        this.radius = radius;
    }

    public double getRadius()
    {
        return radius;
    }
    
}
