package com.sn.deliveryserver.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class OrderDTO {

  private int id;
  private SupplierDTO supplier;
  private DepotDTO depot;
  private List<PackageDTO> packages;
  private String shippingAddress;
  private StatusEnum status;
  private LocalDateTime createdAt;

}
