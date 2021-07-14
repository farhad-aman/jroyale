public class Bot1 extends Bot
{
    
    public Bot1() 
    {
        super("CPU Easy", null, 1);
        Deck deck = new Deck();
        deck.addCard(new Archer());
        deck.addCard(new Barbarians());
        deck.addCard(new Arrows());
        deck.addCard(new Rage());
        deck.addCard(new Fireball());
        deck.addCard(new Cannon());
        deck.addCard(new Pekka());
        deck.addCard(new Wizard());
        this.setDeck(deck);
    }


}
