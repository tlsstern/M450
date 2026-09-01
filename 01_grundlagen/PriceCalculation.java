public class PriceCalculation {

    static double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;

        if (extras >= 3)
            addon_discount = 10;
        else if (extras >= 5)
            addon_discount = 15;
        else
            addon_discount = 0;

        if (discount > addon_discount)
            addon_discount = discount;

        result = baseprice/100.0 * (100-discount) + specialprice
                + extraprice/100.0 * (100-addon_discount);

        return result;
    }

    // Compares expected and actual value and prints one line per test case.
    static boolean check(String name, double expected, double actual) {
        boolean ok = Math.abs(expected - actual) < 0.01;
        System.out.println((ok ? "OK     " : "FAILED ") + name
                + "  expected: " + expected + "  actual: " + actual);
        return ok;
    }

    static boolean test_calculate_price() {
        double price;
        boolean test_ok = true;

        // no extras, no dealer discount
        price = calculatePrice(20000, 2000, 1000, 0, 0);
        if (!check("no extras           ", 23000.0, price)) test_ok = false;

        // dealer discount of 5% on the base price
        price = calculatePrice(20000, 0, 0, 0, 5);
        if (!check("dealer discount 5%  ", 19000.0, price)) test_ok = false;

        // three extras, 10% on the accessories
        price = calculatePrice(20000, 0, 2000, 3, 0);
        if (!check("three extras        ", 21800.0, price)) test_ok = false;

        // five extras, 15% on the accessories according to the rules
        price = calculatePrice(20000, 0, 2000, 5, 0);
        if (!check("five extras         ", 21700.0, price)) test_ok = false;

        // dealer discount higher than the accessory discount, applies to both
        price = calculatePrice(20000, 0, 2000, 3, 20);
        if (!check("dealer discount 20% ", 17600.0, price)) test_ok = false;

        return test_ok;
    }

    public static void main(String[] args) {
        boolean test_ok = test_calculate_price();
        System.out.println();
        if (test_ok) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("At least one test case failed.");
        }
    }
}
