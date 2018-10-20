package MMExceptions;

public class DeviceNotInitializedException extends Exception{

    public DeviceNotInitializedException(String pString)
    {
        super(pString);
    }

    public DeviceNotInitializedException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
