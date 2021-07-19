import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Card
{
    private final String type;

    private final String id;

    protected HashMap<Integer, Image> pics;

    private final int cost;

    private final double range;

    private final String target;

    private final int imageSize;

    private final MediaPlayer attackSound;

    private final MediaPlayer deathSound;
    
    public Card(String type, String id, int cost, double range, String target, int imageSize, MediaPlayer attackSound, MediaPlayer deathSound)
    {
        this.type = type;
        this.pics = new HashMap<>();
        this.id = id;
        this.cost = cost;
        this.range = range;
        this.target = target;
        this.imageSize = imageSize;
        this.attackSound = attackSound;
        this.deathSound = deathSound;
    }

    public String getId()
    {
        return id;
    }

    public int getCost()
    {
        return cost;
    }

    public double getRange() 
    {
        return range;
    }

    public int getImageSize()
    {
        return imageSize;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if(obj instanceof Card)
        {
            Card c = (Card)obj;
            if(c.getId().equals(this.id))
            {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() 
    {
        return id.hashCode();
    }

    /**
     * @return the relevant picture for the status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public Image getImage(int status)
    {
        return pics.get(status);
    }

    public abstract void loadImages();

    public abstract void step(Creature creature);

    public abstract int getDamage(int level);

    public String getTarget() 
    {
        return target;
    }

    public String getType() 
    {
        return type;
    }

    public int getSpeed()
    {
        return 0;
    }

    public int getHitSpeed()
    {
        return 0;
    }

    public ArrayList<Creature> makeCreature(Point2D center, int side)
    {
        ArrayList<Creature> creatures = new ArrayList<>();
        if(side == -1)
        {
            for(int i = 0;i < (this instanceof Troop ? ((Troop) this).getCount() : 1);i++){
                if (GameManager.getInstance().getCurrentBot() instanceof Bot1) {
                    creatures.add(new Creature(this, 1, center, -1));
                } else if (GameManager.getInstance().getCurrentBot() instanceof Bot2) {
                    creatures.add(new Creature(this, 3, center, -1));
                } else {
                    creatures.add(new Creature(this, 5, center, -1));
                }
            }
            
        }
        else
        {
            for(int i = 0;i < (this instanceof Troop ? ((Troop) this).getCount() : 1);i++){
                creatures.add(new Creature(this, GameManager.getInstance().getCurrentPlayer().getLevel(), center, 1));
            }
        }
        return creatures;
    }

    public Creature findNearestValidCreature(Creature creature) 
    {
        Creature target = null;
        double distance = 10000.00;
        Iterator<Creature> it = GameManager.getInstance().getBattle().getArena().getCreatures().iterator();

        while (it.hasNext())
        {
            Creature tempCreature = it.next();
            double tempDistance = creature.getDistance(tempCreature);

                if(creature.getSide() != tempCreature.getSide() && !(tempCreature.getCard() instanceof Spell) && tempDistance < distance && canHit(this.target, tempCreature.getCard().getType()))
                {
                    target = tempCreature;
                    distance = tempDistance;
                }
        }
        return target;
    }

    private boolean canHit(String ability, String targetType){
        if(ability.equals("both") || ability.equals(targetType))
            return true;
        if(ability.equals("ground") && targetType.equals("building")){
                return true;
        }
        return false;
    }

    public void playAttackSound()
    {
        attackSound.seek(Duration.ZERO);
        attackSound.setVolume(0.5);
        attackSound.play();
    }

    public void playDeathSound()
    {
        deathSound.seek(Duration.ZERO);
        deathSound.setVolume(0.5);
        deathSound.play();
    }
}