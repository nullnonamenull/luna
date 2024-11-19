package com.noname.lnadocumentchunkingdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChunkingResponseDTO {

    private String documentId;
    private List<Chunk> chunks;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Chunk {
        private int chunkId;
        private String content;
        private Metadata metadata;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private Integer page;
        private String section;
        private String type;
    }
}
