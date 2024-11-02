package com.server.JavaServer.service;

import com.server.JavaServer.model.Motherboard;
import com.server.JavaServer.mapper.MotherboardMapper;
import com.server.JavaServer.repository.MotherboardRepository;
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
public class MotherboardService {

    @Autowired
    private MotherboardRepository motherboardRepository;

    @Autowired
    private MotherboardMapper motherboardMapper;

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

    public Motherboard insert(Motherboard motherboard) {
        if (motherboard.getId() != null) {
            throw new IllegalArgumentException("ID must be null for insert operation");
        }
        return motherboardRepository.save(motherboard);
    }

    public Motherboard update(Long id, Motherboard motherboard) {
        Optional<Motherboard> existingMotherboard = motherboardRepository.findById(id);
        if (existingMotherboard.isPresent()) {
            Motherboard updatedMotherboard = existingMotherboard.get();
            motherboardMapper.updateMotherboardFromDto(motherboard, updatedMotherboard);
            return motherboardRepository.save(updatedMotherboard);
        } else {
            throw new EntityNotFoundException("Motherboard with id " + id + " not found");
        }
    }

    public Motherboard saveOrUpdate(Motherboard motherboard) {
        return motherboardRepository.save(motherboard);
    }

    public void delete(Long id) {
        motherboardRepository.deleteById(id);
    }
}
