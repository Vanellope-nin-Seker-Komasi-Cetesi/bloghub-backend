package com.patika.bloghubservice.dto.request;

import com.patika.bloghubservice.validation.UniqueEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSaveRequest {

    @NotBlank
    private String name;
    @NotBlank(message = "{user.email.cannot.be.empty}")
    @UniqueEmail
    private String email;
    @NotBlank
    private String password;

}
