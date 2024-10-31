package com.server.JavaServer.repository;

import com.server.JavaServer.model.CpuCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuCoolerRepository extends JpaRepository<CpuCooler, Long> {

}
