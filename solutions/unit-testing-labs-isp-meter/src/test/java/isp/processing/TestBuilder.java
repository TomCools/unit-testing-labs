package isp.processing;

import isp.domain.Customer;
import isp.domain.NotificationPreference;

import java.util.Collections;

public class TestBuilder {
    public static Customer.CustomerBuilder aCustomer() {
        return Customer.builder()
                .firstname("Tom")
                .lastname("Cools")
                .customerNumber("123456")
                .emailAdresses(Collections.<String>emptyList())
                .notificationPreferences(Collections.<NotificationPreference>emptySet())
                .phoneNumbers(Collections.<String>emptyList());
    }
}
