import javafx.scene.image.Image;

public class King extends Building
{
    private final int[]  damage = {0, 50, 53, 57, 60, 64};

    public King() 
    {
        super("building", "King", 0, 10000, "both", 7, 1000000, new int[] {0, 2400, 2568, 2736, 2904, 3096});
    }

    @Override
    public void loadImages() 
    {
        pics.put(1, new Image("resources/cards/princess/princessStandPlayer.png"));
    }

    @Override
    public int getDamage(int level) 
    {
        return damage[level];
    }
}