package com.server.JavaServer.service;

import com.server.JavaServer.model.CPU;
import com.server.JavaServer.mapper.CPUMapper;
import com.server.JavaServer.repository.CPURepository;
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
public class CPUService {

    @Autowired
    private CPURepository cpuRepository;
    
    @Autowired
    private CPUMapper cpuMapper;

    public Page<CPU> findAll(PageRequest pageRequest) {
        return cpuRepository.findAll(pageRequest);
    }

    public Page<CPU> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<CPU> cpuOptional = cpuRepository.findById(id);
        if (cpuOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(cpuOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }

    public CPU insert(CPU cpu) {
        if (cpu.getId() != null) {
            throw new IllegalArgumentException("ID must be null for insert operation");
        }
        return cpuRepository.save(cpu);
    }
    
    public CPU update(Long id, CPU cpu) {
        Optional<CPU> existingCpu = cpuRepository.findById(id);
        if (existingCpu.isPresent()) {
            CPU updatedCpu = existingCpu.get();
            BeanUtils.copyProperties(cpu, updatedCpu, "id");
            return cpuRepository.save(updatedCpu);
        } else {
            throw new EntityNotFoundException("CPU with id " + id + " not found");
        }
    }


    public CPU saveOrUpdate(CPU cpu) {
        return cpuRepository.save(cpu);
    }

    public void delete(Long id) {
        cpuRepository.deleteById(id);
    }
}
