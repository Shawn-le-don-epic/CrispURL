package com.shawn.crispurl.service;

import com.shawn.crispurl.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UrlShorteningService {

    private final UrlMappingRepository urlMappingRepository;
    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    // Use constructor injection - this is a best practice
    public UrlShorteningService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    /**
     * Generates a random 6-character string that is guaranteed to be unique
     * in the database.
     * @return A unique short code.
     */
    public String generateUniqueShortCode() {
        String shortCode;
        // Keep generating a new code until we find one that isn't already used
        do {
            byte[] buffer = new byte[4];
            random.nextBytes(buffer);
            shortCode = encoder.encodeToString(buffer).substring(0, 6);
        } while (urlMappingRepository.findByShortCode(shortCode).isPresent());
        
        return shortCode;
    }
}

