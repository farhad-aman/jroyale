public class Cannon extends Building
{
    public final int hp[] = {0, 380, 418, 459, 505, 554};
    
    public final int damage[] = {0, 60, 66, 72, 79, 87};

    public final int hitSpeed = 800;

    public final int lifeTime = 30000;

    public Cannon() 
    {
        super("Cannon", 3, 5.5);
    }
    
}