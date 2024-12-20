package com.server.JavaServer.service;

import com.server.JavaServer.model.Storage;
import com.server.JavaServer.mapper.StorageMapper;
import com.server.JavaServer.repository.StorageRepository;
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
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private StorageMapper storageMapper;

    public Page<Storage> findAll(PageRequest pageRequest) {
        return storageRepository.findAll(pageRequest);
    }

    public Page<Storage> findByIdAsPage(Long id, PageRequest pageRequest) {
        Optional<Storage> storageOptional = storageRepository.findById(id);
        if (storageOptional.isPresent()) {
            return new PageImpl<>(Collections.singletonList(storageOptional.get()), pageRequest, 1);
        } else {
            return Page.empty(pageRequest);
        }
    }
    
    public Storage insert(Storage storage) {
        if (storage.getId() != null) {
            throw new IllegalArgumentException("ID must be null for insert operation");
        }
        return storageRepository.save(storage);
    }

    public Storage update(Long id, Storage storage) {
        Optional<Storage> existingStorage = storageRepository.findById(id);
        if (existingStorage.isPresent()) {
            Storage updatedStorage = existingStorage.get();
            storageMapper.updateStorageFromDto(storage, updatedStorage);
            return storageRepository.save(updatedStorage);
        } else {
            throw new EntityNotFoundException("Storage with id " + id + " not found");
        }
    }

    public Storage saveOrUpdate(Storage storage) {
        return storageRepository.save(storage);
    }

    public void delete(Long id) {
        storageRepository.deleteById(id);
    }
}
