public class Princess extends Building
{
    private final int[] damage = {0, 50, 54, 58, 62, 69};

    public Princess() 
    {
        super("Princess", 0, 800, "both", 7.5, 1000000, new int[] {0, 1400, 1512, 1624, 1750, 1890});
    }

    @Override
    public void loadImages() 
    {
        
    }
    
}
