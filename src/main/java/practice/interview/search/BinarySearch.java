package practice.interview.search;

public class BinarySearch {
    public int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 6));
        System.out.println(bs.binarySearch(new int[] {1, 4, 5, 6, 7, 8, 10}, 6));
        System.out.println(bs.binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 90));
        System.out.println(bs.binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, -1));
        System.out.println(bs.binarySearch(new int[] {}, 5));

        System.out.println(bs.binarySearch(new int[] {1}, 1));
        System.out.println(bs.binarySearch(new int[] {1}, 5));
        System.out.println(bs.binarySearch(new int[] {1, 2}, 2));
        System.out.println(bs.binarySearch(new int[] {1, 2}, 5));
    }
}
