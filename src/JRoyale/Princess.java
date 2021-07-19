import javafx.scene.image.Image;

public class Princess extends Building
{
    private final int[] damage = {0, 50, 54, 58, 62, 69};

    public Princess() 
    {
        super("building", "Princess", 0, 800, "both", 120, 7.5, 1000000, new int[] {0, 1400, 1512, 1624, 1750, 1890});
    }

    @Override
    public void loadImages() 
    {
        pics.put(1, new Image("resources/cards/princess/princessStandPlayer.png"));
        pics.put(2, new Image("resources/cards/princess/princessStandBot.png"));
        pics.put(3, new Image("resources/cards/princess/princessAttackPlayer.gif"));
        pics.put(4, new Image("resources/cards/princess/princessAttackBot.gif"));
    }

    @Override
    public int getDamage(int level) 
    {
        return damage[level];
    }
}
