package com.patika.bloghubservice.dto.request;

import jakarta.validation.constraints.Size;

public record PasswordUpdate(
        @Size(min = 6, max = 12)
        String password) {

}