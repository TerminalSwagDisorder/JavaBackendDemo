package com.server.JavaServer.repository;

import com.server.JavaServer.model.Chassis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChassisRepository extends JpaRepository<Chassis, Long> {

}
