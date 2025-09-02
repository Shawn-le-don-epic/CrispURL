package com.shawn.crispurl.dto;

import lombok.Getter;
import lombok.Setter;

// A Data Transfer Object (DTO) for the incoming request body
@Getter
@Setter
public class ShortenRequest {
    private String longUrl;
}
