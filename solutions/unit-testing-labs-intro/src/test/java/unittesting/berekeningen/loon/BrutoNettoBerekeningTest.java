package unittesting.berekeningen.loon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import unittesting.dao.BrutoNettoBerekeningResultaat;
import unittesting.dao.BrutoNettoPersister;
import unittesting.domein.ContractType;
import unittesting.domein.LoonContext;
import unittesting.domein.LoonType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrutoNettoBerekeningTest {

    @Mock
    RszBerekening rszBerekening;

    @Mock
    BedrijfsvoorheffingBerekening bedrijfsvoorheffingBerekening;
    @Mock
    BrutoNettoPersister resultaatPersister;

    @InjectMocks
    BrutoNettoBerekening sut;

    MockHelper mock = new MockHelper();

    @Before
    public void init() {
        mock.rszBijdrage(130D);
        mock.bedrijfsVoorheffing(250D);
    }

    @Test
    public void givenDetails_whenBerekenNetto_thenGeeftGegevensDoorAanRszBerekening() {

        sut.berekenNetto(1000D, ContractType.ARBEIDER, LoonContext.ALLEENSTAANDE, LoonType.WERKNEMER);

        verify(rszBerekening).bepaalRszBijdrage(1000D, ContractType.ARBEIDER);
    }

    @Test
    public void givenDetails_whenBerekenNetto_thenGeeftBelastbaarLoonCorrectAanBedrijfsvoorheffingBerekening() {
        mock.rszBijdrage(100D);

        sut.berekenNetto(1000D, ContractType.ARBEIDER, LoonContext.ALLEENSTAANDE, LoonType.WERKNEMER);

        verify(bedrijfsvoorheffingBerekening).berekenBedrijfsvoorheffing(900D, LoonType.WERKNEMER, LoonContext.ALLEENSTAANDE);
    }

    @Test
    public void givenDetails_whenBerekenNetto_thenResultaatBevatCorrecteRszBijdrage() {
        sut.berekenNetto(1000D, ContractType.ARBEIDER, LoonContext.ALLEENSTAANDE, LoonType.WERKNEMER);

        BrutoNettoBerekeningResultaat resultaat = mock.capturePersister();
        assertThat(resultaat.getRszBijdrage(), is(130D));
    }

    @Test
    public void givenDetails_whenBerekenNetto_thenResultaatBevatCorrecteBedrijfsVoorheffing() {
        sut.berekenNetto(1000D, ContractType.ARBEIDER, LoonContext.ALLEENSTAANDE, LoonType.WERKNEMER);

        BrutoNettoBerekeningResultaat resultaat = mock.capturePersister();
        assertThat(resultaat.getBedrijfsvoorheffing(), is(250D));
    }

    @Test
    public void givenDetails_whenBerekenNetto_thenResultaatBevatCorrecteTotaalNetto() {
        sut.berekenNetto(1000D, ContractType.ARBEIDER, LoonContext.ALLEENSTAANDE, LoonType.WERKNEMER);

        BrutoNettoBerekeningResultaat resultaat = mock.capturePersister();
        assertThat(resultaat.getNettoLoon(), is(620.00));     //bruto - rsz - voorheffing = 1000 - 130 - 250;
    }

    class MockHelper {
        public void rszBijdrage(Double resultaat) {
            when(rszBerekening.bepaalRszBijdrage(anyDouble(), any(ContractType.class))).thenReturn(resultaat);
        }

        public void bedrijfsVoorheffing(Double resultaat) {
            when(bedrijfsvoorheffingBerekening.berekenBedrijfsvoorheffing(anyDouble(), any(LoonType.class), any(LoonContext.class))).thenReturn(resultaat);
        }

        public BrutoNettoBerekeningResultaat capturePersister() {
            ArgumentCaptor<BrutoNettoBerekeningResultaat> captor = ArgumentCaptor.forClass(BrutoNettoBerekeningResultaat.class);
            verify(resultaatPersister).slaBerekeningOp(captor.capture());
            return captor.getValue();
        }
    }
}