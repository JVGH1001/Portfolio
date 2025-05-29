
/**
 * Write a description of class HanoiDemo here.
 * 
 * @author Jeffrey VanMeter
 * 
 */
public class HanoiDemo
{
   public static void main(String args[])
   {
       solveTowers(64, "A", "B", "C");
   }
   
  public static void solveTowers(int n, String source, String dest, String spare)
   {
       if(n == 1)
       {
           System.out.println("Move from " + source + " to " + dest);
       }
       else
       {
           solveTowers(n-1, source, spare, dest); //move all but last
           solveTowers(1, source, dest, spare); //move last to dest
           solveTowers(n-1, spare, dest, source); //move rest
           
       }
   }
}
