package com.example.coffee_log.repository;

import com.example.coffee_log.model.Brew;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;

@Repository
public class BrewRepository {

    private final List<Brew> brews = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Brew save(Brew brew) {
        Brew saved = new Brew(idCounter.getAndIncrement(), brew.capsuleName(), brew.size(), brew.intensity(), brew.timestamp());
        brews.add(saved);
        return saved;
    }

    public List<Brew> findAll() {
        return List.copyOf(brews);
    }
}
