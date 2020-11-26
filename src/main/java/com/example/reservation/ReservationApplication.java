package com.example.reservation;

import com.example.reservation.constants.DataConstant;
import com.example.reservation.models.Reservation;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReservationApplication.class, args);
    initialData();
  }

  public static void initialData() {
    initialRoomData();
    initialReservationData();
  }

  private static void initialRoomData() {
    DataConstant.ROOMS = Arrays.asList(
        "A1",
        "A2",
        "A3",
        "A4",
        "A5"
    );
  }

  private static void initialReservationData() {
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
  }

}
