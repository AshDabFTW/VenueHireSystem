package nz.ac.auckland.se281;

public class Catering extends Service{
  private String name;

  public Catering(int costPerPerson, String name){
    super(costPerPerson);
    this.name = name;
  }
}
