/*
Collaboration with IoKog 
 */
public class Characters extends Tiles {
    protected int id;
    
    public Characters(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public String toString()
    {
        return "Char  : "+id;
    }
   
    public String getName()
     {
         return "Characters";
     }
}
