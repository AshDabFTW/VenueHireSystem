package nz.ac.auckland.se281;

public class Catering extends Service {
  private String name;
  private String bookingAttendees;

  public Catering(int costPerPerson, String name, String bookingAttendees) {
    super(costPerPerson);
    this.name = name;
    this.bookingAttendees = bookingAttendees;
  }

  @Override
  public int getServiceCost() {
    int totalCost = this.cost * Integer.parseInt(bookingAttendees);
    return totalCost;
  }

  public String getCateringName() {
    return name;
  }
}
