package com.switchfully.order.domain;

import java.util.UUID;

public abstract class Repository<T extends Entity, U extends EntityDatabase<T>> {

    private U database;

    public Repository(U database) {
        this.database = database;
    }

    public T save(T entity) {
        entity.generateId();
        database.save(entity);
        return entity;
    }

    public T get(UUID entityId) {
        return database.getAll().get(entityId);
    }

}
