package nz.ac.auckland.se281;

public abstract class Service {
  protected int costPerPerson;
  protected String name;
  
  public Service(int costPerPerson, String name){
    this.costPerPerson = costPerPerson;
    this.name = name;
  }
}
