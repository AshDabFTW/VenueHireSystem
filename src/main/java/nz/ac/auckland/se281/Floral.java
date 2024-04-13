package nz.ac.auckland.se281;

public class Floral extends Service{
  private String floralType;
  
  public Floral(int cost, String floralType){
    super(cost);
    this.floralType = floralType;
  }
}
