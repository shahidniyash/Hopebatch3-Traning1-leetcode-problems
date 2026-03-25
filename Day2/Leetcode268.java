class Leetcode268 {
    public int missingNumber(int[] nums) {
        int i=0;
        while(i<nums.length){
            int current = nums[i];
            if(nums[i] < nums.length && nums[i] != nums[current]){
                int temp = nums[i];
                nums[i] = nums[current];
                nums[current] = temp;
            }else{
                i++;
            }
        }
        for(int j = 0; j < nums.length; j++){
            if(nums[j] != j){
                return j;
            }
        }
        return nums.length;
    }
}
