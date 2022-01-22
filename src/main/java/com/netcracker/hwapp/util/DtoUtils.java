package com.netcracker.hwapp.util;

import com.netcracker.hwapp.dto.DTOEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class DtoUtils {

    public DTOEntity convertToDto(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

//    public Object convertToEntity(Object obj, DTOEntity mapper) {
//        return new ModelMapper().map(mapper, obj.getClass());
//    }
}
