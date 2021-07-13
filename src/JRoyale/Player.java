import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
    
    /**
     * changes the password of the player
     * @param oldPassword
     * @param newPassword
     * @param confirmNewPassword
     * @return 0 if old password is incorrect // 1 if new password not equla to confirm new password // 2 if new password lenth is invalid // 3 successfully changed
     */
    public int setPassword(String oldPassword, String newPassword, String confirmNewPassword)
    {
        if(this.password.equals(oldPassword))
        {
            if(newPassword.equals(confirmNewPassword))
            {
                if(newPassword.length() >= 6 && newPassword.length() <= 32)
                {   
                    this.password = newPassword;
                    saveOnDB();
                    return 3;
                } 
                else
                {
                    return 2;
                }
            }
            else
            {
                return 1;
            }
        }
        else 
        {
            return 0;
        }
    }
    
    public int getXp()
    {
        return xp;
    }

    public void setXp(int xp)
    {
        this.xp = xp;
        saveOnDB();
    }

    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
        saveOnDB();
    }

    public History getHistory()
    {
        return history;
    }

    public void addBattleResult(BattleResult battleResult)
    {
        history.addBattleResult(battleResult);

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";

            Connection con = DriverManager.getConnection(url, "root", GameManager.dbPassword);
            Statement st = con.createStatement();

            String insertion = "insert into gameHistory values(userName = " + '\"' + username + '\"' + ", botDifficulty = " + '\"' + battleResult.getBotDifficulty() + '\"' + ", yourScore = " + '\"' + battleResult.getYourScore() + '\"' + ", enemyScore = " + '\"' + battleResult.getEnemyScore() + '\"' + ")";
            st.execute(insertion);

            st.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getLevel()
    {
        if(xp < 300)
        {
            return 1;
        }
        else if(xp < 500)
        {
            return 2;
        }
        else if(xp < 900)
        {
            return 3;
        }
        else if(xp < 1700)
        {
            return 4;
        }
        else if(xp >= 1700)
        {
            return 5;
        }
        return -1;
    }

    public void saveOnDB()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";

            Connection con = DriverManager.getConnection(url, "root", GameManager.dbPassword);
            Statement st = con.createStatement();

            String insertion = "update players" +
                    " set pass = " + '\"' + password + '\"' + ", xp = " + '\"' + xp + '\"' + ", deck = " + '\"' + getDeckString() + '\"'  +
                    " where userName=" + '\"' + username + '\"';
            st.execute(insertion);

            st.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getDeckString() 
    {
        String deckString = "";

        for(Card card : deck.getCards())
            deckString = deckString.concat(card.getId() + ":");

        return deckString;
    }
}
