package com.example.reservation.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {

  private String id;
  private String roomId;
  private String checkin;
  private String checkout;

}
