import javafx.scene.image.Image;

public class King extends Building
{
    private final int[]  damage = {0, 50, 53, 57, 60, 64};

    public King() 
    {
        super("building", "King", 0, 10000, "both", 160, 7, 1000000, new int[] {0, 2400, 2568, 2736, 2904, 3096});
    }

    @Override
    public void loadImages() 
    {
        pics.put(1, new Image("resources/cards/king/kingStandPlayer.png"));
        pics.put(2, new Image("resources/cards/king/kingStandBot.png"));
        pics.put(3, new Image("resources/cards/king/kingAttackPlayer.gif"));
        pics.put(4, new Image("resources/cards/king/kingAttackBot.gif"));
    }

    @Override
    public int getDamage(int level) 
    {
        return damage[level];
    }
}