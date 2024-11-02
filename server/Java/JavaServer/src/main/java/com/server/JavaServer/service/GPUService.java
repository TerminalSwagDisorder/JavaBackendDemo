package com.server.JavaServer.service;

import com.server.JavaServer.model.GPU;
import com.server.JavaServer.repository.GPURepository;
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
public class GPUService {

    @Autowired
    private GPURepository gpuRepository;

    public Page<GPU> findAll(PageRequest pageRequest) {
        return gpuRepository.findAll(pageRequest);
    }

    public Page<GPU> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<GPU> gpuOptional = gpuRepository.findById(id);
        if (gpuOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(gpuOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }

    public GPU insert(GPU gpu) {
        if (gpu.getId() != null) {
            throw new IllegalArgumentException("ID must be null for insert operation");
        }
        return gpuRepository.save(gpu);
    }

    public GPU update(Long id, GPU gpu) {
        Optional<GPU> existingCpu = gpuRepository.findById(id);
        if (existingCpu.isPresent()) {
            GPU updatedCpu = existingCpu.get();
            BeanUtils.copyProperties(gpu, updatedCpu, "id");
            return gpuRepository.save(updatedCpu);
        } else {
            throw new EntityNotFoundException("GPU with id " + id + " not found");
        }
    }

    public GPU saveOrUpdate(GPU gpu) {
        return gpuRepository.save(gpu);
    }

    public void delete(Long id) {
        gpuRepository.deleteById(id);
    }
}
