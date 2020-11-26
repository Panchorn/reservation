package com.example.reservation.controllers;

import com.example.reservation.services.ReservationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

  private ReservationService reservationService;

  @Autowired
  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping
  public List<String> getAvailableRooms(
      @RequestParam("checkin") String checkin,
      @RequestParam("checkout") String checkout
  ) {
    return reservationService.getAvailableRooms(checkin, checkout);
  }

}
