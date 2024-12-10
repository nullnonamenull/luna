package com.noname.lnafrontendgateway.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    // Obsługa wiadomości wysyłanych przez klienta
    @MessageMapping("/session/{sessionId}/send")
    @SendTo("/topic/session/{sessionId}") // Odpowiedź do subskrybentów
    public ChatMessage handleMessage(ChatMessage message) {
        System.out.println("Received message: " + message.getContent());
        // Zwracamy odpowiedź "Test test"
        return new ChatMessage("LUNA", "Test test");
    }

    // Klasa reprezentująca wiadomość
    public static class ChatMessage {
        private String sender;
        private String content;

        public ChatMessage() {
        }

        public ChatMessage(String sender, String content) {
            this.sender = sender;
            this.content = content;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}


