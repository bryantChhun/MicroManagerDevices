package MMExceptions;

public class DeviceNotDetectedException extends Exception{

    public DeviceNotDetectedException(String pString)
    {
        super(pString);
    }

    public DeviceNotDetectedException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
