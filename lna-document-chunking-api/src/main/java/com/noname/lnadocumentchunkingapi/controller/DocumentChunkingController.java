package com.noname.lnadocumentchunkingapi.controller;

import com.noname.lnadocumentchunkingapi.service.DocumentChunkingService;
import com.noname.lnadocumentchunkingdto.ChunkingRequestDTO;
import com.noname.lnadocumentchunkingdto.ChunkingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/chunking")
@RequiredArgsConstructor
public class DocumentChunkingController {

    private final DocumentChunkingService chunkingService;

    @PostMapping(value = "/chunk", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ChunkingResponseDTO> chunkDocument(@ModelAttribute ChunkingRequestDTO requestDTO) {
        ChunkingResponseDTO response = chunkingService.chunkDocument(requestDTO);
        return ResponseEntity.ok(response);
    }
}
