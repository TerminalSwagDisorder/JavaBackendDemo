package com.server.JavaServer.service;

import com.server.JavaServer.model.UniversalComponents;
import com.server.JavaServer.repository.UniversalComponentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversalComponentsService {

    @Autowired
    private UniversalComponentsRepository repository;

    public List<UniversalComponents> findAll() {
        return repository.findAll();
    }

    public Optional<UniversalComponents> findById(Long id) {
        return repository.findById(id);
    }

    public UniversalComponents saveOrUpdate(UniversalComponents component) {
        return repository.save(component);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
