package com.sn.deliveryserver.repository;

import com.sn.deliveryserver.persistence.DepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotRepo extends JpaRepository<DepotEntity, Integer> {

}
