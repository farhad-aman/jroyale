public abstract class Card 
{
    private final String id;

    public Card(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
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