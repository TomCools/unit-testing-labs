package isp.processing;

import isp.domain.Customer;
import isp.domain.NotificationPreference;
import isp.processing.services.EmailService;
import isp.processing.services.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/*rekening houdende met de voorkeuren van de klant, geeft de ISP een melding bij het overschrijden van het datalimiet.
 Voeg testen toe, en zorg ervoor dat deze logica correct getest is.
 Pas alleen de NotificationProcessor aan! De Services moeten blijven zoals ze zijn (ze worden asynchroon geimplementeerd door een ander team).

Minimum Test cases:
 - Customer heeft 1 preference EMAIL en 1 E-mail adres
 - Customer heeft 1 preference EMAIL en 2 E-mail adressen
 - Customer heeft 1 preference EMAIL en 0 E-mail adressen
 - Customer heeft geen preferences en krijgt in dat geval geen notificatie
 - Customer heeft 2 preference (Email en SMS), hij heeft 1 E mail adres en 1 telefoonnummer
 - Customer heeft 2 preference (Email en SMS), hij heeft 1 E mail adres maar geen telefoonnummer

 Solution Notes:
 - één enkele verify per test is iets meer werk, maar zorgt vaak voor stabielere testen.
 - Bovenstaanded "Minimum Test Cases" kunden bijgevolg dus opgesplitst worden over verschillende testen.
 - Kijk in het boek voor RIGHT-BICEP en CORRECT afkortingen. Deze verklaren wat er getest zou moeten worden.
 - Ik maak hier hergebruik van de domein-builder in mijn testbuilder. Dit is voldoende voor deze oefening
 Vaak zal echter blijken dat je beter je testbuilder intelligenter kan maken.
*/
@RunWith(MockitoJUnitRunner.class)
public class NotificationProcessorTest {

    @Mock
    EmailService emailService;

    @Mock
    SmsService smsService;

    @InjectMocks
    NotificationProcessor sut;

    private DataHelper data = new DataHelper();

    @Test
    public void givenCustomerPreferenceEmailAndOneEmailAdres_whenDataLimitExceeded_sendsEmailToAdress() {
        Customer customer = data.aCustomerWithEmailPreferenceAnd1EmailAdres();

        sut.dataLimitExceeded(customer);

        verify(emailService).sendEmail(anyString(), anyString());
    }

    @Test
    public void givenCustomerPreferenceEmailAndMultipleEmailAdresses_whenDataLimitExceeded_sendsEmailToEachAdress() {
        Customer customer = data.aCustomerWithEmailPreferenceAnd2EmailAdresses();

        sut.dataLimitExceeded(customer);

        verify(emailService, times(2)).sendEmail(anyString(), anyString());
    }

    @Test
    public void givenCustomerPreferenceEmailAndNoEmailAdresses_whenDataLimitExceeded_sendsNoEmail() {
        Customer customer = data.aCustomerWithEmailPreferenceAndNoEmailAdresses();

        sut.dataLimitExceeded(customer);

        verify(emailService, never()).sendEmail(anyString(), anyString());
    }

    @Test
    public void givenCustomerWithoutPreference_whenDataLimitExceeded_doesNotSendEmail() {
        Customer customer = data.aCustomerWithoutPreferences();

        sut.dataLimitExceeded(customer);

        verify(emailService, never()).sendEmail(anyString(), anyString());
    }

    @Test
    public void givenCustomerPreferenceSmsAndOnePhoneNumber_whenDataLimitExceeded_sendsSms() {
        Customer customer = data.aCustomerWithSmsPrefenceAnd1PhoneNumber();

        sut.dataLimitExceeded(customer);

        verify(smsService).sendSms(anyString(), anyString());
    }

    @Test
    public void givenCustomerPreferenceSmsAndMultipleTelephoneNumbers_whenDataLimitExceeded_sendsSmsToEachNumber() {
        Customer customer = data.aCustomerWithSmsPrefenceAnd2PhoneNumbers();

        sut.dataLimitExceeded(customer);

        verify(smsService, times(2)).sendSms(anyString(), anyString());
    }

    @Test
    public void givenCustomerPreferenceSmsAndNoPhoneNumber_whenDataLimitExceeded_sendsNoSms() {
        Customer customer = data.aCustomerWithSmsPrefenceAndNoPhoneNumbers();

        sut.dataLimitExceeded(customer);

        verify(smsService, never()).sendSms(anyString(), anyString());
    }

    @Test
    public void givenCustomerWithoutPreference_whenDataLimitExceeded_doesNotSendSms() {
        Customer customer = data.aCustomerWithoutPreferences();

        sut.dataLimitExceeded(customer);

        verify(smsService, never()).sendSms(anyString(), anyString());
    }

    class DataHelper {
        public Customer aCustomerWithEmailPreferenceAnd1EmailAdres() {
            return TestBuilder.aCustomer()
                    .emailAdresses(Arrays.asList("tom.cools@infosupport.com"))
                    .notificationPreferences(new HashSet<NotificationPreference>(Arrays.asList(NotificationPreference.EMAIL)))
                    .build();
        }

        public Customer aCustomerWithEmailPreferenceAnd2EmailAdresses() {
            return TestBuilder.aCustomer()
                    .emailAdresses(Arrays.asList("tom.cools@infosupport.com", "tom.cools@github.com"))
                    .notificationPreferences(new HashSet<NotificationPreference>(Arrays.asList(NotificationPreference.EMAIL)))
                    .build();
        }

        public Customer aCustomerWithEmailPreferenceAndNoEmailAdresses() {
            return TestBuilder.aCustomer()
                    .emailAdresses(Collections.<String>emptyList())
                    .notificationPreferences(new HashSet<NotificationPreference>(Arrays.asList(NotificationPreference.EMAIL)))
                    .build();
        }

        public Customer aCustomerWithoutPreferences() {
            return TestBuilder.aCustomer()
                    .notificationPreferences(Collections.<NotificationPreference>emptySet())
                    .build();
        }

        public Customer aCustomerWithSmsPrefenceAnd1PhoneNumber() {
            return TestBuilder.aCustomer()
                    .phoneNumbers(Arrays.asList("0432 12 34 56"))
                    .notificationPreferences(new HashSet<NotificationPreference>(Arrays.asList(NotificationPreference.SMS)))
                    .build();
        }

        public Customer aCustomerWithSmsPrefenceAnd2PhoneNumbers() {
            return TestBuilder.aCustomer()
                    .phoneNumbers(Arrays.asList("0432 12 34 56", "0432 98 76 54"))
                    .notificationPreferences(new HashSet<NotificationPreference>(Arrays.asList(NotificationPreference.SMS)))
                    .build();
        }

        public Customer aCustomerWithSmsPrefenceAndNoPhoneNumbers() {
            return TestBuilder.aCustomer()
                    .phoneNumbers(Collections.<String>emptyList())
                    .notificationPreferences(new HashSet<NotificationPreference>(Arrays.asList(NotificationPreference.SMS)))
                    .build();
        }
    }
}