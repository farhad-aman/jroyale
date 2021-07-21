import javafx.scene.media.MediaPlayer;

/**
 * this abstract class contains the concept of building in the game
 * @version 1.0
 */
public abstract class Building extends Card
{
    /**
     * the hit speed of the building
     */
    private final int hitSpeed;

    /**
     * the initial life time of the building
     */
    private final int initLifeTime;

    /**
     * the initial hp of the building
     */
    private final int[] initHP;

    /**
     * creates a new building
     * @param type
     * @param id
     * @param cost
     * @param hitSpeed
     * @param target
     * @param imageSize
     * @param range
     * @param initLifeTime
     * @param initHP
     * @param attackSound
     * @param deathSound
     */
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

    /**
     * @return initial life time
     */
    public int getInitLifeTime()
    {
        return initLifeTime;
    }

    /**
     * @param level
     * @return initial hp
     */
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
                creature.setFollowTarget(target);

                boolean isKilled = creature.hit(creature.getKillTarget());

                findStatus(isKilled, creature, target);
            }
        }
        else
        {
            if(creature.isCreatureInRange(creature.getKillTarget()))
            {
                boolean isKilled = creature.hit(creature.getKillTarget());

                findStatus(isKilled, creature, creature.getKillTarget());
            }
            else
            {
                if(creature.getCard() instanceof Cannon)
                    findStatus(true, creature, creature.getKillTarget());

                creature.setKillTarget(null);
            }
        }
        if(creature.getKillTarget() != null && creature.getKillTarget().isEliminated())
        {
            creature.setKillTarget(null);
        }
    }
    /**
     * sets the appropriate status for the given cannon
     * @param isKilled represents whether the target is killed or not
     * @param c the creature to set status for
     * @param target to kill
     * */
    private void findStatus(boolean isKilled, Creature c, Creature target){
        if(c != null && target != null){
            if (isKilled && c.getCard() instanceof Cannon) {
                if(target.getPosition().getX() < c.getPosition().getX())
                    c.setStatus(2);
                else
                    c.setStatus(1);
            }
            else{
                if(target.getPosition().getX() < c.getPosition().getX())
                    c.setStatus(4);
                else
                    c.setStatus(3);
            }
        }
    }

    /**
     * @return damage of the building
     */
    public abstract int getDamage(int level);
}
