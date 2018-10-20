package MMExceptions;

public class LoadLaserDeviceException extends Exception{

    public LoadLaserDeviceException(String pString)
    {
        super(pString);
    }

    public LoadLaserDeviceException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
