package com.noname.lnadocumentchunkingdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChunkingRequestDTO {
    private String type;
    private String content;
    private MultipartFile file;
}

