package functions.lambda_compare;

public class HighOrderFunc {

    /**
     * What if we want to set a function as the parameter?
     * This parameter should be like a simplified version callback function
     * @param param the is parameter to be passed
     * @return
     */
    int a(int param)
    {
        return b(param) + 1;
    }

    int b(int num) {
        return 1;
    }

    /**
     * Java does not allow a function to be a parameter, but an interface should be a good solution
     *
     * Then we can let the interface be the parameter type, and then the method can be called inside anther function
     */
    public interface Wrapper{
        int method();//this method act like a parameter
    }

    int a(Wrapper wrapper){
        return wrapper.method() + 1;
    }
}

