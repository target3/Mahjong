/*
Collaboration with IoKog 
 */
public class Bamboo extends Tiles{
       protected int id;
    
    public Bamboo(int id)
    {
        this.id=id;
    }
     public int getId()
    {
        return id;
    }
     public String toString()
    {
        return "Bamboo : "+id;
    }
       
     public String getName()
     {
         return "Bamboo";
     }
}
