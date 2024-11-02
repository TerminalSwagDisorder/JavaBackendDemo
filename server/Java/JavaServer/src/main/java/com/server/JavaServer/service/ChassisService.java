package com.server.JavaServer.service;

import com.server.JavaServer.model.Chassis;
import com.server.JavaServer.repository.ChassisRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collections;
import java.util.Optional;

@Service
public class ChassisService {

    @Autowired
    private ChassisRepository chassisRepository;

    public Page<Chassis> findAll(PageRequest pageRequest) {
        return chassisRepository.findAll(pageRequest);
    }

    public Page<Chassis> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<Chassis> chassisOptional = chassisRepository.findById(id);
        if (chassisOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(chassisOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }

    public Chassis insert(Chassis chassis) {
        if (chassis.getId() != null) {
            throw new IllegalArgumentException("ID must be null for insert operation");
        }
        return chassisRepository.save(chassis);
    }

    public Chassis update(Long id, Chassis chassis) {
        Optional<Chassis> existingCpu = chassisRepository.findById(id);
        if (existingCpu.isPresent()) {
            Chassis updatedCpu = existingCpu.get();
            BeanUtils.copyProperties(chassis, updatedCpu, "id");
            return chassisRepository.save(updatedCpu);
        } else {
            throw new EntityNotFoundException("Chassis with id " + id + " not found");
        }
    }

    public Chassis saveOrUpdate(Chassis chassis) {
        return chassisRepository.save(chassis);
    }

    public void delete(Long id) {
        chassisRepository.deleteById(id);
    }
}
