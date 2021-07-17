import java.util.ArrayList;

import javafx.geometry.Point2D;

public abstract class Building extends Card
{
    private final int hitSpeed;

    private final int initLifeTime;

    private final int[] initHP;

    public Building(String type,
                    String id,
                    int cost, 
                    int hitSpeed, 
                    String target, 
                    double range, 
                    int initLifeTime, 
                    int[] initHP) 
    {
        super(type, id, cost, range, target);
        this.hitSpeed = hitSpeed;
        this.initLifeTime = initLifeTime;
        this.initHP = initHP;
    }

    @Override
    public int getHitSpeed()
    {
        return hitSpeed;
    }

    public int getInitLifeTime()
    {
        return initLifeTime;
    }

    public int getInitHP(int level)
    {
        return initHP[level];
    }

    @Override
    public void step(Creature creature) 
    {
        if(creature.getKillTarget() == null)
        {
            Creature target = creature.findNearestValidCreature();
            if(creature.isCreatureInRange(target))
            {
                creature.setKillTarget(target);
                creature.hit(creature.getKillTarget());
            }
        }
        else
        {
            if(creature.getKillTarget().isEliminated())
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
                    creature.setKillTarget(null);
                }
            }
        }
    }

    public abstract int getDamage(int level);
}
