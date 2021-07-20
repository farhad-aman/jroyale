import java.util.ArrayList;

/**
 * the battle history of a player
 */
public class History 
{
    /**
     * the list battle results
     */
    private ArrayList<BattleResult> battleResults;

    /**
     * creates a new history
     */
    public History()
    {
        battleResults = new ArrayList<>();
    }

    /**
     * adds a new battle result to the list
     * @param battleResult
     */
    public void addBattleResult(BattleResult battleResult)
    {
        battleResults.add(battleResult);
    }

    /**
     * sets list of battle results
     * @param battleResults
     */
    public void setBattleResults(ArrayList<BattleResult> battleResults)
    {
        this.battleResults = battleResults;
    }

    /**
     * @return list of battle results
     */
    public ArrayList<BattleResult> getBattleResults()
    {
        return battleResults;
    }
}
