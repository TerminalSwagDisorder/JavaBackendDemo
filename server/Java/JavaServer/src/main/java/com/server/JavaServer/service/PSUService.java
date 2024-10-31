package com.server.JavaServer.service;

import com.server.JavaServer.model.PSU;
import com.server.JavaServer.repository.PSURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class PSUService {

    @Autowired
    private PSURepository psuRepository;

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

    public PSU saveOrUpdate(PSU psu) {
        return psuRepository.save(psu);
    }

    public void delete(Long id) {
        psuRepository.deleteById(id);
    }
}
