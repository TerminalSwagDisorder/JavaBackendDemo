package com.server.JavaServer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.server.JavaServer.model.Memory;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemoryMapper {
    void updateMemoryFromDto(Memory source, @MappingTarget Memory target);
}