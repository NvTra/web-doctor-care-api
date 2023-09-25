package com.tranv.webdoctorcareapi.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequest {
    private String email;
}
