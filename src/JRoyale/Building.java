public abstract class Building extends Card
{
    private final int hitSpeed;

    private final String target;

    private final double range;

    private final int lifeTime;

    private final int[] initHP;

    public Building(String id, 
                    int cost, 
                    int hitSpeed, 
                    String target, 
                    double range, 
                    int lifeTime, 
                    int[] initHP) 
    {
        super(id, cost);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifeTime = lifeTime;
        this.initHP = initHP;
    }

    public double getRange()
    {
        return range;
    }
    
}
