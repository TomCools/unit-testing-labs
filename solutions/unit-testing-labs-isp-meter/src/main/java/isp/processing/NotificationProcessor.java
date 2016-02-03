package isp.processing;

import isp.domain.Customer;
import isp.domain.NotificationPreference;
import isp.processing.services.EmailService;
import isp.processing.services.SmsService;

//rekening houdende met de voorkeuren van de klant, geeft de ISP een melding bij het overschrijden van het datalimiet.
public class NotificationProcessor {

    private final static String DATA_EXCEEDED_MESSAGE = "You've exceeded your data for this month. You have been put on smallband. Sorry...";

    private EmailService emailService;
    private SmsService smsService;

    public NotificationProcessor(EmailService emailService, SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void dataLimitExceeded(Customer customer) {
        for (NotificationPreference preference : customer.getNotificationPreferences()) {
            switch (preference) {
                case EMAIL:
                    notifyViaEmail(customer);
                    break;
                case SMS:
                    notifyViaSms(customer);
                    break;
            }
        }
    }

    private void notifyViaEmail(Customer customer) {
        for (String emailAdress : customer.getEmailAdresses()) {
            emailService.sendEmail(emailAdress, DATA_EXCEEDED_MESSAGE);
        }
    }

    private void notifyViaSms(Customer customer) {
        for (String numbers : customer.getPhoneNumbers()) {
            smsService.sendSms(numbers, DATA_EXCEEDED_MESSAGE);
        }

    }
}
