package com.shawn.crispurl.service;

import com.shawn.crispurl.model.UrlMapping;
import com.shawn.crispurl.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service // Marks this as a Spring service component
public class UrlShorteningService {

    @Autowired // Spring will automatically inject an instance of our repository
    private UrlMappingRepository urlMappingRepository;

    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public UrlMapping shortenUrl(String longUrl) {
        String shortCode;
        // Keep generating a new short code until we find one that isn't already in the database
        do {
            shortCode = generateShortCode();
        } while (urlMappingRepository.findById(shortCode).isPresent());

        UrlMapping urlMapping = new UrlMapping(shortCode, longUrl);
        return urlMappingRepository.save(urlMapping);
    }

    public Optional<UrlMapping> getLongUrl(String shortCode) {
        return urlMappingRepository.findById(shortCode);
    }

    private String generateShortCode() {
        // Generates a random, URL-safe 6-character string
        byte[] buffer = new byte[4]; // 4 bytes will produce ~5.3 characters, we'll trim
        random.nextBytes(buffer);
        return encoder.encodeToString(buffer).substring(0, 6);
    }
}
