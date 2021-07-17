import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Troop extends Card
{
    private final int hitSpeed;

    private final int speed;

    private final boolean areaSplash;

    private final int count;

    private final int[] initHP;

    private final int[] damage;

    public Troop(String type,
                 String id,
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
        super(type, id, cost, range, target);
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.areaSplash = areaSplash;
        this.count = count;
        this.initHP = initHP;
        this.damage = damage;
    }

    @Override
    public int getHitSpeed()
    {
        return hitSpeed;
    }

    @Override
    public int getSpeed()
    {
        return speed;
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

                if(creature.getPosition().getX() < creature.getKillTarget().getPosition().getX())
                    creature.setStatus(3);
                else
                    creature.setStatus(4);
            }
            else
            {
                creature.followCreature(creature.getFollowTarget());
            }
        }
        else
        {
            if(creature.isCreatureInRange(creature.getKillTarget()))
            {
                creature.hit(creature.getKillTarget());

                if(creature.getPosition().getX() < creature.getKillTarget().getPosition().getX())
                    creature.setStatus(3);
                else
                    creature.setStatus(4);
            }
            else
            {
                creature.followCreature(creature.getKillTarget());
            }
        }
        if(creature.getKillTarget().isEliminated())
        {
            creature.setKillTarget(null);


        }
    }
}