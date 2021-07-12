import java.util.ArrayList;

public class Arena
{
    private final int rows = 20;//not precise
    private final int columns = 40;//not precise
    private ArrayList<Creature> playerCreatures;
    private ArrayList<Creature> botCreatures;

    public Arena() {
        playerCreatures = new ArrayList<>();
        botCreatures = new ArrayList<>();
    }

    public void addPlayerCreature(Creature creature){playerCreatures.add(creature);}

    public void addBotCreature(Creature creature){botCreatures.add(creature);}

    public boolean removePlayerCreature(Creature creature){
        return playerCreatures.remove(creature);
    }

    public boolean removeBotCreature(Creature creature){
        return botCreatures.remove(creature);
    }

    public void step(){
        for(Creature creature : playerCreatures)
            creature.step(this, 2);

        for(Creature creature : botCreatures)
            creature.step(this, 1);
    }
    // private int rows = 14;
    // private int columns = 18;
    // private ImageView[][] field;
    // private final double tileLength = 15.00;

    // public Arena(){field = new ImageView[rows][columns];}

    // public void updateView(Battle battle) {
    //     //updating the whole field
    // }

    // private void fetchField() {
    //     for (int row = 0; row < rows; row++) {
    //         for (int column = 0; column < columns; column++) {
    //             ImageView iv = new ImageView();

    //             iv.setX(column * tileLength);
    //             iv.setY(row * tileLength);

    //             iv.setFitWidth(tileLength);
    //             iv.setFitHeight(tileLength);

    //             field[row][column] = iv;
    //             this.getChildren().add(iv);
    //         }
    //     }
    // }
}
