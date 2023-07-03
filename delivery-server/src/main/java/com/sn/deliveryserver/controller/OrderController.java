package com.sn.deliveryserver.controller;

import com.sn.deliveryserver.exceptions.UnmodifiableException;
import com.sn.deliveryserver.model.CreateOrderDTO;
import com.sn.deliveryserver.model.ModifyOrderDTO;
import com.sn.deliveryserver.model.OrderDTO;
import com.sn.deliveryserver.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {
  @Autowired
  OrderService orderService;


  @Operation(summary = "Place a delivery order")
  @CrossOrigin(origins = {"http://localhost:8090", "http://localhost:3000"}, maxAge = 1800)
  @PostMapping("/sendOrder")
  public String sendOrder(@RequestBody CreateOrderDTO createOrderDTO) {
    orderService.saveOrder(createOrderDTO);
    return "Ordine preso in carico correttamente";
  }


  @Operation(summary = "Modify an existing order")
  @CrossOrigin(origins = {"http://localhost:8090", "http://localhost:3000"}, maxAge = 1800)
  @PutMapping("/modifyOrder/{orderId}")
  public ResponseEntity<OrderDTO> modifyOrder(@PathVariable int orderId, @RequestBody @Validated ModifyOrderDTO modifyOrderDTO)
      throws UnmodifiableException {
    return ResponseEntity.ok(orderService.modifyOrder(orderId, modifyOrderDTO));
  }


  @Operation(summary = "Start delivery path")
  @CrossOrigin(origins = {"http://localhost:8090", "http://localhost:3000"}, maxAge = 1800)
  @PostMapping("/deliveryPlanning")
  public ResponseEntity<String> deliveryPlanning(@RequestParam("depotId") int depotId) {
    return ResponseEntity.ok(orderService.deliveryPlanning(depotId));
  }

  @Operation(summary = "Get list of all orders placed")
  @CrossOrigin(origins = {"http://localhost:8090", "http://localhost:3000"}, maxAge = 1800)
  @GetMapping("/order")
  public ResponseEntity<List<OrderDTO>> retrieveOrders() {
    return ResponseEntity.ok(orderService.getOrderList());
  }

}
