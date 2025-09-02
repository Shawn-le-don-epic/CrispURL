package com.shawn.crispurl.repository;

import com.shawn.crispurl.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a Spring Data repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {
    // Spring Data JPA will automatically provide methods like save(), findById(), delete(), etc.
    // We don't need to write anything else here!
}
