package br.com.lott.reminderapp.service;

import br.com.lott.reminderapp.model.Reminder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReminderService {

    private List<Reminder> reminders;

    public ReminderService() {
        this.reminders = new ArrayList<>();
        reminders.add(new Reminder(LocalDate.now(), LocalTime.now(), "Fazer compras no supermercado"));
        reminders.add(new Reminder(LocalDate.now(), LocalTime.now(), "Pagar boleto"));
    }

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminders;
    }

    @PostMapping
    public void addReminder(Reminder reminder) throws IllegalArgumentException {
        if (reminder.getMessage() == null || reminder.getMessage().isEmpty())
            throw new IllegalArgumentException("Erro: A mensagem deve possuir conteúdo.");

        reminders.add(reminder);
    }

    @PostMapping
    public void removeReminder(Reminder reminder) throws NoSuchElementException {
        if (!reminders.contains(reminder))
            throw new NoSuchElementException("Erro: Não foi possível encontrar o lembrete.");

        reminders.remove(reminder);
    }
}
