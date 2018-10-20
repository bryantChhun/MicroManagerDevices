package MMExceptions;

public class DeviceNotSetException extends Exception{

    public DeviceNotSetException(String pString)
    {
        super(pString);
    }

    public DeviceNotSetException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
