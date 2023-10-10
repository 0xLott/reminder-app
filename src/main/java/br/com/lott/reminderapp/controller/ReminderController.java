package br.com.lott.reminderapp.controller;

import br.com.lott.reminderapp.model.Reminder;
import br.com.lott.reminderapp.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ReminderController {

    private ReminderService reminderService;

    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @RequestMapping("/reminders")
    public String reminders() {
        return "reminders";
    }

    @GetMapping("/reminders")
    public String displayTable(Model model) {
        List<Reminder> allReminders = reminderService.getAllReminders();
        model.addAttribute("reminders", allReminders);
        return "reminders";
    }

    @PostMapping("/add")
    public String addReminder(@ModelAttribute Reminder reminder) {
        reminderService.addReminder(reminder);
        return "redirect:/reminders";
    }

    @PostMapping("/deleteReminder/{id}")
    public String deleteReminder(@PathVariable Long id) {
        reminderService.removeReminder(id);
        return "redirect:/reminders";
    }
}
