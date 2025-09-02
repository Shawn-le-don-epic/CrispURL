package com.shawn.crispurl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Tells JPA that this class is an entity to be stored in the database
@Getter   // Lombok annotation to generate getter methods automatically
@Setter   // Lombok annotation to generate setter methods automatically
@NoArgsConstructor // Lombok annotation for a no-argument constructor
@AllArgsConstructor // Lombok annotation for a constructor with all arguments
public class UrlMapping {

    @Id // Marks this field as the primary key
    private String shortCode;

    @Column(nullable = false, length = 2048) // Ensures the column is not null and sets a max length
    private String longUrl;
}
