import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * the player that logins in the game and all its details
 * @version 1.0
 */
public class Player
{
    /**
     * the username of the player
     */
    private final String username;

    /**
     * the password of the player
     */
    private String password;

    /**
     * the xp of the player
     */
    private int xp;

    /**
     * the deck of the player
     */
    private Deck deck;

    /**
     * the battles history of the player
     */
    private History history;

    /**
     * creates a new player
     * @param username
     * @param password
     * @param xp
     * @param deck
     * @param history
     */
    public Player(String username, String password, int xp, Deck deck, History history)
    {
        this.username = username;
        this.password = password;
        this.xp = xp;
        this.deck = deck;
        this.history = history;
    }
    
    /**
     * @return username of the player
     */
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
    
    /**
     * @return xp of the player
     */
    public int getXp()
    {
        return xp;
    }

    /**
     * sets xp of the player and save it on data base
     * @param xp
     */
    public void setXp(int xp)
    {
        this.xp = xp;
        saveOnDB();
    }

    /**
     * @return deck of the player
     */
    public Deck getDeck()
    {
        return deck;
    }

    /**
     * sets deck of the player and save it on data base
     * @param deck
     */
    public void setDeck(Deck deck)
    {
        this.deck = deck;
        saveOnDB();
    }

    /**
     * @return the battles history of the player
     */
    public History getHistory()
    {
        return history;
    }

    /**
     * adds a new battle result to the history and save it on data base
     * @param battleResult
     */
    public void addBattleResult(BattleResult battleResult)
    {
        history.addBattleResult(battleResult);

        try
        {
            System.out.println("salam" + '\"' + xp + '\"');
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";

            Connection con = DriverManager.getConnection(url, "root", GameManager.dbPassword);
            Statement st = con.createStatement();

            String insertion = "insert into gameHistory values(" + '\"' + username + '\"' + ", " + '\"' + battleResult.getBotDifficulty() + '\"' + ", " + '\"' + battleResult.getYourScore() + '\"' + ", " + '\"' + battleResult.getEnemyScore() + '\"' + ")";
            st.execute(insertion);
            System.out.println("salam" + '\"' + xp + '\"');

            st.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return the level of the player based on xp
     */
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

    /**
     * this method is the main method that saves new player information in players table on data base 
     * this app uses MySQL data base
     */
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

    /**
     * @return string that uses for saving deck on data base 
     */
    private String getDeckString() 
    {
        String deckString = "";

        for(Card card : deck.getCards())
            deckString = deckString.concat(card.getId() + ":");

        return deckString;
    }
}
