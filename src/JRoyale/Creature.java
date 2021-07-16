import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * This class contains a creature on the arena 
 */
public class Creature 
{
    /**
     * the card that creature based on that
     */
    private Card card;

    /**
     * the level of the creature
     */
    private int level;

    /**
     * the center position of the creature 
     */
    private Point2D position;

    /**
     * the current image that creture shown based on status
     */
    private ImageView image;

    /**
     * the side that creature fights for
     * -1 -> belongs to bot , 1 -> belongs to player 
     */
    private int side; 

    /**
     * the current status of the creature
     * 0 -> created recently , 1 -> move to right , 2 -> move to left , 3 -> fighting to right , 4 -> fighting to left , 5 -> dying to right , 6 -> dying to left
     */
    private int status;

    /**
     * the creature this creature should follow
     */
    private Creature followTarget;

    /**
     * the creature that this creature should kill or be killed
     */
    private Creature killTarget;

    /**
     * the current hp of creature
     */
    private int hp;

    /**
     * the current life time of the creature
     */
    private int lifeTime;

    /**
     * the tick time to hit
     */
    private int hitStepValue;

    private int hitSpeed;

    private int speed;

    private int speedValue;

    private boolean underRage;

    private int damage;

    private int rageTimeRemained;//in milliseconds

    /**
     * creates a new creature
     * @param card
     * @param position
     * @param side
     */
    public Creature(Card card, int level, Point2D position, int side) 
    {
        this.card = card;
        this.level = level;
        this.position = position;
        this.side = side;

        underRage = false;
        rageTimeRemained = 0;

        speed = card.getSpeed();
        speedValue = 0;

        hitSpeed = card.getHitSpeed();
        hitStepValue = 0;

        damage = card.getDamage(level);

        if(card instanceof Building)
        {
            this.hp = ((Building)card).getInitHP(level);
            this.lifeTime = ((Building)card).getInitHP(level);
        }
        else if(card instanceof Troop)
        {
            this.hp = ((Troop)card).getInitHP(level);
            this.lifeTime = 1000000;
        }
    }

    public int getSide() 
    {
        return side;
    }

    public int getLevel() 
    {
        return level;
    }

    public boolean isUnderRage() 
    {
        return underRage;
    }

    public Card getCard() 
    {
        return card;
    }

    public int getRageTimeRemained() 
    {
        return rageTimeRemained;
    }

    public Point2D getPosition() 
    {
        return position;
    }

    public ImageView getImage() 
    {
        return image;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Creature getKillTarget()
    {
        return killTarget;
    }

    public void setKillTarget(Creature creature)
    {
        this.killTarget = creature;
    }

    public Creature getFollowTarget()
    {
        return followTarget;
    }

    public void setUnderRage(boolean newUnderRage) 
    {
        this.underRage = newUnderRage;
    }

    public void setRageTimeRemained(int rageTimeRemained) 
    {
        this.rageTimeRemained = rageTimeRemained;
    }

    public void setFollowTarget(Creature creature)
    {
        this.followTarget = creature;
    }

    public int getHP()
    {
        return hp;
    }

    public void step()
    {
        card.step(this);

        if(underRage)
        {
            rageTimeRemained -= 1000 / GameManager.FPS;

            if(rageTimeRemained == 0)
                underRage = false;
        }
    }

    /**
     * this creature get hit
     * @param damage
     * @return true if target eliminated else false
     */
    public boolean getHit(int damage)
    {
        hp -= damage;
        if(hp <= 0)
        {
            hp = 0;
            return true;
        }
        return false;
    }

    /**
     * hit another creature
     * @param creature
     * @return true if target creature eliminated else false
     */
    public boolean hit(Creature creature)
    {
        creature.getHit((int) (damage * (underRage ? 1.4 : 1)));
        return creature.isEliminated();
    }

    /**
     * @return is creature eliminated
     */
    public boolean isEliminated()
    {
        if(hp == 0)
        {
            return true;
        }
        return false;
    }

    public int getDistance(Creature creature)
    {
        return (int)position.distance(creature.position);
    }

    public Creature findNearestValidCreature()
    {
        return null;
    }

    public boolean isCreatureInRange(Creature creature)
    {
        return false;
    }

    public void followCreature(Creature creature)
    {
        //TODO: applying rage effect if necessary for following speed

        hitStepValue += (underRage ? 1.4 : 1) * GameManager.FPS;
        if(hitStepValue > hitSpeed)
            hitStepValue = speedValue;
    }
}
