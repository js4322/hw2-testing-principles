package calc;

public class SimpleCalc implements ICalc{
    private static SimpleCalc instance;
    private SimpleCalc(){}
    public static SimpleCalc getInstance(){
        if(instance == null)
            instance = new SimpleCalc();
        return instance;
    }
    @Override
    public float calc(String expression) {
        return 0;
    }
}
