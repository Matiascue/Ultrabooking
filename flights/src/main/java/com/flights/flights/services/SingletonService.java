package com.flights.flights.services;

public interface SingletonService {
    <T> T getInstance(Class<T> tClass);
}
