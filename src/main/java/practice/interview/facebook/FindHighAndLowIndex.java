package practice.interview.facebook;

public class FindHighAndLowIndex {
    public void Solve(int[] in) {

    }

    private int find(int[] in, int start, int end, int key) {
        if(end > (start+1)) {
            int m = (int)((end + start) / 2);

            if(in[m] == key) return m;
            if(in[m] > key) return find(in, start, m, key);
            return find(in, m, end, key);
        } else {
            if(in[start] == key) return start;
            if(in[end] == key) return end;
            return -1;
        }
    }

    public int findUpperBound(int[] in, int start, int end, int key) {
        if(end > (start+1)) {
            int m = (int)((end + start) / 2);

            if(in[m] > key) {
                return findUpperBound(in, start, m, key);
            } else {
                return findUpperBound(in, m+1, end, key);
            }
        } else {
            return end;
        }
    }

    public int findLowerBound(int[] in, int start, int end, int key) {
        if(end > (start+1)) {
            int m = (int)((end + start) / 2);

            if(in[m] < key) {
                return findLowerBound(in, m, end, key);
            } else {
                return findLowerBound(in, start, m-1, key);
            }
        } else {
            return end;
        }
    }

    public static void main(String[] args) {
        FindHighAndLowIndex f = new FindHighAndLowIndex();

        int[] in = new int[]{1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 6, 6 ,6 ,6 ,6 ,7 ,7, 7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,8 ,8 ,8 ,8 ,8 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9 ,9};
        // int[] in = new int[]{1,2,3,4,5,6,7,8,9,10};
        int key = 5;
        int index = f.find(in, 0, in.length-1, key);
        System.out.println(index);

        int ubIndex = f.findUpperBound(in, index, in.length-1, key);
        System.out.println(ubIndex);

        int lbIndex = f.findLowerBound(in, 0, index-1, key);
        System.out.println(lbIndex);

        System.out.println(in[index]);
        System.out.println(in[ubIndex]);
        System.out.println(in[ubIndex-1]);

        System.out.println(in[lbIndex]);
        System.out.println(in[lbIndex+1]);
    }
}
