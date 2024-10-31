package com.server.JavaServer.service;

import com.server.JavaServer.model.Memory;
import com.server.JavaServer.repository.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class MemoryService {

    @Autowired
    private MemoryRepository memoryRepository;

    public Page<Memory> findAll(PageRequest pageRequest) {
        return memoryRepository.findAll(pageRequest);
    }

    public Page<Memory> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<Memory> memoryOptional = memoryRepository.findById(id);
        if (memoryOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(memoryOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }

    public Memory saveOrUpdate(Memory memory) {
        return memoryRepository.save(memory);
    }

    public void delete(Long id) {
        memoryRepository.deleteById(id);
    }
}
