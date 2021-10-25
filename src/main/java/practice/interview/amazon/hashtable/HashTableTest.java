package practice.interview.amazon.hashtable;

public class HashTableTest {
    public static void main(String[] args) {
        HashTable hash = new HashTable();

        hash.put(10, "This is one");
        hash.put(1293, "This is two");
        hash.put(20, "This is three");
        hash.put(1020, "This is four");
        hash.put(20, "This is five");
        hash.put(1234, "This is six");
        hash.put(1234, "This is seven");
        hash.put(982, "This is eight");
        hash.put(120, "This is nine");
        hash.put(0, "This is then");
        hash.put(0, "This is eleven");

        System.out.println((String) hash.get(10));
        System.out.println((String) hash.get(20));
        System.out.println((String) hash.get(1020));
    }
}
