package com.example.socks_warehouse.mapper;


import com.example.socks_warehouse.dto.SocksDto;
import com.example.socks_warehouse.entity.Socks;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SocksMapper {

    @Mapping(target = "color", source = "color")
    @Mapping(target = "cottonPart", source = "cottonPart")
    SocksDto toDto(Socks socks);


    @Mapping(target = "color", source = "color")
    @Mapping(target = "cottonPart", source = "cottonPart")
    Socks toEntity(SocksDto socksDto);

}
