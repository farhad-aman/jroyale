import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class ArenaView extends Group 
{
    private GameManager gameManager = GameManager.getInstance();
    
    private ArrayList<Creature> arenaViewCreatures;

    private HashMap<Creature, Integer> oldStatus;

    private ArrayList<ImageView> arenaViewImageViews;

    public ArenaView()
    {
        arenaViewCreatures = new ArrayList<>();
        oldStatus = new HashMap<>();
        arenaViewImageViews = new ArrayList<>();
    }

    public void updateView(Arena arena, BattleController bt)
    {
        for (Creature c : gameManager.getBattle().getArena().getCreatures()) 
        {
            if(arenaViewCreatures.contains(c))
            {
                ImageView iv = arenaViewImageViews.get(arenaViewCreatures.indexOf(c));
                iv.setX(c.getPosition().getX() - 25);
                iv.setY(c.getPosition().getY() - 25);
                if(!oldStatus.get(c).equals(c.getStatus()))
                {
                    iv.setImage(c.getCard().getImage(c.getStatus()));
                    oldStatus.put(c, c.getStatus());
                }
            }
            else
            {
                oldStatus.put(c, c.getStatus());
                ImageView iv = new ImageView(c.getCard().getImage(c.getStatus()));
                iv.setX(c.getPosition().getX() - 25);
                iv.setY(c.getPosition().getY() - 25);
                iv.setFitHeight(50);
                iv.setFitWidth(50);
                System.out.println("\n" + c.getCard().getId() + "\nx, y :" + c.getPosition().getX() + ", " + c.getPosition().getY() + "\nkill: " + (c.getKillTarget() == null ? "null" : c.getKillTarget().getCard().getId()) + "\nfollow: " + (c.getFollowTarget() == null ? "null" : c.getFollowTarget().getCard().getId()));
                iv.setPreserveRatio(true);
                iv.setPickOnBounds(true);
                iv.setVisible(true);
                bt.getArenaPane().getChildren().add(iv);
                arenaViewImageViews.add(iv);
                arenaViewCreatures.add(c);
            }
        }
    }
}
