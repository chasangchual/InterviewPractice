package practice.interview.amazon.stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(7);
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(123);
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(19);
        System.out.println(stack.print());
        System.out.println(stack.pop());
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(7);
        System.out.println(stack.print());
        System.out.println(stack.pop());
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(23);
        stack.push(87);
        System.out.println(stack.print());
        System.out.println(stack.pop());
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(4);
        System.out.println(stack.print());
        System.out.println(stack.pop());
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(6);
        System.out.println(stack.print());
        System.out.println(stack.pop());
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(4);
        System.out.println(stack.print());
        System.out.println(stack.pop());
        System.out.println(stack.print());
        System.out.println("-----------------------");
        stack.push(2);
        System.out.println(stack.print());
        System.out.println(stack.pop());
        System.out.println(stack.print());
        System.out.println("-----------------------");
    }
}
