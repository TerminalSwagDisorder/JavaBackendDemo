package com.server.JavaServer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.server.JavaServer.model.CpuCooler;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CpuCoolerMapper {
    void updateCpuCoolerFromDto(CpuCooler source, @MappingTarget CpuCooler target);
}