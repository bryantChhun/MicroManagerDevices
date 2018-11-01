package MMInterfaces;

import MMExceptions.LoadStageDeviceException;

/**
 * all device base abstract classes will implement this interface
 */
public interface MMLoadUnloadDeviceInterface {

    public boolean loadDevice() throws LoadStageDeviceException;

    public boolean unloadDevice() throws LoadStageDeviceException;

    public boolean unloadLibrary() throws LoadStageDeviceException;

    public boolean unloadAllDevices() throws LoadStageDeviceException;


}
