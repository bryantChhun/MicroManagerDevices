package MMInterfaces;

import MMExceptions.FocusDirectionException;
import MMCore.MMCore;
import MMExceptions.*;
import mmcorej.CMMCore;
import mmcorej.DeviceDetectionStatus;

/**
 * Uses MMCore Java wrapper to control devices using string properties.
 *   See exact documentation for your device for available strings.
 *
 */
public class MMGenericDeviceByPropertyBase implements MMLoadUnloadDeviceInterface, MMInitializeDeviceInterface {

    private final CMMCore lcmm;
    private final String device_label;
    private final String device_module;
    private final String device_name;


    protected MMGenericDeviceByPropertyBase(String pdevice_label, String pdevice_module, String pdevice_name) {
        lcmm = MMCore.getCore();
        device_label = pdevice_label;
        device_module = pdevice_module;
        device_name = pdevice_name;
    }

    // =============  Stage Base methods  ==================== //

    //TODO: use generics here to simplify?
    protected void setProperty(String property, float value) throws DeviceByPropertyException {
        try{
            lcmm.setProperty(device_label, property, value);
        } catch (Exception ex) {
            throw new DeviceByPropertyException(String.format("Exception setting property: %s to value: %f  : " +ex.getMessage(), property, value));
        }
    }

    protected void setProperty(String property, int value) throws DeviceByPropertyException {
        try{
            lcmm.setProperty(device_label, property, value);
        } catch (Exception ex) {
            throw new DeviceByPropertyException(String.format("Exception setting property: %s to value: %i  : " +ex.getMessage(), property, value));
        }
    }

    protected void setProperty(String property, double value) throws DeviceByPropertyException {
        try{
            lcmm.setProperty(device_label, property, value);
        } catch (Exception ex) {
            throw new DeviceByPropertyException(String.format("Exception setting property: %s to value: %d  : " +ex.getMessage(), property, value));
        }
    }

    protected void setProperty(String property, String value) throws DeviceByPropertyException {
        try{
            lcmm.setProperty(device_label, property, value);
        } catch (Exception ex) {
            throw new DeviceByPropertyException(String.format("Exception setting property: %s to value: %s  : " +ex.getMessage(), property, value));
        }
    }

    protected void setProperty(String property, Boolean value) throws DeviceByPropertyException {
        try{
            lcmm.setProperty(device_label, property, value);
        } catch (Exception ex) {
            throw new DeviceByPropertyException(String.format("Exception setting property: %s to value: %b  : " +ex.getMessage(), property, value));
        }
    }

    // =============  Load/Unload device interface methods ==================== //


    @Override
    public boolean loadDevice() throws LoadStageDeviceException {
        try {
            lcmm.loadDevice(device_label, device_module, device_name);
            return true;
        } catch (Exception ex) {
            throw new LoadStageDeviceException("could not load micromanager stage device: " + ex.getMessage());
        }
    }

    @Override
    public boolean unloadDevice() throws LoadStageDeviceException {
        try {
            lcmm.unloadDevice(device_label);
            return true;
        } catch (Exception ex) {
            throw new LoadStageDeviceException("could not UNload micromanager stage device: " + ex.getMessage());
        }
    }

    @Override
    public boolean unloadLibrary() throws LoadStageDeviceException {
        try {
            lcmm.unloadLibrary(device_module);
            return true;
        } catch (Exception ex) {
            throw new LoadStageDeviceException("could not UNLOAD library for micromanager stage device: " + ex.getMessage());
        }
    }

    @Override
    public boolean unloadAllDevices() throws LoadStageDeviceException {
        try {
            lcmm.unloadAllDevices();
            return true;
        } catch (Exception ex) {
            throw new LoadStageDeviceException("Could not UNLOAD ALL micromanager devices: " + ex.getMessage());
        }
    }

    // =============  Initialize Device interface methods ==================== //

    @Override
    public DeviceDetectionStatus detectDevice() throws DeviceNotDetectedException, AutomatedDetectionException {
        if (lcmm.supportsDeviceDetection(device_label)) {
            try {
                return lcmm.detectDevice(device_label);
            } catch (Exception ex) {
                throw new DeviceNotDetectedException("could not detect device " + ex.getMessage());
            }
        } else {
            throw new AutomatedDetectionException("device does not support automated detection ");
        }

    }

    @Override
    public boolean initializeDevice() throws DeviceNotInitializedException {
        try {
            lcmm.initializeDevice(device_label);
            return true;
        } catch (Exception ex) {
            throw new DeviceNotInitializedException("could not detect device " + ex.getMessage());
        }
    }

}
