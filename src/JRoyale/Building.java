import javafx.scene.media.MediaPlayer;

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
                    int imageSize, 
                    double range, 
                    int initLifeTime, 
                    int[] initHP,
                    MediaPlayer attackSound,
                    MediaPlayer deathSound) 
    {
        super(type, id, cost, range, target, imageSize, attackSound, deathSound);
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
            if(target != null && creature.isCreatureInRange(target))
            {
                creature.setKillTarget(target);
                creature.hit(creature.getKillTarget());

                if(creature.getPosition().getX() < creature.getKillTarget().getPosition().getX())
                    creature.setStatus(3);
                else
                    creature.setStatus(4);
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
                creature.setKillTarget(null);
            }
        }
        if(creature.getKillTarget() != null && creature.getKillTarget().isEliminated())
        {
            creature.setKillTarget(null);
        }
    }

    public abstract int getDamage(int level);
}
