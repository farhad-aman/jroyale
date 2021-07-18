import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArenaView extends Group 
{
    private GameManager gameManager = GameManager.getInstance();
    private int show = 0;

    public void updateView(Arena arena, BattleController bt)
    {
        if(show >= 5000) {
            for (Creature c : gameManager.getBattle().getArena().getCreatures()) {
                if(c != null && !(c.getCard() instanceof King) && !(c.getCard() instanceof Princess)){
                    ImageView iv = new ImageView(c.getCard().getImage(c.getStatus()));
                    iv.setX(c.getPosition().getX());
                    iv.setY(c.getPosition().getY());
                    iv.setFitHeight(50);
                    iv.setFitWidth(50);
                    System.out.println("\nkill: " + (c.getKillTarget() == null ? "null" : c.getKillTarget().getCard().getId()) + "\nfollow: " + (c.getFollowTarget() == null ? "null" : c.getFollowTarget().getCard().getId()));
                    iv.setPreserveRatio(true);
                    iv.setPickOnBounds(true);
                    iv.setVisible(true);
                    bt.getArenaPane().getChildren().add(iv);
                }
            }
            show = 0;
        }
        else
            show += GameManager.FPS;
    }
}
