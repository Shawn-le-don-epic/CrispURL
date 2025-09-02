package com.shawn.crispurl.repository;

import com.shawn.crispurl.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {

    // Spring Data JPA automatically implements this method for us
    // based on its name. It will execute a query like:
    // "SELECT * FROM url_mapping WHERE short_code = ?"
    Optional<UrlMapping> findByShortCode(String shortCode);
}

