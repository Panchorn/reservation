package com.example.reservation.services;

import com.example.reservation.constants.DataConstant;
import com.example.reservation.models.Reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  public List<String> getAvailableRooms(String checkin, String checkout) {
    validateParameters(checkin, checkout);
    return findAvailableRooms(checkin, checkout);
  }

  private void validateParameters(String checkin, String checkout) {
    if (convertTimeStringToInteger(checkin) > convertTimeStringToInteger(checkout)) {
      throw new IllegalArgumentException("Time is invalid");
    }
  }

  private List<String> findAvailableRooms(String checkin, String checkout) {
    List<String> notAvailableRooms = getNotAvailableRooms(checkin, checkout);
    List<String> allRooms = new ArrayList<>(DataConstant.ROOMS);
    allRooms.removeAll(notAvailableRooms);
    return allRooms;
  }

  private List<String> getNotAvailableRooms(String checkin, String checkout) {
    return DataConstant.RESERVATIONS
        .stream()
        .filter(it -> !isNotOverlap(
            convertTimeStringToInteger(checkin),
            convertTimeStringToInteger(checkout),
            convertTimeStringToInteger(it.getCheckin()),
            convertTimeStringToInteger(it.getCheckout())
        ))
        .map(Reservation::getRoomId)
        .distinct()
        .collect(Collectors.toList());
  }

  private Boolean isNotOverlap(Integer c1, Integer c2, Integer r1, Integer r2) {
    return (c1 >= r2) || (c2 <= r1);
  }

  private Integer convertTimeStringToInteger(String string) {
    return Integer.parseInt(string);
  }

}
