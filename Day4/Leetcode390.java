class Leetcode390 {
    public int lastRemaining(int n) {
        boolean left = true;
        int head=1, step=1;
        int rem=n;
        while(rem>1){
            if(left || rem%2!=0){
                head+=step;
            }
            rem/=2;
            step*=2;
            left=!left;
        }
        return head;

    }
}
