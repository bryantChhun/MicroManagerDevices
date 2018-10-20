package MMInterfaces;

import MMExceptions.AutomatedDetectionException;
import MMExceptions.DeviceNotDetectedException;
import MMExceptions.DeviceNotInitializedException;
import mmcorej.DeviceDetectionStatus;

/**
 * all device base abstract classes will implement this interface
 */
public interface MMInitializeDevice {

    public DeviceDetectionStatus detectDevice() throws DeviceNotDetectedException, AutomatedDetectionException;

    public void initializeDevice() throws DeviceNotInitializedException;

}
