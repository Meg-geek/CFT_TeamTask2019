package util;

public class Pair<L, R>{
    private L left;
    private R right;

    public Pair(L l, R r){
        left = l;
        right = r;
    }

    public L getLeft(){
        return left;
    }

    public R getRight(){
        return right;
    }
}
