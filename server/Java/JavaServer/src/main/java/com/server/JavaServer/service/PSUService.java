package com.server.JavaServer.service;

import com.server.JavaServer.model.PSU;
import com.server.JavaServer.mapper.PSUMapper;
import com.server.JavaServer.repository.PSURepository;
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
public class PSUService {

    @Autowired
    private PSURepository psuRepository;

    @Autowired
    private PSUMapper psuMapper;

    public Page<PSU> findAll(PageRequest pageRequest) {
        return psuRepository.findAll(pageRequest);
    }

    public Page<PSU> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<PSU> psuOptional = psuRepository.findById(id);
        if (psuOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(psuOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }

    public PSU insert(PSU psu) {
        if (psu.getId() != null) {
            throw new IllegalArgumentException("ID must be null for insert operation");
        }
        return psuRepository.save(psu);
    }

    public PSU update(Long id, PSU psu) {
        Optional<PSU> existingPsu = psuRepository.findById(id);
        if (existingPsu.isPresent()) {
            PSU updatedPsu = existingPsu.get();
            psuMapper.updatePSUFromDto(psu, updatedPsu);
            return psuRepository.save(updatedPsu);
        } else {
            throw new EntityNotFoundException("PSU with id " + id + " not found");
        }
    }

    public PSU saveOrUpdate(PSU psu) {
        return psuRepository.save(psu);
    }

    public void delete(Long id) {
        psuRepository.deleteById(id);
    }
}
