package br.com.lott.reminderapp;

import br.com.lott.reminderapp.model.Reminder;
import br.com.lott.reminderapp.service.ReminderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReminderAppApplicationTests {

	@Autowired
	private ReminderAppApplication context;

	@Autowired
	private ReminderService reminderService;

	Reminder reminder1;
	Reminder reminder2;
	LocalDate date1;
	LocalDate date2;

	@BeforeEach
	public void setUp() {
		date1 = LocalDate.of(2023, 01, 01);
		date2 = LocalDate.of(2023, 12, 31);
		reminder1 = new Reminder(date1,
				LocalTime.now(),
				"Lembrete de Teste");
		reminder2 = new Reminder(date2,
				LocalTime.now(),
				"Lembrete de Teste");
	}

	@Test
	public void contextLoads() {
		assertNotNull(context);
	}

	@Test
	public void createReminderCorrectly() {
		assertNotNull(reminder1.getId());
		assertNotNull(reminder1.getDate());
		assertNotNull(reminder1.getTime());
		assertNotNull(reminder1.getMessage());
	}

	@Test
	public void addReminderTest() {
		reminderService.addReminder(reminder1);

		Map<LocalDate, List<Reminder>> reminders = reminderService.getAllReminders();
		List<Reminder> reminderList = reminders.get(date1);

		assertTrue(reminders.containsKey(date1), "Map deve incluir key com a data especificada");

		assertTrue(reminderList.contains(reminder1), "Value com o lembrete criado deve estar na lista");
	}

	@Test
	public void deleteReminderTest() {
		reminderService.addReminder(reminder2);
		reminderService.removeReminder(Long.valueOf(reminder2.getId()));

		Map<LocalDate, List<Reminder>> reminders = reminderService.getAllReminders();
		List<Reminder> reminderList = reminders.get(date2);

		assertFalse(reminders.containsKey(date2), "Map não deve incluir key com a data especificada");

		if (reminderList != null)
			assertFalse(reminderList.contains(reminder2), "Value com o lembrete criado não deve estar na lista");

	}

	@Test
	public void addReminderWithInvalidMessage() {
		Reminder reminder = new Reminder(LocalDate.now(), LocalTime.now(), null);

		assertThrows(IllegalArgumentException.class, () -> reminderService.addReminder(reminder));
	}

}
