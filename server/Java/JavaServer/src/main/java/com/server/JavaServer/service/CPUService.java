package com.server.JavaServer.service;

import com.server.JavaServer.model.CPU;
import com.server.JavaServer.repository.CPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CPUService {

    @Autowired
    private CPURepository cpuRepository;

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

    public CPU saveOrUpdate(CPU cpu) {
        return cpuRepository.save(cpu);
    }

    public void delete(Long id) {
        cpuRepository.deleteById(id);
    }
}
