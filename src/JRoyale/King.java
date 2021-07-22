import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * this class contains king card in the game
 * @version 1.0
 */
public class King extends Building
{
    /**
     * the damage of the king card
     */
    private final int[]  damage = {0, 50, 53, 57, 60, 64};

    /**
     * creates a new king card
     */
    public King() 
    {
        super("building", 
              "King", 
              0, 
              1000,
              "both", 
              160, 
              7, 
              1000000, 
              new int[] {0, 2400, 2568, 2736, 2904, 3096},
              new MediaPlayer(new Media(new File("resources/cards/king/kingAttack.mp3").toURI().toString())),
              new MediaPlayer(new Media(new File("resources/cards/king/kingDeath.mp3").toURI().toString())));
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