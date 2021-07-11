import java.util.ArrayList;

public abstract class Bot {
    int difficulty;
    private ArrayList<Card> cards;

    public Bot(int difficulty, ArrayList<Card> cards) {
        this.difficulty = difficulty;
        this.cards = cards;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
