package com.flights.flights.services.imp;

import com.flights.flights.services.GenericMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericMapperServiceImp implements GenericMapperService {
   @Autowired
   private ModelMapper modelMapper;

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source,destinationType);
    }
}
