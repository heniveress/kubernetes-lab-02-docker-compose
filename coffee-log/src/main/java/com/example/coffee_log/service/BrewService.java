package com.example.coffee_log.service;

import com.example.coffee_log.model.Brew;
import com.example.coffee_log.repository.BrewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BrewService {

    private final BrewRepository repository;

    public BrewService(BrewRepository repository) {
        this.repository = repository;
    }

    public Brew logBrew(String capsuleName, String size, Integer intensity) {
        Brew brew = new Brew(null, capsuleName, size, intensity, LocalDateTime.now());
        return repository.save(brew);
    }

    public List<Brew> getAllBrews() {
        return repository.findAll();
    }

    public Map<String, Object> getStats() {
        List<Brew> brews = repository.findAll();

        Map<String, Long> countByCapsule = brews.stream()
                .collect(Collectors.groupingBy(Brew::capsuleName, Collectors.counting()));

        String mostUsedCapsule = countByCapsule.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        double averageIntensity = brews.stream()
                .filter(brew -> brew.intensity() != null)
                .mapToInt(Brew::intensity)
                .average()
                .orElse(0.0);

        return Map.of(
                "totalBrews", brews.size(),
                "mostUsedCapsule", mostUsedCapsule,
                "averageIntensity", averageIntensity,
                "brewsByCapsule", countByCapsule
        );
    }
}
