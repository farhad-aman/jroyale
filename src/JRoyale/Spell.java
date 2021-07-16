import java.util.ArrayList;

import javafx.geometry.Point2D;

public abstract class Spell extends Card
{
    private final double radius;

    public Spell(String id, int cost, double radius) 
    {
        super(id, cost);
        this.radius = radius;
    }

    public double getRadius()
    {
        return radius;
    }

    @Override
    public int getDamage(int level) 
    {
        return 0;
    }

}