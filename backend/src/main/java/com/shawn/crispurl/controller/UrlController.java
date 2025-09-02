package com.shawn.crispurl.controller;

import com.shawn.crispurl.dto.ShortenRequest;
import com.shawn.crispurl.dto.ShortenResponse;
import com.shawn.crispurl.model.UrlMapping;
import com.shawn.crispurl.repository.UrlMappingRepository;
import com.shawn.crispurl.service.UrlShorteningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class UrlController {

    private final UrlShorteningService urlShorteningService;
    private final UrlMappingRepository urlMappingRepository;

    public UrlController(UrlShorteningService urlShorteningService, UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
        this.urlShorteningService = urlShorteningService;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<ShortenResponse> shortenUrl(@RequestBody ShortenRequest request) {
        String longUrl = request.getLongUrl();
        if (longUrl == null || longUrl.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // 1. Call the public method from the service to get a unique code
        String shortCode = urlShorteningService.generateUniqueShortCode();

        // 2. Create the entity and save it using the repository
        UrlMapping urlMapping = new UrlMapping(longUrl, shortCode);
        urlMappingRepository.save(urlMapping);

        // 3. Build the response URL correctly
        String shortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{shortCode}")
                .buildAndExpand(shortCode)
                .toUriString();

        return ResponseEntity.ok(new ShortenResponse(shortUrl));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        // Use the repository's "magic" method to find the long URL
        Optional<UrlMapping> urlMapping = urlMappingRepository.findByShortCode(shortCode);

        if (urlMapping.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(urlMapping.get().getLongUrl()))
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

