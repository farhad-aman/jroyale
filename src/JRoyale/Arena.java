import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Arena extends Group
{
    private int rows = 14;
    private int columns = 18;
    private ImageView[][] field;
    private final double tileLength = 15.00;

    public Arena(){field = new ImageView[rows][columns];}

    public void updateView(Battle battle) {
        //updating the whole field
    }

    private void fetchField() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                ImageView iv = new ImageView();

                iv.setX(column * tileLength);
                iv.setY(row * tileLength);

                iv.setFitWidth(tileLength);
                iv.setFitHeight(tileLength);

                field[row][column] = iv;
                this.getChildren().add(iv);
            }
        }
    }
}
