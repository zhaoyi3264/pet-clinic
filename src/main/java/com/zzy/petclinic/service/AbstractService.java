package com.zzy.petclinic.service;

import java.util.Collection;

public abstract class AbstractService<T, ID, R extends org.springframework.data.jpa.repository.JpaRepository<T, ID>> {

    protected R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return this.repository.save(entity);
    }

    public T findById(ID id) {
        return this.repository.findById(id).get();
    }

    public Collection<T> findAll() {
        return this.repository.findAll();
    }

    public void delete(T entity) {
        this.repository.delete(entity);
    }

    public void deleteById(ID id) {
        this.repository.deleteById(id);
    }
}
