package com.example.coffee_log.service;

import com.example.coffee_log.model.Brew;
import com.example.coffee_log.repository.BrewRepository;
import com.example.coffee_log.repository.BrewRepository.CapsuleCount;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
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
        List<CapsuleCount> counts = repository.countByCapsule();

        Map<String, Long> brewsByCapsule = new LinkedHashMap<>();
        counts.forEach(c -> brewsByCapsule.put(c.getCapsuleName(), c.getTotal()));

        String mostUsedCapsule = counts.stream()
                .max(Comparator.comparingLong(CapsuleCount::getTotal))
                .map(CapsuleCount::getCapsuleName)
                .orElse(null);

        Double average = repository.averageIntensity();

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalBrews", repository.count());
        stats.put("mostUsedCapsule", mostUsedCapsule);
        stats.put("averageIntensity", average == null ? 0.0 : average);
        stats.put("brewsByCapsule", brewsByCapsule);
        return stats;
    }
}
