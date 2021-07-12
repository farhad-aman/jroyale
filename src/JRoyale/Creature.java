import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class Creature {
    private Card card;
    private Point2D position;
    private ImageView image;
    private int status;//0->created recently//1->move to right//2->move to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to right

    public Creature(Card card, Point2D position) {
        this.card = card;
        this.position = position;
        status = 0;
    }

    public Card getCard() {
        return card;
    }

    public Point2D getPosition() {
        return position;
    }

    public ImageView getImage() {
        return image;
    }

    public void step(Arena arena, int direction){//bot creatures move to left(1) and player creatures move to right(2)
        //moving logic for every creature
        //setting appropriate image for the creature status
    }
}
