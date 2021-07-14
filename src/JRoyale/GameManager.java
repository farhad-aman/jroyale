import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class GameManager
{
    private static GameManager gameManager = new GameManager();

    public static final String dbPassword = "@#$mg200";

    public static final int FPS = 40;
    
    private Player currentPlayer;
    
    private Battle battle;

    private GameManager()
    {

    }

    public static GameManager getInstance()
    {
        return gameManager;
    }

    public void logout()
    {
        currentPlayer = null;
        battle = null;
    }

    /**
     * process the given information for log in
     * @return number for status//-1->username does not exist//0->password does not match//1->information is accurate
     * */
    public int login(String username, String userPassword)
    {System.out.println("game manager line 41 started");
        try 
        {System.out.println("game manager line 43 started");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";

            Connection con = DriverManager.getConnection(url, "root", GameManager.dbPassword);
            Statement st = con.createStatement();
            String insertion = "select * from players where userName=" + '\"' + username + '\"';
            st.execute(insertion);

            ResultSet rs = st.getResultSet();
System.out.println("game manager line 53 started");
            if(!rs.next()) 
            {
                System.out.println("game manager line 56 started");
                st.close();
                rs.close();

                return -1;
            }
            else if(rs.getString(2).equals(userPassword))
            {
                System.out.println("game manager line 64 started");
                currentPlayer = new Player(rs.getString(1), rs.getString(2), rs.getInt(3), getDeck(rs.getString(4)), getHistory(rs.getString(1)));

                System.out.println("game manager line 67 started");
                System.out.println(currentPlayer.getDeck());

                st.close();
                rs.close();

                return 1;
            }
            else 
            {
                System.out.println("game manager line 77 started");
                st.close();
                rs.close();

                return 0;
            }
        }
        catch (Exception e) 
        {
            return -1;
        }
    }

    private History getHistory(String playerName) 
    {
        History history = new History();

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";
            String USERNAME = "root";
            String password = GameManager.dbPassword;

            Connection con = DriverManager.getConnection(url, USERNAME, password);
            Statement st = con.createStatement();
            String insertion = "select * from gameHistory where userName=" + '\"' + playerName + '\"';
            st.execute(insertion);

            ResultSet rs = st.getResultSet();

            while (rs.next())
                history.addBattleResult(new BattleResult(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));

            st.close();
            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return history;
    }

    private Deck getDeck(String deckString)
    {
        Deck deck = new Deck();
        
        for(String id : deckString.split(":"))
        {
            deck.addCard(getCard(id));
        }

        return deck;
    }

    private Card getCard(String id) 
    {
        Card card;

        switch (id){
            case "Barbarians":
                card = new Barbarians();
                break;
            case "Wizard":
                card = new Wizard();
                break;
            case "Giant":
                card = new Giant();
                break;
            case "Inferno":
                card = new Inferno();
                break;
            case "Archer":
                card = new Archer();
                break;
            case "Rage":
                card = new Rage();
                break;
            case "Arrows":
                card = new Arrows();
                break;
            case "Dragon":
                card = new Dragon();
                break;
            case "Pekka":
                card = new Pekka();
                break;
            case "Valkyrie":
                card = new Valkyrie();
                break;
            case "Fireball":
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
        System.out.println("gameManager line 160 started");
        if(!userPassword.equals(confirmPassword)) 
        {
            System.out.println("gameManager line 162 started");
            return -2;
        }
        else if(!(username.length() <= 30) || !(username.length() >= 3) || !(userPassword.length() >= 6) || !(userPassword.length() <= 32)) 
        {
            System.out.println("gameManager line 166 started");
            return -1;
        }

        try 
        {
            System.out.println("gameManager line 171 started");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";

            Connection con = DriverManager.getConnection(url, "root", GameManager.dbPassword);
            Statement st = con.createStatement();

            String insertion = "select * from players where userName=" + '\"' + username + '\"';
            st.execute(insertion);

            ResultSet rs = st.getResultSet();

            if(!rs.next())
            {
                System.out.println("gameManager line 186 started\t\tuserName:" + username);
                String deck = createDeck(username, userPassword);

                System.out.println("gameManager line 189 started\t\t" + "UserName:" + username);
                insertion = "insert into players values(" + '\"' +  username + '\"' + "," +  '\"' +  userPassword + '\"' + ", " + 0 + ", " +  '\"' +  deck + '\"' + ")";
                st.execute(insertion);

                rs.close();
                st.close();

                return 1;
            }
            System.out.println("gameManager line 198 started\t\t" + username);
            rs.close();
            st.close();

            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    private String createDeck(String username, String userPassword) 
    {
        System.out.println("gameManager line 209 started");
        String deck = "";
        ArrayList<String> cards = new ArrayList<>();
        cards.add("fireBall");
        cards.add("valkyrie");
        cards.add("pekka");
        cards.add("barbarians");
        cards.add("wizard");
        cards.add("giant");
        cards.add("dragon");
        cards.add("arrows");
        cards.add("cannon");
        cards.add("rage");
        cards.add("inferno");
        cards.add("archer");

        Player player = new Player(username, userPassword, 0, new Deck(), null);
        Random rand = new Random();

        while(cards.size() > 4)
        {
            int index = rand.nextInt(cards.size());

            deck = deck.concat(cards.get(index) + ":");

            player.getDeck().addCard(getCard(cards.get(index)));

            cards.remove(index);
        }
        System.out.println("gameManager line 237 started");
        currentPlayer = player;

        return deck;
    }

    public Player getCurrentPlayer() 
    {
        return currentPlayer;
    }

    public boolean createBattle(int botDifficulty)
    {
        if(botDifficulty == 1)
        {
            this.battle = new Battle(new Bot1());
            return true; 
        }
        else if(botDifficulty == 2)
        {
            this.battle = new Battle(new Bot2());
            return true;
        }
        else if(botDifficulty == 3)
        {
            this.battle = new Battle(new Bot3());
            return true;
        }
        return false;
    }

    public int battleStep()
    {
        return battle.step();
    }

    public Battle getBattle() {
        return battle;
    }
}
