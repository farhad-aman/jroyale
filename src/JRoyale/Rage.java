import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Iterator;

/**
 * this class contains rage spell card in the game
 * @version 1.0
 */
public class Rage extends Spell
{
    /**
     * the duration of the rage spell
     */
    private final int duration[] = {0, 6000, 6500, 7000, 7500, 8000};

    /**
     * creates a new rage card
     */
    public Rage() 
    {
        super("spell", "Rage", 3, 5, 100, new MediaPlayer(new Media(new File("resources/cards/rage/rageAttack.mp3").toURI().toString())));
    }

    /**
     * loads the proper pics for status//0->for deck(150.jpg)//troops://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//10->inferno.gif//11->spells gif
     * */
    public void loadImages()
    {
        pics.put(0, new Image("resources/cards/rage/rage150.png"));
        pics.put(-1, new Image("resources/cards/rage/rage150wb.jpg"));
        pics.put(1, new Image("resources/blank150.jpg"));
    }


    @Override
    public void step(Creature creature)
    {
        Iterator<Creature> it = GameManager.getInstance().getBattle().getArena().getCreatures().iterator();

        while (it.hasNext()) 
        {
            Creature tempTarget = it.next();

            if(tempTarget.getPosition().distance(creature.getPosition()) <= (40 * super.getRange()) && creature.getSide() == tempTarget.getSide())
            {
                tempTarget.setUnderRage(true);
                tempTarget.setRageTimeRemained(duration[creature.getLevel()]);
            }
        }
    }

    /**
     * @param level
     * @return the duration of the rage spell
     */
    public int getDuration(int level)
    {
        return duration[level];
    }
}
