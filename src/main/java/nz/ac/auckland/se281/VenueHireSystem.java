package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  // initialising venues arraylist
  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private String systemDate;

  public VenueHireSystem() {}

  public void printVenues() {
    // checking if no venues have been made
    if (venues.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    } // check if there is only one venue and print accordingly
    else if (venues.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      Venue venue = venues.get(0);
      String availableDate = findAvailableDate(venue);
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getVenueName(),
          venue.getVenueCode(),
          venue.getVenueCapacity(),
          venue.getVenueHireFee(),
          availableDate);
      return;
    } // check if there is below 10 venues and print messages
    else if (venues.size() < 10) {
      String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", ones[venues.size()], "s");
      for (Venue venue : venues) {
        String availableDate = findAvailableDate(venue);
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getVenueCapacity(),
            venue.getVenueHireFee(),
            availableDate);
      }
      return;
    } // all other cases (any above 10 venues) are print accordingly
    else {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venues.size()), "s");
      for (Venue venue : venues) {
        String availableDate = findAvailableDate(venue);
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getVenueCapacity(),
            venue.getVenueHireFee(),
            availableDate);
      }
      return;
    }
  }

  public String findAvailableDate(Venue venue) {
    // this method finds the available date for a given venue
    if (systemDate == null) {
      return "";
    }
    String availableDate = systemDate;
    boolean iterateAgain = true;
    while (iterateAgain) {
      iterateAgain = false;
      for (Booking booking : bookings) {
        if (booking.getBookingDate().equals(availableDate)
            && booking.getBookingVenueCode().equals(venue.getVenueCode())) {
          // if the venue is found in the booking list and is already booked on that day 1 is added
          // the the date and looped back through
          String[] availableDateParts = availableDate.split("/");
          int dayVal = Integer.parseInt(availableDateParts[0]) + 1;
          availableDate =
              String.format("%02d/%s/%s", dayVal, availableDateParts[1], availableDateParts[2]);
          iterateAgain = true;
        }
      }
    }
    return availableDate;
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // Check if venueName is empty
    if (venueName.trim().isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    } // Check venueCode is unique
    for (Venue venue : venues) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenueName());
        return;
      }
    } // check if capacityInput is a number and positive
    try {
      int tempNum = Integer.parseInt(capacityInput);
      if (tempNum <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    } // check if hireFeeInput is a number and positive
    try {
      int tempNum = Integer.parseInt(hireFeeInput);
      if (tempNum <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    // Create venue class instance if input arguments pass all the checks
    Venue venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    // Add to venues arraylist
    venues.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    return;
  }

  public void setSystemDate(String dateInput) {
    // Sets the system date and prints
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    // Checks if there is a system date and prints
    if (systemDate != null) {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    } else {
      MessageCli.CURRENT_DATE.printMessage("not set");
    }
  }

  public void makeBooking(String[] options) {
    // check if systemDate has been set
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }
    // check if a venue has been made
    if (venues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }
    // assigning variable names to input string array for understandability
    String bookingVenueCode = options[0];
    String bookingDate = options[1];
    String bookingEmail = options[2];
    String bookingAttendees = options[3];

    // checks if date is in the past
    String[] systemDateParts = systemDate.split("/");
    String[] bookingDateParts = bookingDate.split("/");
    if (Integer.parseInt(bookingDateParts[2]) < Integer.parseInt(systemDateParts[2])) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
      return;
    } else if (Integer.parseInt(bookingDateParts[1]) < Integer.parseInt(systemDateParts[1])) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
      return;
    } else if (Integer.parseInt(bookingDateParts[0]) < Integer.parseInt(systemDateParts[0])) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
      return;
    }

    // checks if there is a venue with the same venue code
    int count = 0;
    for (Venue venue : venues) {
      if (venue.getVenueCode().equals(bookingVenueCode)) {
        count++;
      }
    }
    if (count == 0) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(bookingVenueCode);
      return;
    }

    // check if booking has already been made on the same day
    for (Booking booking : bookings) {
      if (booking.getBookingVenueCode().equals(bookingVenueCode)
          && booking.getBookingDate().equals(bookingDate)) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            booking.getBookingVenueName(), bookingDate);
        return;
      }
    }

    // Has passed all the checks so will check if attendence values need to change
    for (Venue venue : venues) {
      if (venue.getVenueCode().equals(bookingVenueCode)) {
        if (Integer.parseInt(bookingAttendees) < Integer.parseInt(venue.getVenueCapacity()) / 4) {
          String newBookingAttendees =
              String.valueOf(Integer.parseInt(venue.getVenueCapacity()) / 4);
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
              bookingAttendees, newBookingAttendees, venue.getVenueCapacity());
          bookingAttendees = newBookingAttendees;
        } else if (Integer.parseInt(bookingAttendees)
            > Integer.parseInt(venue.getVenueCapacity())) {
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
              bookingAttendees, venue.getVenueCapacity(), venue.getVenueCapacity());
          bookingAttendees = venue.getVenueCapacity();
        }
      }
    }

    // Makes the booking and adds to the booking arraylist
    String bookingVenueName = "";
    int bookingVenueFee = 0;
    for (Venue venue : venues) {
      if (venue.getVenueCode().equals(bookingVenueCode)) {
        bookingVenueName = venue.getVenueName();
        bookingVenueFee = Integer.parseInt(venue.getVenueHireFee());
      }
    }
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    Booking booking =
        new Booking(
            bookingVenueCode,
            bookingVenueName,
            bookingDate,
            bookingEmail,
            bookingAttendees,
            bookingReference,
            systemDate,
            bookingVenueFee);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, bookingVenueName, bookingDate, bookingAttendees);
    bookings.add(booking);
  }

  public void printBookings(String venueCode) {
    // check is venueCode input is valid and prints header
    int count = 0;
    String venueName = "";
    for (Venue venue : venues) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venue.getVenueName());
        venueName = venue.getVenueName();
        count++;
      }
    }
    if (count == 0) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // prints all bookings related to venueCode
    count = 0;
    for (Booking booking : bookings) {
      if (booking.getBookingVenueCode().equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            booking.getBookingReference(), booking.getBookingDate());
        count++;
      }
    }
    if (count == 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // gets booking from booking reference and prints if not found
    Booking booking = getBooking(bookingReference);
    if (booking == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    }

    // gets catering values from enum
    int costPerPerson = cateringType.getCostPerPerson();
    String name = cateringType.getName();

    // creates new catering instance and adds to booking
    Catering catering = new Catering(costPerPerson, name, booking.getBookingAttendees());
    booking.setCatering(catering);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + name + ")", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    // gets booking from booking reference and prints if not found
    Booking booking = getBooking(bookingReference);
    if (booking == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }

    // creates new music instance and adds to booking
    Music music = new Music();
    booking.setMusic(music);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // gets booking
    Booking booking = getBooking(bookingReference);
    if (booking == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }

    // gets floral values from enum
    int cost = floralType.getCost();
    String name = floralType.getName();

    // creates new floral type and adds to booking
    Floral floral = new Floral(cost, name);
    booking.setFloral(floral);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + name + ")", bookingReference);
  }

  public Booking getBooking(String bookingReference) {
    // method to get booking from booking reference
    for (Booking booking : bookings) {
      if (booking.getBookingReference().equals(bookingReference)) {
        return booking;
      }
    }
    return null;
  }

  public void viewInvoice(String bookingReference) {
    // gets booking from booking reference and prints if not found
    Booking booking = getBooking(bookingReference);
    if (booking == null) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }

    // prints top half of invoice
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        booking.getBookingEmail(),
        booking.getDateMade(),
        booking.getBookingDate(),
        booking.getBookingAttendees(),
        booking.getBookingVenueName());

    // initiallising cost values
    int venueCost = booking.getBookingVenueFee();
    int cateringCost = 0;
    int musicCost = 0;
    int floralCost = 0;

    // going through find the costs per service, changing values and printing
    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(String.valueOf(venueCost));
    if (booking.getBookingCatering() != null) {
      Catering catering = booking.getBookingCatering();
      cateringCost = catering.getServiceCost();
      MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
          catering.getCateringName(), String.valueOf(cateringCost));
    }
    if (booking.getBookingMusic() != null) {
      Music music = booking.getBookingMusic();
      musicCost = music.getServiceCost();
      MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(String.valueOf(musicCost));
    }
    if (booking.getBookingFloral() != null) {
      Floral floral = booking.getBookingFloral();
      floralCost = floral.getServiceCost();
      MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
          floral.getFloralType(), String.valueOf(floralCost));
    }

    // calculating and printing total costs
    int totalCost = venueCost + cateringCost + musicCost + floralCost;
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(String.valueOf(totalCost));
  }
}
