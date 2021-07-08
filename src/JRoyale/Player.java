public class Player 
{
    private final String username;

    private String password;

    private int xp;

    private Deck deck;

    public Player(String username, String password, int xp, Deck deck)
    {
        this.username = username;
        this.password = password;
        this.xp = xp;
        this.deck = deck;
    }

    public String getUsername()
    {
        return username;
    }

    public int getXp()
    {
        return xp;
    }

    public void setXp(int xp)
    {
        this.xp = xp;
        this.saveOnDB();
    }

    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
        this.saveOnDB();
    }

    public boolean setPassword(String oldPassword, String newPassword, String confirmNewPassword)
    {
        if(this.password.equals(oldPassword) && newPassword.equals(confirmNewPassword))
        {
            if(newPassword.length() >= 6 && newPassword.length() <= 32)
            {   
                this.password = newPassword;
                saveOnDB();
                return true;
            } 
            return false;
        }
        return false;
    }

    public void saveOnDB()
    {
        //DB
    }
}
