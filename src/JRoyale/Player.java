public class Player 
{
    private final String username;

    private String password;

    private int xp;

    private Deck deck;

    private History history;

    public Player(String username, String password, int xp, Deck deck, History history)
    {
        this.username = username;
        this.password = password;
        this.xp = xp;
        this.deck = deck;
        this.history = history;
    }
    
    public String getUsername()
    {
        return username;
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


    public History getHistory()
    {
        return history;
    }

    public void setHistory(History history)
    {
        this.history = history;
        saveOnDB();
    }

    public void addBattleResult(BattleResult battleResult)
    {
        history.addBattleResult(battleResult);
        saveOnDB();
    }

    public void saveOnDB()
    {
        //DB
    }
}
