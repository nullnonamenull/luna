package com.noname.lnadocumentchunkingapi.service;

import com.noname.lnadocumentchunkingdto.ChunkingRequestDTO;
import com.noname.lnadocumentchunkingdto.ChunkingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentChunkingService {

    public ChunkingResponseDTO chunkDocument(ChunkingRequestDTO request) {
        List<ChunkingResponseDTO.Chunk> chunks = switch (request.getType().toLowerCase()) {
            case "pdf" -> processPdf(request.getFile());
            case "txt" -> processTxt(request.getFile());
            case "md" -> processMd(request.getFile());
            case "string" -> processString(request.getContent());
            default -> throw new IllegalArgumentException("Unsupported document type: " + request.getType());
        };

        return new ChunkingResponseDTO("doc-" + System.currentTimeMillis(), chunks);
    }


    private List<ChunkingResponseDTO.Chunk> processPdf(MultipartFile file) {
        List<ChunkingResponseDTO.Chunk> chunks = new ArrayList<>();
        chunks.add(new ChunkingResponseDTO.Chunk(1, "PDF content chunk 1", new ChunkingResponseDTO.Metadata(1, null, "paragraph")));
        chunks.add(new ChunkingResponseDTO.Chunk(2, "PDF content chunk 2", new ChunkingResponseDTO.Metadata(1, null, "paragraph")));
        return chunks;
    }

    private List<ChunkingResponseDTO.Chunk> processTxt(MultipartFile file) {
        List<ChunkingResponseDTO.Chunk> chunks = new ArrayList<>();
        try {
            String content = new String(file.getBytes());
            String[] paragraphs = content.split("\\r?\\n\\r?\\n");

            for (int i = 0; i < paragraphs.length; i++) {
                chunks.add(new ChunkingResponseDTO.Chunk(
                        i + 1,
                        paragraphs[i],
                        new ChunkingResponseDTO.Metadata(null, null, "paragraph")
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading TXT file: " + e.getMessage(), e);
        }
        return chunks;
    }

    private List<ChunkingResponseDTO.Chunk> processMd(MultipartFile file) {
        List<ChunkingResponseDTO.Chunk> chunks = new ArrayList<>();
        try {
            String content = new String(file.getBytes());

            String[] sections = content.split("(?=^#{1,3}\\s)");

            for (int i = 0; i < sections.length; i++) {
                String section = sections[i].trim();
                String sectionHeader = extractMarkdownHeader(section);
                String sectionContent = section.replaceFirst("^#{1,3}\\s.*", "").trim();

                chunks.add(new ChunkingResponseDTO.Chunk(
                        i + 1,
                        sectionContent,
                        new ChunkingResponseDTO.Metadata(null, sectionHeader, "section")
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Markdown file: " + e.getMessage(), e);
        }

        return chunks;
    }

    private List<ChunkingResponseDTO.Chunk> processString(String content) {
        List<ChunkingResponseDTO.Chunk> chunks = new ArrayList<>();
        String[] paragraphs = content.split("\n\n");
        for (int i = 0; i < paragraphs.length; i++) {
            chunks.add(new ChunkingResponseDTO.Chunk(i + 1, paragraphs[i], new ChunkingResponseDTO.Metadata(null, null, "paragraph")));
        }
        return chunks;
    }

    private String extractMarkdownHeader(String section) {
        String[] lines = section.split("\\r?\\n");
        for (String line : lines) {
            if (line.startsWith("#")) {
                return line.replaceFirst("^#{1,3}\\s", "").trim();
            }
        }
        return "Untitled Section";
    }

}