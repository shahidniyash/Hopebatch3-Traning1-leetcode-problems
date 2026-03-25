class Leetcode2739 {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int totaltank = mainTank;
        while(mainTank>=5 && additionalTank>=1){
            mainTank-=5;
            mainTank+=1;
            additionalTank--;
            totaltank+=1;
        }
        return totaltank*10;
    }
}
