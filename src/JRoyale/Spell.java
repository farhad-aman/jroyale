import javafx.scene.media.MediaPlayer;

public abstract class Spell extends Card
{
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
    public Creature findNearestValidCreature(Creature creature) {return null;}
}