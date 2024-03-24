package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  //initialising venues arraylist
  private ArrayList<Venue> venues = new ArrayList<Venue>();

  public VenueHireSystem() {}

  public void printVenues() {
    // checking if no venues have been made
    if(venues.isEmpty()){
      MessageCli.NO_VENUES.printMessage();
      return;
    } else if(venues.size() == 1){

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
      int tempnum = Integer.parseInt(capacityInput);
      if (tempnum <= 0){
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity"," positive");
        return;
      }
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    } // check if hireFeeInput is a number and positive
    try {
      int tempnum = Integer.parseInt(hireFeeInput);
      if (tempnum <= 0){
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee"," positive");
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
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
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
