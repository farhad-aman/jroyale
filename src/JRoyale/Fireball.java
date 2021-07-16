import javafx.scene.image.Image;

import java.util.Iterator;

public class Fireball extends Spell
{
    private final int[] damage = {0, 325, 357, 393, 432, 474};

    public Fireball() 
    {
        super("Fireball", 4, 2.5);
    }

    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/fireball/fireball150.png"));
        pics.put(-1, new Image("resources/cards/fireball/fireball150wb.jpg"));
       // pics.put(11, new Image("resources/fireball/archer/fireball.gif"));
    }

    @Override
    public void step(Creature creature)
    {
        Iterator<Creature> it = GameManager.getInstance().getBattle().getArena().getCreatures().iterator();

        while (it.hasNext()) 
        {
            Creature tempTarget = it.next();

            if(tempTarget.getPosition().distance(creature.getPosition()) <= super.getRadius() && creature.getSide() != tempTarget.getSide())
            {
                tempTarget.getHit(damage[creature.getLevel()]);
            }
        }
    }
}
