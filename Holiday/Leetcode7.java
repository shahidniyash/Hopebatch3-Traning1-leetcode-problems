class Leetcode7 {
    public int reverse(int x) {
        Boolean s=true;
        if (x<0) s=false;
         x= Math.abs(x);
         long rev=0;
         while(x>0){
            rev=(rev*10)+(x%10);
            x/=10;
            if(rev> Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
         }
          if(!s) return -1*(int)rev;
           return (int)rev;

    }
}
