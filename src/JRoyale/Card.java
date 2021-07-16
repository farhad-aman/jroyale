import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Card
{
    private final String id;

    protected HashMap<Integer, Image> pics;

    private final int cost;

    public Card(String id, int cost)
    {
        this.pics = new HashMap<>();
        this.id = id;
        this.cost = cost;
    }

    public String getId()
    {
        return id;
    }

    public int getCost()
    {
        return cost;
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
            if(GameManager.getInstance().getBattle().getBot() instanceof Bot1)
            {
                creatures.add(new Creature(this, 1, center, -1));
            }
            else if(GameManager.getInstance().getBattle().getBot() instanceof Bot2)
            {
                creatures.add(new Creature(this, 3, center, -1));
            }
            else
            {
                creatures.add(new Creature(this, 5, center, -1));
            }
            
        }
        else
        {
            creatures.add(new Creature(this, GameManager.getInstance().getCurrentPlayer().getLevel(), center, 1));
        }
        return creatures;
    }
}