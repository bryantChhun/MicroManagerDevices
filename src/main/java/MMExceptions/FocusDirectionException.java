package MMExceptions;

public class FocusDirectionException extends Exception{

    public FocusDirectionException(String pString)
    {
        super(pString);
    }

    public FocusDirectionException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
