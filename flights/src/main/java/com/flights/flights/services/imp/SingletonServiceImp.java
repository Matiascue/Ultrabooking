package com.flights.flights.services.imp;

import com.flights.flights.services.SingletonService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SingletonServiceImp implements SingletonService {
    private final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> tClass) {
        return (T) instances.computeIfAbsent(tClass, clazz -> {
            try {
                Constructor<T> constructor = tClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor.newInstance();
            } catch (NoSuchMethodException | InstantiationException |
                     IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Error creating instance of " + tClass.getName(), e);
            }
        });
    }
}
