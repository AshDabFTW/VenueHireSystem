package nz.ac.auckland.se281;

public class Floral extends Service {
  private String floralType;

  public Floral(int cost, String floralType) {
    super(cost);
    this.floralType = floralType;
  }

  @Override
  public int getServiceCost() {
    return this.cost;
  }

  public String getFloralType() {
    return floralType;
  }
}
