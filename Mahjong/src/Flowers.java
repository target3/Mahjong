/*
Collaboration with IoKog 
 */
public class Flowers extends Tiles{
      protected int id;
    
    public Flowers(int id)
    {
        this.id=id;
    } 
     public int getId()
    {
        return id;
    }
     public String toString()
    {
        return "Flowers : "+id;
    }
   public String getName()
     {
         return "Flowers";
     }
}
