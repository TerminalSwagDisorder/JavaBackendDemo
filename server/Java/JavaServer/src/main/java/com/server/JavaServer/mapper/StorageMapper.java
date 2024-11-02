package com.server.JavaServer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.server.JavaServer.model.Storage;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StorageMapper {
    void updateStorageFromDto(Storage source, @MappingTarget Storage target);
}