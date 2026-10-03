package com.study;
import dev.langchain4j.service.SystemMessage;
public interface StudyAssistant {
    @SystemMessage("Tu ek friendly study agent aahes. Marathi + English madhe simple explain kar.")
    String ask(String question);
}