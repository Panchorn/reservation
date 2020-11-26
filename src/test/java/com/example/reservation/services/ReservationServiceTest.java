package com.example.reservation.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.reservation.constants.DataConstant;
import com.example.reservation.models.Reservation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

  private ReservationService reservationService;

  @BeforeEach
  void setup() {
    reservationService = new ReservationService();
    DataConstant.ROOMS = Arrays.asList(
        "A1",
        "A2",
        "A3",
        "A4",
        "A5"
    );
    DataConstant.RESERVATIONS = Collections.emptyList();
  }

  @Test
  void should_ReturnListOfAvailableRooms_WhenCallGetAvailableRoomsSuccess_Case1() {
    DataConstant.RESERVATIONS = Arrays.asList(
        new Reservation("001", "A1", "0930", "1100"),
        new Reservation("002", "A2", "1200", "1230"),
        new Reservation("003", "A3", "1200", "1400"),
        new Reservation("004", "A5", "1000", "1200"),
        new Reservation("005", "A2", "1100", "1200"),
        new Reservation("006", "A3", "1400", "1500"),
        new Reservation("007", "A1", "1600", "1700"),
        new Reservation("008", "A2", "1400", "1430")
    );

    String checkin = "1200";
    String checkout = "1300";
    List<String> expected = Arrays.asList("A1", "A4", "A5");

    List<String> actual = reservationService.getAvailableRooms(checkin, checkout);

    assertEquals(expected, actual);
  }

  @Test
  void should_ReturnListOfAvailableRooms_WhenCallGetAvailableRoomsSuccess_Case2() {
    DataConstant.RESERVATIONS = Arrays.asList(
        new Reservation("001", "A1", "1000", "1300"),
        new Reservation("002", "A2", "1000", "1330"),
        new Reservation("003", "A3", "1000", "1300"),
        new Reservation("004", "A5", "1000", "1330"),
        new Reservation("005", "A4", "1000", "1300")
    );

    String checkin = "1000";
    String checkout = "1100";
    List<String> expected = Collections.emptyList();

    List<String> actual = reservationService.getAvailableRooms(checkin, checkout);

    assertEquals(expected, actual);
  }


  @Test
  void should_ReturnListOfAvailableRooms_WhenCallGetAvailableRoomsSuccess_Case3() {
    DataConstant.RESERVATIONS = Arrays.asList(
        new Reservation("001", "A1", "1000", "1300"),
        new Reservation("002", "A2", "1000", "1330"),
        new Reservation("003", "A3", "1000", "1300"),
        new Reservation("004", "A5", "1000", "1330"),
        new Reservation("005", "A4", "1000", "1300")
    );

    String checkin = "1300";
    String checkout = "1330";
    List<String> expected = Arrays.asList("A1", "A3", "A4");

    List<String> actual = reservationService.getAvailableRooms(checkin, checkout);

    assertEquals(expected, actual);
  }

  @Test
  void should_ThrowException_WhenCallGetAvailableRoomFail_WithCheckoutLessThanCheckin() {
    String checkin = "1200";
    String checkout = "1000";

    assertThrows(IllegalArgumentException.class,
        () -> reservationService.getAvailableRooms(checkin, checkout));
  }

}