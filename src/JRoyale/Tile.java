public class Tile {
    private Card card;
    private double x;
    private double y;

    public Tile(Card card, double x, double y) {
        this.card = card;
        this.x = x;
        this.y = y;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}