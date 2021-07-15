import javafx.scene.control.Cell;

public abstract class Troop extends Card
{
    private final int hitSpeed;

    private final int speed;

    private final String target;
    
    private final double range;

    private final boolean areaSplash;

    private final int count; 

    private final int[] initHP;

    private final int[] damage;

    public Troop(String id, 
                 int cost, 
                 int hitSpeed, 
                 int speed, 
                 String target, 
                 double range, 
                 boolean areaSplash, 
                 int count,
                 int[] initHP,
                 int[] damage) 
    {
        super(id, cost);
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.areaSplash = areaSplash;
        this.count = count;
        this.initHP = initHP;
        this.damage = damage;
    }
    
    public int getHitSpeed() 
    {
        return hitSpeed; 
    }

    public int getSpeed()
    {
        return speed;
    }

    public String getTarget()
    {
        return target;
    }

    public double getRange()
    {
        return range;
    }

    public boolean isAreaSplash()
    {
        return areaSplash;
    }

    public int getCount()
    {
        return count;
    }
    
    public int getInitHP(int level)
    {
        return initHP[level];
    }

    public int getDamage(int level)
    {
        return damage[level];
    }

    @Override
    public void step(Creature creature) 
    {        
        if(creature.getKillTarget() == null)
        {
            creature.setFollowTarget(creature.findNearestValidCreature());
            if(creature.isCreatureInRange(creature.getFollowTarget()))
            {
                creature.setKillTarget(creature.getFollowTarget());
                creature.hit(creature.getKillTarget());
            }
            else
            {
                creature.followCreature(creature.getFollowTarget());
            }
        }
        else
        {
            if(creature.isEliminated())
            {
                creature.setKillTarget(null);
            }
            else
            {
                if(creature.isCreatureInRange(creature.getKillTarget()))
                {
                    creature.hit(creature.getKillTarget());
                }
                else
                {
                    creature.followCreature(creature.getKillTarget());
                }
            }
        }
    }

}