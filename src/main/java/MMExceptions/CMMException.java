package MMExceptions;

public class CMMException extends Exception{

    public CMMException(String pString)
    {
        super(pString);
    }

    public CMMException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
