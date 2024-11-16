package com.noname.openaidto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIRequestDTO {
    private String model;
    private String prompt;
    private String role;
}