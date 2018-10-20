package MMExceptions;

public class DeviceNotLoadedException extends Exception{

    public DeviceNotLoadedException(String pString)
    {
        super(pString);
    }

    public DeviceNotLoadedException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
