package br.com.lott.reminderapp.service;

import br.com.lott.reminderapp.model.Reminder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public void removeReminder(Long id) {
        Reminder reminderToRemove = null;
        for (Reminder reminder : reminders) {
            if (reminder.getId() == id) {
                reminderToRemove = reminder;
                break;
            }
        }

        if (reminderToRemove != null) {
            reminders.remove(reminderToRemove);
        }
    }
}
