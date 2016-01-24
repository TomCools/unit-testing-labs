package isp.processing;

import isp.domain.Customer;

//rekening houdende met de voorkeuren van de klant, geeft de ISP een melding bij het overschrijden van het datalimiet.
public class NotificationProcessor {

    public void dataLimitExceeded(Customer customer) {
        String dataExceededMessage = "You've exceeded your data for this month. You have been put on smallband. Sorry...";

    }
}
