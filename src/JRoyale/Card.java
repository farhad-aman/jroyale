public abstract class Card 
{
    private final String id;

    private final int cost;

    public Card(String id, int cost)
    {
        this.id = id;
        this.cost = cost;
    }

    public String getId()
    {
        return id;
    }

    public int getCost()
    {
        return cost;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if(obj instanceof Card)
        {
            Card c = (Card)obj;
            if(c.getId().equals(this.id))
            {
                return true;
            }
            return false;
        }
        return false;
    }

}