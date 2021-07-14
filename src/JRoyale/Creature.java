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

    /**
     * 
     * @param card
     * @param position
     * @param side
     */

    public Creature(Card card, int level, Point2D position, int side) 
    {
        this.card = card;
        if(card instanceof Troop)
        {
            troop = (Troop)card;
        }
        else
        {
            building = (Building)card;
        }
        this.level = level;
        if(troop != null)
        {
            this.hp = troop.getInitHP(level);
            this.lifeTime = 1000000;
            this.damage = troop.getDamage(level);
        }
        else
        {
            this.hp = building.getInitHP(level);
            this.lifeTime = building.getInitLifeTime();
            if(building instanceof Inferno)
            {
                this.damage = ((Inferno)building).getMinDamage(level);
                this.maxDamage = ((Inferno)building).getMaxDamage(level);
            }
            else
            {
                this.damage = ((Cannon)building).getDamage(level);
            }
        }
        this.position = position;
        this.side = side;
        status = 0;
    }

    public Card getCard() 
    {
        return card;
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

    public void step(Arena arena, int direction)//bot creatures move to left(1) and player creatures move to right(2)
    {
        if(killTarget == null)
        {
            
        }
        else
        {

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
        return creature.getHit(card.);
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
}
