package unittesting.berekeningen.loon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import unittesting.domein.ContractType;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(Parameterized.class)
public class RszBerekeningTest {
    RszBerekening sut = new RszBerekening();

    private Double brutoLoon;
    private ContractType contractType;
    private Double expectedResult;

    public RszBerekeningTest(Double brutoLoon, ContractType contractType, Double expectedResult) {
        this.brutoLoon = brutoLoon;
        this.contractType = contractType;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1000D, ContractType.BEDIENDE, 130.70D},
                {2333D, ContractType.BEDIENDE, 304.92D},
                {1000D, ContractType.ARBEIDER, 141.16D},
                {2333D, ContractType.ARBEIDER, 329.32D}
        });
    }

    @Test
    public void givenBruttoLoonEnContractType_whenBerekenRsz_thenGeeftCorrectResultaat() {
        Double rszBijdrage = sut.bepaalRszBijdrage(brutoLoon, contractType);

        assertThat(rszBijdrage, is(expectedResult));
    }
}