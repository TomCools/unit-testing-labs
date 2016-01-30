package sumofdigits;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Maak een testset met Parameterized.class
 */
@RunWith(Parameterized.class)
public class SumOfDigitsTest {
    private Double input;
    private Double expectedOutput;

    private SumOfDigits sut = new SumOfDigits();

    public SumOfDigitsTest(Double input, Double expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0.0, 0.0},
                {123.0, 6.0},
                {0.1, 1.0},
                {9821.13, 24.0},
        });
    }

    @Test
    public void givenNumberAsInput_whenCalculatingSumOfDigits_returnsCorrectSum() {
        Double sumOfDigits = sut.calculateSumOfDigits(input);

        assertThat(sumOfDigits, is(expectedOutput));
    }

}