package com.example.coffee_log.controller;

import com.example.coffee_log.dto.BrewRequest;
import com.example.coffee_log.model.Brew;
import com.example.coffee_log.service.BrewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brews")
@Tag(name = "brews", description = "Log and inspect coffee brews")
public class BrewController {

    private final BrewService service;

    public BrewController(BrewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Log a new brew")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    public ResponseEntity<?> addBrew(@RequestBody(required = false) BrewRequest request) {
        String capsuleName = request == null ? null : request.capsuleName();

        if (capsuleName == null || capsuleName.isBlank()) {
            return badRequest("capsuleName is required");
        }

        Brew brew = service.logBrew(capsuleName, request.size(), request.intensity());
        return ResponseEntity.status(HttpStatus.CREATED).body(brew);
    }

    @GetMapping
    @Operation(summary = "List all logged brews")
    public ResponseEntity<List<Brew>> getAllBrews() {
        return ResponseEntity.ok(service.getAllBrews());
    }

    @GetMapping("/stats")
    @Operation(summary = "Get brew statistics: total count, most-used capsule, average intensity")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    private ResponseEntity<Map<String, String>> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", message));
    }
}
