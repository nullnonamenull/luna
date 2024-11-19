package com.noname.openaidto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIVectorResponseDTO {
    private List<Float> vector; // Wektor reprezentujący dane wejściowe
    private String model;       // Nazwa modelu użytego do generowania wektora
    private String input;       // Treść wejściowa, dla której wygenerowano wektor (opcjonalne, ale przydatne)
}
