package MMInterfaces;

import MMExceptions.AutomatedDetectionException;
import MMExceptions.DeviceNotDetectedException;
import MMExceptions.DeviceNotInitializedException;
import mmcorej.DeviceDetectionStatus;

/**
 * all device base abstract classes will implement this interface
 */
public interface MMInitializeDeviceInterface {

    public DeviceDetectionStatus detectDevice() throws DeviceNotDetectedException, AutomatedDetectionException;

    public boolean initializeDevice() throws DeviceNotInitializedException;

    //TODO: do we need to set device parameters before initialization?  Serial ports? etc...

}
