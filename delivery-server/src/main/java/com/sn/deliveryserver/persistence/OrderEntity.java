package com.sn.deliveryserver.persistence;

import com.sn.deliveryserver.model.StatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private int id;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private SupplierEntity supplier;

  @ManyToOne
  @JoinColumn(name = "depot_id")
  private DepotEntity depot;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "order_id")
  private List<PackageEntity> packages;

  @Column(name = "shippingAddress")
  private String shippingAddress;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusEnum status;

  @CreatedDate
  @Column(name = "createdAt")
  private LocalDateTime createdAt;

}
