package MMExceptions;

public class DeviceByPropertyException extends Exception{

    public DeviceByPropertyException(String pString)
    {
        super(pString);
    }

    public DeviceByPropertyException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}