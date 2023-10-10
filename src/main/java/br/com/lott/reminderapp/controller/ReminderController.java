package br.com.lott.reminderapp.controller;

import br.com.lott.reminderapp.model.Reminder;
import br.com.lott.reminderapp.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addReminder")
    public String addReminder(@ModelAttribute Reminder reminder) {
        reminderService.addReminder(reminder);
        return "redirect:/reminders";
    }
}
