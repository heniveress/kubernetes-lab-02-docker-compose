package com.example.coffee_log.model;

import java.time.LocalDateTime;

public record Brew(Long id, String capsuleName, String size, Integer intensity, LocalDateTime timestamp) {
}
