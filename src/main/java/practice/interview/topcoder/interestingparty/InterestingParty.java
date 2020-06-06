package practice.interview.topcoder.interestingparty;

public class InterestingParty {
    public int bestInvitation(final String[] first, final String[] second) {
        int count = 1;

        for(int i = 1 ; i < first.length; i ++) {
            boolean matched = false;
            for(int j = 0 ; !matched && j < second.length; j++) {
                if( i != j) {
                    System.out.println("comparing : " + first[i] + " = " + first[j] + " | "
                            + first[i] + " = " + second[j]);
                    System.out.println("comparing : " + second[i] + " = " + second[j] + " | "
                            + second[i] + " = " + first[j] + "\n");

                    if(first[i].equalsIgnoreCase(first[j]) || first[i].equalsIgnoreCase(second[j]) ||
                            second[i].equalsIgnoreCase(second[j]) || second[i].equalsIgnoreCase(first[j])) {
                        matched = true;
                    }
                }
            }
            if(matched) count ++;
        }
        return count;
    }

    public static void main(String[] args) {
        InterestingParty interestingParty = new InterestingParty();

        // String[] first = {"fishing", "gardening", "swimming", "fishing"};
        // String[] second = {"hunting", "fishing", "fishing", "biting"};
        // System.out.println(interestingParty.bestInvitation(first, second));

        String[] first = {"a", "b", "c", "d"};
        String[] second = {"e", "f", "g", "h"};
        System.out.println(interestingParty.bestInvitation(first, second));
    }
}
