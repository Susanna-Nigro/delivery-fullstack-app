package com.sn.deliveryserver.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class ModifyOrderDTO {

  private DepotDTO depot;
  private List<PackageDTO> packages;
  private String shippingAddress;

}
