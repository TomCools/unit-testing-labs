package sumofdigits;

public class SumOfDigits {

    public Double calculateSumOfDigits(double number) {
        double sum = 0;

        String stringValue = String.valueOf(number);

        for (char c : stringValue.replace(".","").toCharArray()) {
            int digit = Integer.parseInt(String.valueOf(c));
            sum += digit;
        }

        return sum;
    }
}
