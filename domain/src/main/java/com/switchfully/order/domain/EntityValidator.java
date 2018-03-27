package com.switchfully.order.domain;


public abstract class EntityValidator<T extends Entity> {

    public boolean isValidForCreation(T entity) {
        return !isAFieldEmptyOrNull(entity) && entity.getId() == null;
    }

    public boolean isValidForUpdating(T entity) {
        return !isAFieldEmptyOrNull(entity) && entity.getId() != null;
    }

    protected abstract boolean isAFieldEmptyOrNull(T entity);

    protected boolean isEmptyOrNull(String attribute) {
        return attribute == null || attribute.isEmpty();
    }
}
