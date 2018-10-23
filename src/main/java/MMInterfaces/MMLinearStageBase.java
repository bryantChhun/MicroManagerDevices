package MMInterfaces;

import FocusDirectionException.FocusDirectionException;
import MMCore.MMCore;
import MMExceptions.*;
import mmcorej.CMMCore;
import mmcorej.DeviceDetectionStatus;

public class MMLinearStageBase implements MMLoadUnloadDeviceInterface, MMInitializeDevice {

    private final CMMCore lcmm;
    private final String device_label;
    private final String device_module;
    private final String device_name;

    protected MMLinearStageBase(String pdevice_label, String pdevice_module, String pdevice_name) {
        lcmm = MMCore.getCore();
        device_label = pdevice_label;
        device_module = pdevice_module;
        device_name = pdevice_name;
    }

    // =============  Stage Base methods  ==================== //

    public void setOrigin() throws CMMException {
        try {
            lcmm.setOrigin(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error setting XY position for device: " + device_label + "\t" + ex.getMessage());
        }
    }

    public void setPosition(double z) throws CMMException {
        try {
            lcmm.setPosition(device_label, z);
        } catch (Exception ex) {
            throw new CMMException("Error setting XY position for device: " + device_label + "\t" + ex.getMessage());
        }
    }

    public void setRelativePosition(double dz) throws CMMException {
        try {
            lcmm.setRelativePosition(device_label, dz);
        } catch (Exception ex) {
            throw new CMMException("Error setting relative XY position for device: " + device_label + "\t" + ex.getMessage());
        }
    }

    public void setAdapterOrigin(double dz) throws CMMException {
        try {
            lcmm.setAdapterOrigin(device_label, dz);
        } catch (Exception ex) {
            throw new CMMException("Error setting relative XY position for device: " + device_label + "\t" + ex.getMessage());
        }
    }

    public void setFocusDirection(int sign) throws CMMException, FocusDirectionException {
        if(sign!=-1 && sign!=0 && sign!= 1){
            throw new FocusDirectionException("sign must be -1, 0, or 1");
        }

        try {
            lcmm.setFocusDirection(device_label, sign);
        } catch (Exception ex) {
            throw new CMMException("Error setting relative XY position for device: " + device_label + "\t" + ex.getMessage());
        }
    }

    public double getPosition() throws CMMException {
        try {
            return lcmm.getPosition(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error getting X position for device: " + device_label + "\t" + ex.getMessage());
        }
    }

    public int getFocusDirection() throws CMMException {
        try {
            return lcmm.getFocusDirection(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error getting X position for device: " + device_label + "\t" + ex.getMessage());
        }
    }

    // =============  Load/Unload device interface methods ==================== //


    @Override
    public void loadDevice() throws LoadStageDeviceException {
        try {
            lcmm.loadDevice(device_label, device_module, device_name);
        } catch (Exception ex) {
            throw new LoadStageDeviceException("could not load micromanager stage device: " + ex.getMessage());
        }
    }

    @Override
    public void unloadDevice() throws LoadStageDeviceException {
        try {
            lcmm.unloadDevice(device_label);
        } catch (Exception ex) {
            throw new LoadStageDeviceException("could not UNload micromanager stage device: " + ex.getMessage());
        }
    }

    @Override
    public void unloadLibrary() throws LoadStageDeviceException {
        try {
            lcmm.unloadLibrary(device_module);
        } catch (Exception ex) {
            throw new LoadStageDeviceException("could not UNLOAD library for micromanager stage device: " + ex.getMessage());
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
    public void initializeDevice() throws DeviceNotInitializedException {
        try {
            lcmm.initializeDevice(device_label);
        } catch (Exception ex) {
            throw new DeviceNotInitializedException("could not detect device " + ex.getMessage());
        }
    }
}