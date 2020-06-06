package practice.interview;

public class BalancedBrackets {
    // condition, input string size would not go over 10^3
    // in case of the input is well balanced, we may need half of string size
    // worst case, if all input strings are open bracket, whole input string needs to be pushed
    private static int CAPACITY = 1000;

    private char[] stack = new char[CAPACITY];
    private int head = -1;

    public String solve(final String input) {
        // input data validation
        if(input == null || input.length() > CAPACITY) {
            throw new IllegalArgumentException("invalid input string");
        }

        char[] inputArray = input.toCharArray();
        boolean balanced = true;

        for(int i = 0; i < inputArray.length && balanced; i++) {
            if(isOpen(inputArray[i])) {
                push(inputArray[i]);
            } else {
                char c = pop() ;
                balanced = isMatched(c, inputArray[i]);
            }
        }

        return balanced ? "YES" : "NO";
    }

    // returns true if the input character is a open bracket, otherwise it retruns false
    // throw IllegalArgumentException exception if the input character is not a valid bracket
    private boolean isOpen(final char c) {
        if(c == '[' || c == '{' || c == '(' || c == '<') {
            return true;
        } else if(c == ']' || c == '}' || c == ')' || c == '>') {
            return false;
        } else {
            throw new IllegalArgumentException("not bracket");
        }
    }

    // return if the input characters are the open and close pair. Otherwise it returns false
    private boolean isMatched(final char open, final char close) {
        if( (open == '[' && close == ']') || (open == '{' && close == '}') ||
            (open == '(' && close == ')') || (open == '<' && close == '>') ) {
            return true;
        }
        return false;
    }

    // put the character to the queue
    private void push(final char c) {
        if(head < CAPACITY) {
            stack[++head] = c;
        } else {
            throw new IllegalStateException("stack is full");
        }
    }

    // get the character from the queue
    private char pop() {
        if(head >= 0) {
            return stack[head--];
        } else {
            throw new IllegalStateException("stack is empty");
        }
    }

    public static void main(String[] args) {
        BalancedBrackets solution = new BalancedBrackets();
        System.out.println(solution.solve("{[()]}"));
        System.out.println(solution.solve("{[(])}"));
        System.out.println(solution.solve("{{[[(())]]}}"));
    }
}
