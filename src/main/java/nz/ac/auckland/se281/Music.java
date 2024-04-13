package nz.ac.auckland.se281;

public class Music extends Service {

  public Music() {
    super(500);
  }

  @Override
  public int getServiceCost() {
    return this.cost;
  }
}
