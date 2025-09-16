package com.usuarios.usuarios.builder;

import java.util.function.Consumer;

public class GenericBuilder <T>{
    private T instance;
    private Consumer<T> setter;

    // Constructor para aceptar la clase que estamos construyendo y el setter
    public GenericBuilder(Class<T> clazz) {
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating builder instance", e);
        }
    }

    // Método para establecer los valores en la clase
    public GenericBuilder<T> with(Consumer<T> setter) {
        setter.accept(instance);
        return this;
    }

    // Método para construir el objeto
    public T build() {
        return instance;
    }
}
