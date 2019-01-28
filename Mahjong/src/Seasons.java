/*
 Collaboration with IoKog 
 */
public class Seasons extends Tiles{
       protected int id;
    
    public Seasons(int id)
    {
        this.id=id;
    }
     public int getId()
    {
        return id;
    }
     public String toString()
    {
        return "Seasons : "+id;
    }
   public String getName()
     {
         return "Seasons";
     }
}
