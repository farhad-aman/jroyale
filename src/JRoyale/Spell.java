import javafx.scene.media.MediaPlayer;

/**
 * this abstract class contains concept of spell card in game
 * @version 1.0
 */
public abstract class Spell extends Card
{
    /**
     * creates a new spell card
     * @param type
     * @param id
     * @param cost
     * @param range
     * @param imageSize
     * @param attackSound
     */
    public Spell(String type, String id, int cost, double range, int imageSize, MediaPlayer attackSound)
    {
        super(type, id, cost, range, "junk", imageSize, attackSound, null);
    }

    @Override
    public int getDamage(int level) 
    {
        return 0;
    }

    @Override
    public void step(Creature creature) 
    {

    }

    @Override
    public Creature findNearestValidCreature(Creature creature) 
    {
        return null;
    }
}