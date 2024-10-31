package com.server.JavaServer.repository;

import com.server.JavaServer.model.GPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPURepository extends JpaRepository<GPU, Long> {

}
