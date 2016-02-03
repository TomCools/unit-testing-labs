package isp.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Builder
@Getter
public class Customer {
    private String firstname;
    private String lastname;
    private String customerNumber;
    private Set<NotificationPreference> notificationPreferences;
    private List<String> emailAdresses;
    private List<String> phoneNumbers;
}
