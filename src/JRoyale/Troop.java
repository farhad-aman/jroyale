import javafx.scene.media.MediaPlayer;

/**
 * this abstract class contains concept of troop card in the game
 * @version 1.0
 */
public abstract class Troop extends Card
{
    /**
     * the hit speed of troop
     */
    private final int hitSpeed;

    /**
     * the moving speed of troop
     */
    private final int speed;

    /**
     * is troop hits area splash
     */
    private final boolean areaSplash;

    /**
     * the number troops this card can make
     */
    private final int count;

    /**
     * the initial hp of troop
     */
    private final int[] initHP;

    /**
     * the damage of troop
     */
    private final int[] damage;

    /**
     * creates a new troop
     * @param type
     * @param id
     * @param cost
     * @param hitSpeed
     * @param speed
     * @param target
     * @param imageSize
     * @param range
     * @param areaSplash
     * @param count
     * @param initHP
     * @param damage
     * @param attackSound
     * @param deathSound
     */
    public Troop(String type,
                 String id,
                 int cost,
                 int hitSpeed,
                 int speed,
                 String target,
                 int imageSize,
                 double range,
                 boolean areaSplash,
                 int count,
                 int[] initHP,
                 int[] damage,
                 MediaPlayer attackSound,
                 MediaPlayer deathSound)
    {
        super(type, id, cost, range, target, imageSize, attackSound, deathSound);
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

    /**
     * @return is troop hits area splash
     */
    public boolean isAreaSplash()
    {
        return areaSplash;
    }

    /**
     * @return count of troop
     */
    public int getCount()
    {
        return count;
    }

    /**
     * @param level
     * @return initial hp of troop
     */
    public int getInitHP(int level)
    {
        return initHP[level];
    }

    /**
     * @param level
     * @return the damage of the troop
     */
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
            if(creature.getFollowTarget() != null && creature.isCreatureInRange(creature.getFollowTarget()))
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
        if(creature.getKillTarget() != null && creature.getKillTarget().isEliminated())
        {
            creature.setKillTarget(null);
        }
    }
}