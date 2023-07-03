package com.sn.deliveryserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.sn.deliveryserver.exceptions.UnmodifiableException;
import com.sn.deliveryserver.mapper.OrderMapper;
import com.sn.deliveryserver.model.ModifyOrderDTO;
import com.sn.deliveryserver.model.OrderDTO;
import com.sn.deliveryserver.model.StatusEnum;
import com.sn.deliveryserver.model.SupplierDTO;
import com.sn.deliveryserver.persistence.OrderEntity;
import com.sn.deliveryserver.repository.DepotRepo;
import com.sn.deliveryserver.repository.OrderRepo;
import com.sn.deliveryserver.service.OrderService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTests {

  private OrderService orderService;

  @Mock
  private OrderRepo orderRepo;

  @Mock
  private OrderMapper orderMapper;

  @Mock
  private DepotRepo depotRepo;

  @BeforeEach
  public void init() {
    orderService = new OrderService(orderRepo, orderMapper, depotRepo);
  }

	@Test
  @DisplayName("Update order test")
  void updateOrderTest() throws UnmodifiableException {

    SupplierDTO supplier = SupplierDTO.builder()
        .name("Iperal sede decentrata")
        .build();

    ModifyOrderDTO orderNewData = ModifyOrderDTO.builder()
        .shippingAddress("new address")
        .build();

    OrderDTO totalOrder = OrderDTO.builder()
        .id(20)
        .shippingAddress("new address")
        .supplier(supplier)
        .status(StatusEnum.TO_DELIVER)
        .build();

    OrderEntity orderEntity = OrderEntity.builder()
        .id(20)
        .shippingAddress("new address")
        .status(StatusEnum.TO_DELIVER)
        .build();

    when(orderRepo.findById(anyInt())).thenReturn(Optional.of(orderEntity));
    when(orderMapper.updateEntity(any(OrderEntity.class), any(ModifyOrderDTO.class))).thenReturn(OrderEntity.builder().build());
    when(orderRepo.save(any(OrderEntity.class))).thenReturn(OrderEntity.builder().build());

    when(orderMapper.mapOrderFromEntity(any(OrderEntity.class))).thenReturn(totalOrder);

    OrderDTO modifiedOrder = orderService.modifyOrder(1, orderNewData);

    assertNotNull(modifiedOrder);
    assertEquals("new address", modifiedOrder.getShippingAddress());
	}

}
