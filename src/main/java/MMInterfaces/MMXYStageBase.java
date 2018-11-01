package MMInterfaces;

import MMExceptions.*;
import mmcorej.CMMCore;
import mmcorej.DeviceDetectionStatus;
import mmcorej.DoubleVector;

import MMCore.MMCore;

/**
 * Uses the CMMCore java wrapper to adjust and retrieve XY STAGE device properties
 *
 * @author bryant.chhun
 */
public class MMXYStageBase implements MMLoadUnloadDeviceInterface, MMInitializeDeviceInterface {

    private final CMMCore lcmm;
    private final String device_label;
    private final String device_module;
    private final String device_name;

    protected MMXYStageBase(String pdevice_label, String pdevice_module, String pdevice_name) {
        lcmm = MMCore.getCore();
        device_label = pdevice_label;
        device_module = pdevice_module;
        device_name = pdevice_name;
    }

    // =============  Stage Base methods  ==================== //

    public void setXYPosition(double x, double y) throws CMMException
    {
        try {
            lcmm.setXYPosition(device_label, x, y);
        } catch (Exception ex) {
            throw new CMMException("Error setting XY position for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void setXPosition(double x, double epsilon) throws CMMException
    {
        try {
            double currentY = this.getYPosition();
            this.setXYPosition(x, currentY);
        } catch (Exception ex) {
            throw new CMMException("Error setting X position for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void setYPosition(double y, double epsilon) throws CMMException
    {
        try {
            double currentX = this.getXPosition();
            this.setXYPosition(currentX, y);
        } catch (Exception ex) {
            throw new CMMException("Error setting X position for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void setRelativeXYPosition(double dx, double dy) throws CMMException
    {
        try {
            lcmm.setRelativeXYPosition(device_label, dx, dy);
        } catch (Exception ex) {
            throw new CMMException("Error setting relative XY position for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public double getXPosition() throws CMMException
    {
        try {
            return lcmm.getXPosition(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error getting X position for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public double getYPosition() throws CMMException
    {
        try {
            return lcmm.getYPosition(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error getting Y position for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void stop() throws CMMException
    {
        try {
            lcmm.stop(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error Stopping device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void home() throws CMMException
    {
        try {
            lcmm.home(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error Homing device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void setOriginXY() throws CMMException
    {
        try {
            lcmm.setOriginXY(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error setting Origin XY for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void setOriginX() throws CMMException
    {
        try {
            lcmm.setOriginX(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error setting Origin X for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void setOriginY() throws CMMException
    {
        try {
            lcmm.setOriginY(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error setting Origin Y for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void setAdapterOriginXY(double newXUm, double newYUm) throws CMMException
    {
        try {
            lcmm.setAdapterOriginXY(device_label, newXUm, newYUm);
        } catch (Exception ex) {
            throw new CMMException("Error setting Adapter Origin XY for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public boolean isXYStageSequenceable() throws CMMException
    {
        try {
            return lcmm.isXYStageSequenceable(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error checking XY Stage Sequenceable for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void startXYStageSequence() throws CMMException
    {
        try {
            lcmm.startXYStageSequence(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error starting XY Stage sequence for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void stopXYStageSequence() throws CMMException
    {
        try {
            lcmm.stopXYStageSequence(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error stopping XY Stage sequence for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public long getXYStageSequenceMaxLength() throws CMMException
    {
        try {
            return lcmm.getXYStageSequenceMaxLength(device_label);
        } catch (Exception ex) {
            throw new CMMException("Error getting XY Stage Sequence Max length for device: " + device_label + "\t"+ex.getMessage());
        }
    }

    public void loadXYStageSequence(DoubleVector xSequence, DoubleVector ySequence) throws CMMException
    {
        try {
            lcmm.loadXYStageSequence(device_label, xSequence, ySequence);
        } catch (Exception ex) {
            throw new CMMException("Error loading XY Stage Sequence for device: " + device_label + "\t"+ex.getMessage());
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
    public boolean unloadDevice() throws LoadStageDeviceException{
        try {
            lcmm.unloadDevice(device_label);
            return true;
        } catch (Exception ex) {
            throw new LoadStageDeviceException("could not UNload micromanager stage device: " + ex.getMessage());
        }
    }

    @Override
    public boolean unloadLibrary() throws LoadStageDeviceException{
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
        if(lcmm.supportsDeviceDetection(device_label)){
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
