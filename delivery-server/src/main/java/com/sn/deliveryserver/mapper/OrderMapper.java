package com.sn.deliveryserver.mapper;

import com.sn.deliveryserver.model.CreateOrderDTO;
import com.sn.deliveryserver.model.DepotDTO;
import com.sn.deliveryserver.model.ModifyOrderDTO;
import com.sn.deliveryserver.model.OrderDTO;
import com.sn.deliveryserver.model.PackageDTO;
import com.sn.deliveryserver.model.StatusEnum;
import com.sn.deliveryserver.persistence.DepotEntity;
import com.sn.deliveryserver.persistence.OrderEntity;
import com.sn.deliveryserver.persistence.PackageEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    imports = {LocalDateTime.class, StatusEnum.class})
public interface OrderMapper {

  OrderDTO mapOrderFromEntity(OrderEntity orderEntity);

  @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
  @Mapping(target = "status", expression = "java(StatusEnum.TO_DELIVER)")
  OrderEntity mapOrderEntityFromCreateOrder(CreateOrderDTO createOrderDTO);

  List<OrderDTO> mapOrderFromEntityList(List<OrderEntity> orderEntityList);

  List<PackageDTO> mapPackageFromEntityList(List<PackageEntity> packageEntityList);

  OrderEntity updateEntity(@MappingTarget OrderEntity entity, ModifyOrderDTO modifyOrderDTO);

  DepotEntity mapDepoEntitytFromDTO(DepotDTO depotDTO);

  DepotDTO mapDepotDTOFromEntity(DepotEntity depotEntity);

}