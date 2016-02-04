package unittesting.berekeningen.loon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import unittesting.domein.LoonContext;
import unittesting.domein.LoonType;
import unittesting.domein.SchaalWaarde;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BedrijfsvoorheffingBerekeningTest {
    @Mock
    private BedrijfsvoorheffingSchaalService schaalService;

    @InjectMocks
    private BedrijfsvoorheffingBerekening sut;

    private MockHelper mock = new MockHelper();

    @Test
    public void givenAlleenStaandPersoon_whenBerekenenBedrijfsvoorHeffing_gebruiktSchaal1() {
        sut.berekenBedrijfsvoorheffing(0D, LoonType.WERKNEMER, LoonContext.ALLEENSTAANDE);

        verify(schaalService).haalSchaalwaardenOp(1);
    }

    @Test
    public void givenTweeVerdienersGezin_whenBerekenenBedrijfsvoorHeffing_gebruiktSchaal1() {
        sut.berekenBedrijfsvoorheffing(0D, LoonType.WERKNEMER, LoonContext.TWEE_VERDIENER_GEZIN);

        verify(schaalService).haalSchaalwaardenOp(1);
    }

    @Test
    public void given1VerdienersGezin_whenBerekenenBedrijfsvoorHeffing_gebruiktSchaal2() {
        sut.berekenBedrijfsvoorheffing(0D, LoonType.WERKNEMER, LoonContext.EEN_VERDIENER_GEZIN);

        verify(schaalService).haalSchaalwaardenOp(2);
    }

    @Test
    public void givenNietBelgischeInwoners_whenBerekenenBedrijfsvoorHeffing_gebruiktSchaal3() {
        sut.berekenBedrijfsvoorheffing(0D, LoonType.WERKNEMER, LoonContext.NIET_INWONERS);

        verify(schaalService).haalSchaalwaardenOp(3);
    }

    @Test
    public void givenWerknemerMet1000BelastbaarLoon_whenBerekenBedrijfsVoorheffingVoorSchalen_SelecteertDeJuisteWaardeUitDeSchaal() {
        mock.voorheffingSchalen();

        Double bedrijfsvoorheffing = sut.berekenBedrijfsvoorheffing(1000D, LoonType.WERKNEMER, LoonContext.ALLEENSTAANDE);

        assertThat(bedrijfsvoorheffing, is(120D));
    }

    @Test
    public void givenWerknemerMet1600BelastbaarLoon_whenBerekenBedrijfsVoorheffingVoorSchalen_SelecteertDeJuisteWaardeUitDeSchaal() {
        mock.voorheffingSchalen();

        Double bedrijfsvoorheffing = sut.berekenBedrijfsvoorheffing(1600D, LoonType.WERKNEMER, LoonContext.ALLEENSTAANDE);

        assertThat(bedrijfsvoorheffing, is(150D));
    }

    class MockHelper {
        public void voorheffingSchalen() {
            when(schaalService.haalSchaalwaardenOp(anyInt())).thenReturn(
                    Arrays.asList(new SchaalWaarde(1000, 120, 150, 60),
                            new SchaalWaarde(1500, 150, 170, 80)));
        }

    }

}