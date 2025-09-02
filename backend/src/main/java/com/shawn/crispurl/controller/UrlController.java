package com.shawn.crispurl.controller;

import com.shawn.crispurl.dto.ShortenRequest;
import com.shawn.crispurl.dto.ShortenResponse;
import com.shawn.crispurl.model.UrlMapping;
import com.shawn.crispurl.service.UrlShorteningService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*") // Allows requests from any origin (e.g., our React frontend)
public class UrlController {

    @Autowired
    private UrlShorteningService urlShorteningService;

    // Endpoint for creating a short URL
    @PostMapping("/api/shorten")
    public ResponseEntity<ShortenResponse> shortenUrl(@RequestBody ShortenRequest request) {
        UrlMapping urlMapping = urlShorteningService.shortenUrl(request.getLongUrl());
        // We'll assume a base URL for our service. This would be your domain in production.
        String shortUrl = "http://localhost:8081/" + urlMapping.getShortCode();
        return ResponseEntity.ok(new ShortenResponse(shortUrl));
    }

    // Endpoint for redirecting from a short URL to the original URL
    @GetMapping("/{shortCode}")
    public void redirectToLongUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        urlShorteningService.getLongUrl(shortCode).ifPresentOrElse(
            urlMapping -> {
                try {
                    response.sendRedirect(urlMapping.getLongUrl());
                } catch (IOException e) {
                    // Handle exception
                }
            },
            () -> {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
        );
    }
}
