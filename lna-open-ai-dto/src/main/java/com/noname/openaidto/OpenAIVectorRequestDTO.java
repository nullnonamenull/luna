package com.noname.openaidto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIVectorRequestDTO {
    private String model;
    private String input;
}
