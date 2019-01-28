/*
Collaboration with IoKog 
 */
public class Circle extends Tiles{
       protected int id;
    
    public Circle(int id)
    {
        this.id=id;
    }
     public int getId()
    {
        return id;
    }
     public String toString()
    {
        return "Circle : "+id;
    }
  public String getName()
     {
         return "Circle";
     }
}
