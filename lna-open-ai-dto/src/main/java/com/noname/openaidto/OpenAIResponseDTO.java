package com.noname.lnaopenaidto;

import lombok.Data;

@Data
public class OpenAIResponseDTO {
    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private Choice[] choices;

    @Data
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
    }

    @Data
    public static class Choice {
        private Message message;

        @Data
        public static class Message {
            private String role;
            private String content;
        }
    }
}

