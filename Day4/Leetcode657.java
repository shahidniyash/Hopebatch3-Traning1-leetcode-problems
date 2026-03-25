class Leetcode657 {
    public boolean judgeCircle(String moves) {
        int x=0,y=0;
        for(char i:moves.toCharArray()){
            if(i == 'U') y++;
            else if(i == 'D') y--;
            else if(i == 'L') x--;
            else x++;
        }
        if(x==0 && y==0) return true;
        return false;
    }
}
