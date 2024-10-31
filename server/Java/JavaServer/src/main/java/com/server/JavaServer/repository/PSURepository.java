package com.server.JavaServer.repository;

import com.server.JavaServer.model.PSU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PSURepository extends JpaRepository<PSU, Long> {

}
