import java.util.ArrayList;

public class History 
{
    private ArrayList<BattleResult> battleResults;

    public History()
    {
        battleResults = new ArrayList<>();
    }

    public void addBattleResult(BattleResult battleResult)
    {
        battleResults.add(battleResult);
    }

    public void setBattleResults(ArrayList<BattleResult> battleResults)
    {
        this.battleResults = battleResults;
    }

    public ArrayList<BattleResult> getBattleResults()
    {
        return battleResults;
    }
}
