package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  // initialising venues arraylist
  private ArrayList<Venue> venues = new ArrayList<Venue>();
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
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getVenueName(),
          venue.getVenueCode(),
          venue.getVenueCapacity(),
          venue.getVenueHireFee());
      return;
    } // check if there is below 10 venues and print messages
    else if (venues.size() < 10) {
      String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", ones[venues.size()], "s");
      for (Venue venue : venues) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getVenueCapacity(),
            venue.getVenueHireFee());
      }
      return;
    } // all other cases (any above 10 venues) are print accordingly
    else {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venues.size()), "s");
      for (Venue venue : venues) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getVenueCapacity(),
            venue.getVenueHireFee());
      }
      return;
    }
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
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    if (systemDate != null) {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    } else {
      MessageCli.CURRENT_DATE.printMessage("not set");
    }
  }

  public void makeBooking(String[] options) {
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    }
    if (venues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
    }
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
