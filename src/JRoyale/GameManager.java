import javafx.application.Platform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

public class GameManager
{
    private static GameManager gameManager = new GameManager();

    private final int frameRate = 40;

    private Player currentPlayer;

    private GameManager()
    {

    }

    public static GameManager getInstance()
    {
        return gameManager;
    }

    /**
     * process the given information for log in
     * @return number for status//-1->username does not exist//0->password does not match//1->information is accurate
     * */
    public int login(String username, String userPassword)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";
            String userName = "root";
            String password = "@#$mg200";

            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            String insertion = "select * from players where userName=" + userName;
            st.execute(insertion);

            ResultSet rs = st.getResultSet();

            if(!rs.next()) {
                st.close();
                rs.close();

                return -1;
            }
            else if(rs.getString(2).equals(userPassword)){
                currentPlayer = new Player(rs.getString(1), rs.getString(2), rs.getInt(3), getDeck(rs.getString(4)), getHistory(rs.getString(1)));

                st.close();
                rs.close();

                return 1;
            }
            else {
                st.close();
                rs.close();

                return 0;
            }
        }
        catch (Exception e) {
            return -1;
        }
    }

    private History getHistory(String playerName) {
        History history = new History();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";
            String userName = "root";
            String password = "@#$mg200";

            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            String insertion = "select * from history where userName=" + userName;
            st.execute(insertion);

            ResultSet rs = st.getResultSet();

            while (rs.next())
                history.addBattleResult(new BattleResult(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));

            st.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return history;
    }

    private Deck getDeck(String deckString){
        Deck deck = new Deck();
        StringTokenizer tokens = new StringTokenizer(deckString, ":");

        while(tokens.hasMoreTokens())
            deck.addCard(getCard(tokens.nextToken()));

        return deck;
    }

    private Card getCard(String id) {
        Card card;

        switch (id){
            case "barbarians":
                card = new Barbarians();
                break;
            case "wizard":
                card = new Wizard();
                break;
            case "giant":
                card = new Giant();
                break;
            case "inferno":
                card = new Inferno();
                break;
            case "archer":
                card = new Archer();
                break;
            case "rage":
                card = new Rage();
                break;
            case "arrows":
                card = new Arrows();
                break;
            case "dragon":
                card = new Dragon();
                break;
            case "pekka":
                card = new Pekka();
                break;
            case "valkyrie":
                card = new Valkyrie();
                break;
            case "fireBall":
                card = new Fireball();
                break;
            default:
                card = new Cannon();
        }
        return card;
    }
    /**
     * checks the information and create the new player if the given username is not used before
     * @return status->-2 : passwords are not the same//-1->information is not accurate//0->username is occupied//1-> player is added
     * */
    public int signUp(String username, String userPassword, String confirmPassword)
    {
        if(!userPassword.equals(confirmPassword))
            return -2;
        else if(!(username.length() >= 3) || !(userPassword.length() >= 6) || !(userPassword.length() <= 32))
            return -1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";
            String userName = "root";
            String password = "@#$mg200";

            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();

            String insertion = "select * from players where userName=" + userName;
            st.execute(insertion);

            ResultSet rs = st.getResultSet();

            if(!rs.next()){
                insertion = "insert into players values(" + userName + "," + userPassword + ", " + 0 + ")";

                rs.close();
                st.close();

                return 1;
            }
            rs.close();
            st.close();

            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
