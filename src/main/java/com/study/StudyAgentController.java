package com.study;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

@RestController
@CrossOrigin
public class StudyAgentController {
    private final StudyAssistant agent;
    public StudyAgentController() {
        var model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName("gemini-3.5-flash-lite").build();
     // Ha memory saglya chat sathi ekach rahil
        var memory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .id("main-chat")
                .build();
                
        this.agent = AiServices.builder(StudyAssistant.class)
                .chatLanguageModel(model)
                .chatMemory(memory)
                .build();
    }
    @PostMapping("/ask")
    public Map<String,String> ask(@RequestBody Map<String,String> body) {
        try {
            String q = body.get("question");
            System.out.println("Question aala: " + q);
            String ans = agent.ask(q);
            System.out.println("Answer: " + ans);
            return Map.of("answer", ans);
        } catch (Exception e) {
            e.printStackTrace(); // Ha error Console madhe dakhvel
            return Map.of("answer", "Error: " + e.getMessage());
        }
    }
}