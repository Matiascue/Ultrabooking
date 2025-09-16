package com.flights.flights.services;

public interface GenericMapperService {
    <D> D map(Object source,Class<D> destinationType);
}
