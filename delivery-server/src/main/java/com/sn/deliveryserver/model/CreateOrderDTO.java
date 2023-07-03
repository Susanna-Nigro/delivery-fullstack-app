package com.sn.deliveryserver.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {

  private SupplierDTO supplier;
  private DepotDTO depot;
  private List<PackageDTO> packages;
  private String shippingAddress;

}
