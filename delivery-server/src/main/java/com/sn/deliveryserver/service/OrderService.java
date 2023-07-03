package com.sn.deliveryserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sn.deliveryserver.exceptions.UnmodifiableException;
import com.sn.deliveryserver.mapper.OrderMapper;
import com.sn.deliveryserver.model.CreateOrderDTO;
import com.sn.deliveryserver.model.ModifyOrderDTO;
import com.sn.deliveryserver.model.OrderDTO;
import com.sn.deliveryserver.model.StatusEnum;
import com.sn.deliveryserver.persistence.DepotEntity;
import com.sn.deliveryserver.persistence.OrderEntity;
import com.sn.deliveryserver.repository.DepotRepo;
import com.sn.deliveryserver.repository.OrderRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

  private static final Logger logger = LogManager.getLogger(OrderService.class);
  private final OrderRepo orderRepo;
  private final DepotRepo depotRepo;
  private final OrderMapper orderMapper;

  public OrderService(OrderRepo orderRepo, OrderMapper orderMapper, DepotRepo depotRepo) {
    this.orderRepo = orderRepo;
    this.orderMapper = orderMapper;
    this.depotRepo = depotRepo;
  }

  public void saveOrder(CreateOrderDTO createOrderDTO) {
    orderRepo.save(orderMapper.mapOrderEntityFromCreateOrder(createOrderDTO));
  }

  public OrderDTO modifyOrder(int orderId, ModifyOrderDTO modifyOrderDTO) throws UnmodifiableException {
    OrderEntity orderEntity = orderRepo.findById(orderId).orElseThrow();

    if(!orderEntity.getStatus().equals(StatusEnum.TO_DELIVER)) {
      logger.error("Errore: Ordine non modificabile");
      throw new UnmodifiableException("Ordine non modificabile, consegna già avvenuta o in transito");
    }

    return orderMapper.mapOrderFromEntity(
              orderRepo.save(
                orderMapper.updateEntity(orderEntity, modifyOrderDTO)));
  }

  public List<OrderDTO> getOrderList() {
    return orderMapper.mapOrderFromEntityList(orderRepo.findAll());
  }

  public String deliveryPlanning(int depotId) {

    logger.info("Iniziato piano di delivery");

    String response = "";
    List<String> path = new ArrayList<>();

    DepotEntity depotEntity = depotRepo.findById(depotId).orElseThrow();
    path.add(depotEntity.getAddress());

    orderRepo.findAll().stream()
        .filter(o -> o.getDepot().getId() == depotId && o.getStatus().equals(StatusEnum.TO_DELIVER))
        .collect(Collectors.toList())
        .forEach(order -> path.add(order.getShippingAddress()));

    path.add(depotEntity.getAddress());

    try {
       response = new ObjectMapper().writeValueAsString(path);
    } catch (JsonProcessingException e) {
      logger.error("Errore durante deserializzazione");
      e.printStackTrace();
    }

    return response;
  }

}
