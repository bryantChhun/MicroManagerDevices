package MMExceptions;

public class AutomatedDetectionException extends Exception{

    public AutomatedDetectionException(String pString)
    {
        super(pString);
    }

    public AutomatedDetectionException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}