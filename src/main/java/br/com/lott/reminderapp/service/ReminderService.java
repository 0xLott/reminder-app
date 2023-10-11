package br.com.lott.reminderapp.service;

import br.com.lott.reminderapp.model.Reminder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ReminderService {

    private List<Reminder> reminders;

    public ReminderService() {
        this.reminders = new ArrayList<>();

        // População da tabela:
        reminders.add(new Reminder(LocalDate.of(2025, 12, 14), LocalTime.of(18, 30), "Cerimônia de formatura"));
        reminders.add(new Reminder(LocalDate.of(2021, 04, 28), LocalTime.of(15, 30), "Pagar boleto"));
        reminders.add(new Reminder(LocalDate.of(2023, 10, 31), LocalTime.of(19, 40), "Festa de Halloween"));
    }

    @GetMapping
    public List<Reminder> getAllReminders() {
        sortByDate(reminders);
        return reminders;
    }

    @PostMapping
    public void addReminder(Reminder reminder) throws IllegalArgumentException {
        if (reminder.getMessage() == null || reminder.getMessage().isEmpty())
            throw new IllegalArgumentException("Erro: A mensagem deve possuir conteúdo.");

        Reminder existingReminder = (Reminder) reminders.stream()
                        .filter(r -> r.getDate().equals(reminder.getDate()));

        reminders.add(reminder);
    }

    @PostMapping
    public void removeReminder(Long id) {
        Reminder reminderToRemove = null;
        for (Reminder reminder : reminders) {
            if (reminder.getId() == id) {
                reminderToRemove = reminder;
                break;
            }
        }

        if (reminderToRemove != null)
            reminders.remove(reminderToRemove);
    }

    /**
     * Utiliza classe anônima para ordenar objetos do tipo Reminder, que são comparáveis uma vez que implementam a
     * interface Comparable<>.
     *
     * @param reminders Lista de lembretes original.
     */
    public void sortByDate(List<Reminder> reminders) {
        reminders.sort(new Comparator<Reminder>() {
            @Override
            public int compare(Reminder o1, Reminder o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
