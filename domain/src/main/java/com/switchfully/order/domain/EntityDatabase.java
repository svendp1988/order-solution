package com.switchfully.order.domain;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Dummy In-Memory Database....
 */
public abstract class EntityDatabase<T extends Entity> {

    private Map<UUID, T> entities;

    public EntityDatabase() {
        entities = new HashMap<>();
    }

    void poplulate(T... entities) {
        this.entities = Arrays.stream(entities)
                .collect(Collectors.toMap(Entity::getId, entity -> entity));
    }

    Map<UUID, T> getAll() {
        return Collections.unmodifiableMap(entities);
    }

    void save(T entity) {
        entities.put(entity.getId(), entity);
    }

}
