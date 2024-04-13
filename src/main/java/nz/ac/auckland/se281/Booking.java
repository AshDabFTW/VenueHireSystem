package nz.ac.auckland.se281;

public class Booking {
  private String bookingVenueCode;
  private String bookingVenueName;
  private String bookingDate;
  private String bookingEmail;
  private String bookingAttendees;
  private String bookingReference;
  private String dateMade;
  private int bookingVenueFee;

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
      String bookingReference,
      String dateMade,
      int bookingVenueFee) {
    this.bookingVenueCode = bookingVenueCode;
    this.bookingVenueName = bookingVenueName;
    this.bookingDate = bookingDate;
    this.bookingEmail = bookingEmail;
    this.bookingAttendees = bookingAttendees;
    this.bookingReference = bookingReference;
    this.dateMade = dateMade;
    this.bookingVenueFee = bookingVenueFee;
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

  public String getBookingEmail() {
    return bookingEmail;
  }

  public void setCatering(Catering catering) {
    cateringService = catering;
  }

  public void setFloral(Floral floral) {
    floralService = floral;
  }

  public void setMusic(Music music) {
    musicService = music;
  }

  public String getDateMade() {
    return dateMade;
  }

  public String getBookingAttendees() {
    return bookingAttendees;
  }

  public int getBookingVenueFee() {
    return bookingVenueFee;
  }

  public Catering getBookingCatering() {
    return cateringService;
  }

  public Music getBookingMusic() {
    return musicService;
  }

  public Floral getBookingFloral() {
    return floralService;
  }
}
