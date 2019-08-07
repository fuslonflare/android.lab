package csd.gisc.ywc16;

public class SampleCode {

    private static final String[] SUIT = {"C", "D", "H", "S"};
    private static final String[] VALUE = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public static void main(String[] args) {
        String message = cardAt(48);
        System.out.println(message);
    }

    private static String cardAt(int n) throws IndexOutOfBoundsException {
        String result;
        int suitIndex = n / (VALUE.length);
        int valueIndex = n % (VALUE.length);

        if (suitIndex >= SUIT.length) {
            return "Out of bound";
        }

        result = SUIT[suitIndex] + VALUE[valueIndex];
        return result;
    }
}
