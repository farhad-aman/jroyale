import java.util.ArrayList;

import javafx.geometry.Point2D;

public abstract class Spell extends Card
{
    public Spell(String type, String id, int cost, double range, int imageSize)
    {
        super(type, id, cost, range, "junk", imageSize);
    }

    @Override
    public int getDamage(int level) 
    {
        return 0;
    }

    @Override
    public Creature findNearestValidCreature(Creature creature) {return null;}
}