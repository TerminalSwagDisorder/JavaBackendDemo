package com.server.JavaServer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.server.JavaServer.model.Chassis;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChassisMapper {
    void updateChassisFromDto(Chassis source, @MappingTarget Chassis target);
}