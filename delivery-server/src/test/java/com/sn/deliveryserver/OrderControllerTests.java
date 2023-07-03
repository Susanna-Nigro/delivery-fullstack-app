package com.sn.deliveryserver;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sn.deliveryserver.controller.OrderController;
import com.sn.deliveryserver.model.DepotDTO;
import com.sn.deliveryserver.model.OrderDTO;
import com.sn.deliveryserver.model.PackageDTO;
import com.sn.deliveryserver.model.SupplierDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(OrderController.class)
class OrderControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderController controller;

  @Test
  @DisplayName("Retrieve orders list")
  void getOrdersList() throws Exception {

    given(controller.retrieveOrders())
        .willReturn(ResponseEntity.ok(Collections.singletonList(OrderDTO.builder().build())));

    SupplierDTO supplierDTO = SupplierDTO.builder().build();
    DepotDTO depotDTO = DepotDTO.builder().build();
    PackageDTO packageDTO = PackageDTO.builder().build();
    PackageDTO packageDTO2 = PackageDTO.builder().build();
    List<PackageDTO> packagesList = new ArrayList<>();
    packagesList.add(packageDTO);
    packagesList.add(packageDTO2);


    OrderDTO order = OrderDTO.builder()
        .supplier(supplierDTO)
        .depot(depotDTO)
        .packages(packagesList)
        .build();

    String json = "";
    try {
      json = new ObjectMapper().writeValueAsString(order);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

}
