package nz.ac.auckland.se281;

public class Booking {
  private String bookingVenueCode;
  private String bookingVenueName;
  private String bookingDate;
  private String bookingEmail;
  private String bookingAttendees;
  private String bookingReference;

  // Service fields
  private Catering cateringService = null;
  private Floral floralService = null;
  private Music musicService = null;

  public Booking(
      String bookingVenueCode,
      String bookingVenueName,
      String bookingDate,
      String bookingEmail,
      String bookingAttendees,
      String bookingReference) {
    this.bookingVenueCode = bookingVenueCode;
    this.bookingVenueName = bookingVenueName;
    this.bookingDate = bookingDate;
    this.bookingEmail = bookingEmail;
    this.bookingAttendees = bookingAttendees;
    this.bookingReference = bookingReference;
  }

  public String getBookingVenueCode() {
    return bookingVenueCode;
  }

  public String getBookingVenueName() {
    return bookingVenueName;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getBookingReference() {
    return bookingReference;
  }

  public void setCatering(Catering catering) {
    cateringService = catering;
  }

  public void setFloral(Floral floral){
    floralService = floral;
  }

  public void setMusic(Music music){
    musicService = music;
  }

}
