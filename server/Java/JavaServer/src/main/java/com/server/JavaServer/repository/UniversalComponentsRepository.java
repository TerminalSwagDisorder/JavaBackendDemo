package com.server.JavaServer.repository;

import com.server.JavaServer.model.UniversalComponents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversalComponentsRepository extends JpaRepository<UniversalComponents, Long> {

}
