package com.sn.deliveryserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DepotDTO {

  private int id;
  private String name;
  private String address;

}
