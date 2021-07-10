import javafx.application.Platform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
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


    public int login(String username, String userPassword)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";
            String userName = "root";
            String password = "<password>";

            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            String insertion = "select * from players where userName=" + userName;
            st.execute(insertion);

            ResultSet rs = st.getResultSet();
            rs.next();

            if(rs.getString(1) == null)
                return -1;
            else if(rs.getString(2).equals(userPassword)){
                Player player = new Player(rs.getString(1), rs.getString(2), rs.getInt(3), getDeck(rs.getString(4)), getHistory(rs.getString(1)));
                return 1;
            }
            else
                return 0;
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
            String password = "<password>";

            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            String insertion = "select * from history where userName=" + userName;
            st.execute(insertion);

            ResultSet rs = st.getResultSet();

            while (rs.next())
                history.addBattleResult(new BattleResult(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
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

    public void signUp(String username, String password, String confirmPassword)
    {
        //DB
    }

    public void setCurrentPlayer(Player player)
    {
        this.currentPlayer = player;
    }
}
