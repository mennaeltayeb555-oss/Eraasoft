package org.example.businesslogic.common.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Simple repository that saves objects in a map instead of a database
public abstract class InMemoryRepository<T extends BaseEntity> {

    private final Map<Long, T> storage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(idCounter.getAndIncrement());
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<T> findAll() {
        return storage.values().stream()
                .sorted(Comparator.comparing(BaseEntity::getId))
                .toList();
    }
}
