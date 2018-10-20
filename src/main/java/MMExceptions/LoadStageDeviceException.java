package MMExceptions;

public class LoadStageDeviceException extends Exception{

    public LoadStageDeviceException(String pString)
    {
        super(pString);
    }

    public LoadStageDeviceException(String pErrorMessage, Throwable pThrowable)
    {
        super(pErrorMessage, pThrowable);
    }

}
