import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Card
{
    private final String id;

    protected HashMap<Integer, Image> pics;

    private final int cost;

    public Card(String id, int cost)
    {
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
    /**
     * @return the relevant picture for the status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public Image getImage(int status){
        return pics.get(status);
    }

    public abstract void loadImages();
}