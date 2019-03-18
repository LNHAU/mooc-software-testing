package tudelft.numfinder;

public class NumFinder {
    private int smallest = Integer.MAX_VALUE;
    private int largest = Integer.MIN_VALUE;

    public void find(int[] nums) {
        if ((nums == null) || (nums.length == 0)) { //A ne pas oublier!
            smallest = -1;
            largest = -1;
        }
        for(int n : nums) {
            if(n < smallest)
                smallest = n;
            if (n > largest) //et non pas else if!
                largest = n;
        }
    }

    public int getSmallest () {
        return smallest;
    }

    public int getLargest () {
        return largest;
    }
}
