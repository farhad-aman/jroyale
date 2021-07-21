import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * this class is the main model class of the game written in singleton design pattern and all logic and data bases
 * @version 1.0
 */
public class GameManager
{
    /**
     * the single instance of game manager
     */
    private static GameManager gameManager = new GameManager();

    /**
     * the password of MySQL database
     */
    public static final String dbPassword = "@#$mg200";

    /**
     * the frame rate of the game
     */
    public static final int FPS = 20;
    
    /**
     * the current player that logins to the game
     */
    private Player currentPlayer;

    /**
     * the current bot in the battle
     */
    private Bot currentBot;
    
    /**
     * the current battle of the game
     */
    private Battle battle;

    /**
     * crates a new game manager
     */
    private GameManager()
    {

    }

    /**
     * @return single instance of the game manager
     */
    public static GameManager getInstance()
    {
        return gameManager;
    }

    /**
     * logouts from game
     */
    public void logout()
    {
        currentPlayer = null;
        currentBot = null;
        battle = null;
    }

    /**
     * process the given information for log in
     * @return number for status//-1->username does not exist//0->password does not match//1->information is accurate
     * */
    public int login(String username, String userPassword)
    {
        System.out.println(username + " : " + userPassword);
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";

            Connection con = DriverManager.getConnection(url, "root", GameManager.dbPassword);
            Statement st = con.createStatement();
            String insertion = "select * from players where userName=" + '\"' + username + '\"';
            st.execute(insertion);

            ResultSet rs = st.getResultSet();
            if(!rs.next())
            {
                st.close();
                rs.close();

                return -1;
            }
            else if(rs.getString(2).equals(userPassword))
            {
                currentPlayer = new Player(rs.getString(1), rs.getString(2), rs.getInt(3), getDeck(rs.getString(4)), getHistory(rs.getString(1)));

                st.close();
                rs.close();

                return 1;
            }
            else
            {
                st.close();
                rs.close();

                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @param playerName
     * @return battle history of the given player
     */
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

    /**
     * @param deckString
     * @return deck from deck string 
     */
    private Deck getDeck(String deckString)
    {
        Deck deck = new Deck();

        for(String id : deckString.split(":"))
        {
            deck.addCard(getCard(id));
        }

        return deck;
    }

    /**
     * @param id
     * @return card based on its ID
     */
    private Card getCard(String id)
    {
        Card card;

        switch (id)
        {
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
        if(!userPassword.equals(confirmPassword))
        {
            return -2;
        }
        else if(!(username.length() <= 30) || !(username.length() >= 3) || !(userPassword.length() >= 6) || !(userPassword.length() <= 32))
        {
            return -1;
        }

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JRoyale";

            Connection con = DriverManager.getConnection(url, "root", GameManager.dbPassword);
            Statement st = con.createStatement();

            String insertion = "select * from players where userName=" + '\"' + username + '\"';
            st.execute(insertion);

            ResultSet rs = st.getResultSet();

            if(!rs.next())
            {
                // System.out.println("gameManager line 186 started\t\tuserName:" + username);
                String deck = createDeck(username, userPassword);

                insertion = "insert into players values(" + '\"' +  username + '\"' + "," +  '\"' +  userPassword + '\"' + ", " + 0 + ", " +  '\"' +  deck + '\"' + ")";
                st.execute(insertion);

                rs.close();
                st.close();

                return 1;
            }
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

    /**
     * create a initial deck for given player
     * @param username
     * @param userPassword
     * @return deck string
     */
    private String createDeck(String username, String userPassword)
    {
        // System.out.println("gameManager line 209 started");
        String deck = "";
        ArrayList<String> cards = new ArrayList<>();
        cards.add("Fireball");
        cards.add("Valkyrie");
        cards.add("Pekka");
        cards.add("Barbarians");
        cards.add("Wizard");
        cards.add("Giant");
        cards.add("Dragon");
        cards.add("Arrows");
        cards.add("Cannon");
        cards.add("Rage");
        cards.add("Inferno");
        cards.add("Archer");

        Player player = new Player(username, userPassword, 0, new Deck(), getHistory(username));
        Random rand = new Random();

        while(cards.size() > 4)
        {
            int index = rand.nextInt(cards.size());

            deck = deck.concat(cards.get(index) + ":");

            player.getDeck().addCard(getCard(cards.get(index)));

            cards.remove(index);
        }
        currentPlayer = player;

        return deck;
    }

    /**
     * @return current player
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * finishes current battle
     */
    public void finishBattle()
    {
        currentBot = null;
        battle = null;
    }

    /**
     * creates a new battle for the game
     * @param botDifficulty
     * @return is battle created
     */
    public boolean createBattle(int botDifficulty)
    {
        if(botDifficulty == 1)
        {
            currentBot = new Bot1();
            this.battle = new Battle(currentBot);
            return true;
        }
        else if(botDifficulty == 2)
        {
            currentBot = new Bot2();
            this.battle = new Battle(currentBot);
            return true;
        }
        else if(botDifficulty == 3)
        {
            currentBot = new Bot3();
            this.battle = new Battle(currentBot);
            return true;
        }
        return false;
    }

    /**
     * the main step that battle done every frame
     * @return the status of the battle after step
     */
    public int battleStep()
    {
        return battle.step();
    }

    /**
     * @return current battle
     */
    public Battle getBattle()
    {
        return battle;
    }

    /**
     * @return current bot
     */
    public Bot getCurrentBot()
    {
        return currentBot;
    }

    /**
     * @param difficulty for the deck
     * @return a random deck for the bot
     */
    public static Deck getRandomDeck(int difficulty)
    {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Archer());
        cards.add(new Arrows());
        cards.add(new Barbarians());
        cards.add(new Cannon());
        cards.add(new Dragon());
        cards.add(new Fireball());
        cards.add(new Giant());
        cards.add(new Inferno());
        cards.add(new Pekka());
        cards.add(new Rage());
        cards.add(new Valkyrie());
        cards.add(new Wizard());
        Collections.shuffle(cards);
        Deck deck = new Deck();

        if(difficulty == 1)
        {
            cards.remove(0);
            cards.remove(0);
            cards.remove(0);
            cards.remove(0);
            for (Card c : cards) 
            {
                deck.addCard(c);
            }
        }
        else if(difficulty == 2)
        {
            int spellCount = 0, buildingCount = 0, troopCount = 0;

            for(Card c : cards)
            {
                if(c instanceof Spell && spellCount < 1)
                {
                    deck.addCard(c);
                    spellCount++;
                }
                else if(c instanceof Building && buildingCount < 1)
                {
                    deck.addCard(c);
                    buildingCount++;
                }
                else if(c instanceof Troop && troopCount < 6)
                {
                    deck.addCard(c);
                    troopCount++;
                }
            }
        }
        return deck;
    }
}
