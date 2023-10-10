package br.com.lott.reminderapp.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Reminder {
    private final int id;
    private static int nextId = 0;
    private LocalDate date;
    private LocalTime time;
    private String message;

    public Reminder(LocalDate date, LocalTime time, String message) {
        this.id = nextId++;
        this.date = date;
        this.time = time;
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "\nData: " + date +
                "\nHorário: " + time +
                "\nMensagem: '" + message + '\'';
    }

}
