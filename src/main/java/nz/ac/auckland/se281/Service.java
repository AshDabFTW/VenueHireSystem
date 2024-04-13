package nz.ac.auckland.se281;

public abstract class Service {
  protected int cost;

  public Service(int cost) {
    this.cost = cost;
  }

  public abstract int getServiceCost();
}
