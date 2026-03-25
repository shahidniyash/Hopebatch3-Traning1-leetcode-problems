class Leetcode3232 {
    public boolean canAliceWin(int[] nums) {
        int Asum=0,Bsum=0;
        for(int i:nums){
            if(i<10) Asum+=i;
            else Bsum+=i;
        }
        return (Asum != Bsum)? true : false ;
    }
}
