package com.server.JavaServer.service;

import com.server.JavaServer.model.CpuCooler;
import com.server.JavaServer.repository.CpuCoolerRepository;
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
public class CpuCoolerService {

    @Autowired
    private CpuCoolerRepository cpucoolerRepository;

    public Page<CpuCooler> findAll(PageRequest pageRequest) {
        return cpucoolerRepository.findAll(pageRequest);
    }

    public Page<CpuCooler> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<CpuCooler> cpucoolerOptional = cpucoolerRepository.findById(id);
        if (cpucoolerOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(cpucoolerOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }

    public CpuCooler insert(CpuCooler cpucooler) {
        if (cpucooler.getId() != null) {
            throw new IllegalArgumentException("ID must be null for insert operation");
        }
        return cpucoolerRepository.save(cpucooler);
    }

    public CpuCooler update(Long id, CpuCooler cpucooler) {
        Optional<CpuCooler> existingCpu = cpucoolerRepository.findById(id);
        if (existingCpu.isPresent()) {
            CpuCooler updatedCpu = existingCpu.get();
            BeanUtils.copyProperties(cpucooler, updatedCpu, "id");
            return cpucoolerRepository.save(updatedCpu);
        } else {
            throw new EntityNotFoundException("CpuCooler with id " + id + " not found");
        }
    }

    public CpuCooler saveOrUpdate(CpuCooler cpucooler) {
        return cpucoolerRepository.save(cpucooler);
    }

    public void delete(Long id) {
        cpucoolerRepository.deleteById(id);
    }
}
