class Leetcode9 {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        long rev=0;
        int temp=x;
        while(x>0){
            rev=(rev*10)+(x%10);
            x/=10;
        }
        if (rev==temp)return true;
        return false;
    }
}
