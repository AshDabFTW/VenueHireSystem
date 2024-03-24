package nz.ac.auckland.se281;

public class Venue {
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;

  public Venue(String venueName, String venueCode, String capacityInput, String hireFeeInput){
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hireFeeInput;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getVenueName() {
    return venueName;
  }

}
