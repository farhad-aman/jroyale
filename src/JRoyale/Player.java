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
            String USERNAME = "root";
            String password = "@#$mg200";

            Connection con = DriverManager.getConnection(url, USERNAME, password);
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
            String USERNAME = "root";
            String password = "@#$mg200";

            Connection con = DriverManager.getConnection(url, USERNAME, password);
            Statement st = con.createStatement();

            String insertion = "update from players" +
                    "set pass = " + '\"' + password + '\"' + ", xp = " + '\"' + xp + '\"' + ", deck = " + '\"' + getDeckString() + '\"'  +
                    " where userName=" + '\"' + username + '\"';
            st.execute(insertion);

            st.close();
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
