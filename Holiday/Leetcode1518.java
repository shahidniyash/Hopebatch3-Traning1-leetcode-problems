class Leetcode1518 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int full = numBottles;
        int result = 0;
        while(full>0){
            if(full>=numExchange){
                result+=full- full%numExchange;
                full=full/numExchange + full%numExchange;
            }
            else{
                result+=full;
                full = 0;
            }
        }
        return result;
        
    }
}
