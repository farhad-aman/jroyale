import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Iterator;

/**
 * this class contains arrows card and its details
 * @version 1.0
 */
public class Arrows extends Spell
{
    /**
     * the damge of arrows
     */
    private final int[] damage = {0, 144, 156, 174, 189, 210};

    /**
     * creates a new arrows card
     */
    public Arrows() 
    {
        super("spell", "Arrows", 3, 4, 100, new MediaPlayer(new Media(new File("resources/cards/arrows/arrowsAttack.mp3").toURI().toString())));
    }

    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/arrows/arrows150.png"));
        pics.put(-1, new Image("resources/cards/arrows/arrows150wb.jpg"));
        pics.put(1, new Image("resources/blank150.jpg"));
    }

    @Override
    public void step(Creature creature)
    {
        Iterator<Creature> it = GameManager.getInstance().getBattle().getArena().getCreatures().iterator();

        while (it.hasNext()) 
        {
            Creature tempTarget = it.next();

            if(tempTarget.getPosition().distance(creature.getPosition()) <= ((tempTarget.getCard() instanceof King ? 120 : tempTarget.getCard() instanceof Princess ? 80 : tempTarget.getCard() instanceof Building ? 60 : 40) * super.getRange()) && creature.getSide() != tempTarget.getSide())
            {
                tempTarget.getHit(damage[creature.getLevel()], creature.getPosition().getX() < tempTarget.getPosition().getX() ? 6 : 5);
            }
        }
    }
}
