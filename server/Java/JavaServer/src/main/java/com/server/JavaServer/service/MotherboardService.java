package com.server.JavaServer.service;

import com.server.JavaServer.model.Motherboard;
import com.server.JavaServer.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class MotherboardService {

    @Autowired
    private MotherboardRepository motherboardRepository;

    public Page<Motherboard> findAll(PageRequest pageRequest) {
        return motherboardRepository.findAll(pageRequest);
    }

    public Page<Motherboard> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<Motherboard> motherboardOptional = motherboardRepository.findById(id);
        if (motherboardOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(motherboardOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }

    public Motherboard saveOrUpdate(Motherboard motherboard) {
        return motherboardRepository.save(motherboard);
    }

    public void delete(Long id) {
        motherboardRepository.deleteById(id);
    }
}
