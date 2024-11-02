package com.server.JavaServer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.server.JavaServer.model.PSU;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PSUMapper {
    void updatePSUFromDto(PSU source, @MappingTarget PSU target);
}