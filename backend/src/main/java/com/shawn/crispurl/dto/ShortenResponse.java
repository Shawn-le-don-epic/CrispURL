package com.shawn.crispurl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// A DTO for the outgoing response body
@Getter
@Setter
@AllArgsConstructor
public class ShortenResponse {
    private String shortUrl;
}
