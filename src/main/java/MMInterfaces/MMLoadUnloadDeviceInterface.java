package MMInterfaces;

import MMExceptions.LoadStageDeviceException;

/**
 * all device base abstract classes will implement this interface
 */
public interface MMLoadUnloadDeviceInterface {

    public void loadDevice() throws LoadStageDeviceException;

    public void unloadDevice() throws LoadStageDeviceException;

    public void unloadLibrary() throws LoadStageDeviceException;

    public void unloadAllDevices() throws LoadStageDeviceException;


}
