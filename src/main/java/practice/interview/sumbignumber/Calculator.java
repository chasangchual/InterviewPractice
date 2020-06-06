package practice.interview.sumbignumber;

import java.util.Arrays;

public class Calculator {
    /**
     * step 1: cover positive numbers
     */
    public String add(final String num1, final String num2) {
        String added = "";

        boolean isVal1Negative = isNegative(num1);
        boolean isVal2Negative = isNegative(num2);

        if((isVal1Negative && ! isVal2Negative) || (! isVal1Negative && isVal2Negative)) {
            return isVal1Negative ? sub(num2, num1) : sub(num1, num2);
        }

        String val1 = num1;
        String val2 = num2;

        if(isVal1Negative && isVal2Negative) {
            val1 = num1.substring(1);
            val2 = num2.substring(1);
        }

        int len1 = val1.length();
        int len2 = val2.length();
        int len = Math.max(len1, len2);

        char[] val1arr = fill(len, val1, '0').toCharArray();
        char[] val2arr = fill(len, val2, '0').toCharArray();

        Integer carry = 0;

        for(int i = len-1; i >= 0; i--){
            char c1 = val1arr[i];
            char c2 = val2arr[i];

            int sum = add(c1, c2) + carry;
            int val = sum % 10;

            added = String.valueOf(val) + added;

            carry = (int)(sum / 10);
        }

        if(carry > 0) {
            added = String.valueOf(carry) + added;
        }

        if(isVal1Negative && isVal2Negative) {
            added = "-" + added;
        }

        return added;
    }

    public int add(final char val1, final char val2) {
        return (val1 - '0') + (val2 - '0');
    }

    public int sub(final char val1, final char val2) {
        return (val1 - '0') - (val2 - '0');
    }

    public String sub(final String val1, final String val2) {
        String added = "";
        return added;
    }

    public boolean isNegative(final String val) {
        return val.matches("-.*");
    }

    public String fill(final int len, final String str, final char c) {
        String filled = "";

        for(int i = 0; i < len - str.length(); i++) {
            filled = c + filled;
        }

        return filled + str;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("expected 1776, actual: " + calculator.add("789", "987"));

        //System.out.println("expected 0000001234, actual: " + calculator.fill(10, "1234", '0'));
        //System.out.println("expected true, actual: " + calculator.isNegative("-123"));
    }
}
