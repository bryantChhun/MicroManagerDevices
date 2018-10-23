package MMtest;

import MMCore.MMCore;
import MMDevices.MMLinearStageDevice;
import MMExceptions.*;
import mmcorej.CMMCore;
import mmcorej.DeviceDetectionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * all tests use micromanager's DemoCamera hub
 *   https://micro-manager.org/wiki/DemoCamera
 *
 */
public class testLinearStageDevice {

    private MMLinearStageDevice linearStage;
    private MMLinearStageDevice linearStage2;
    private CMMCore lcmm;

    // ==========  Basic device loading tests =================== //

    @Test
    void test_device_loading() {
        // Setup
        try{
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
        } catch (Exception ex) {
            System.out.println("test failed: Device load fail "+ex.getMessage());
            fail(ex);
        }

        // Shutdown
        lcmm = MMCore.getCore();
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}
    }

    @Test
    void test_invalid_device_loading() {
        // Setup
        try{
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "baddevicename");
        } catch (LoadStageDeviceException stex) {
            System.out.println("test passed: invalid device catch");
        } catch (Exception ex) {
            System.out.println("test failed: unable to catch invalid device"+ex.getMessage());
            fail(ex);
        }

        // Shutdown
        lcmm = MMCore.getCore();
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}
    }

    @Test
    void test_duplicate_device_labels() {
        // Setup
        try{
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
            linearStage2 = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
        } catch (LoadStageDeviceException ex) {
            System.out.println("test pass: duplicate device label catch");
        } catch (Exception ex) {
            System.out.println("test failed: unable to catch duplicate device"+ex.getMessage());
            fail(ex);
        }

        // Shutdown
        lcmm = MMCore.getCore();
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}
    }

    @Test
    void test_device_detection() {
        // Setup
        try{
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
        } catch (LoadStageDeviceException ex) {
            fail(ex);
        }

        // Tests
        try {
            DeviceDetectionStatus response = linearStage.detectDevice();
            System.out.println("device detection response = "+response.toString());
        } catch (DeviceNotDetectedException dex) {
            fail(dex);
        } catch (AutomatedDetectionException aex) {
            System.out.println("device does not support automated detection");
        }

        // Shutdown
        lcmm = MMCore.getCore();
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}

    }

    @Test
    void test_device_initialization() throws DeviceNotInitializedException {
        // Setup
        try {
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            linearStage.initializeDevice();
        } catch (DeviceNotInitializedException dex) {
            throw new DeviceNotInitializedException("test fail: device could not be initialized :"+dex);
        }

        // Shutdown
        lcmm = MMCore.getCore();
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
        }
    }

    // ==========  XY stage device-specific tests =================== //

    @Test
    void test_device_operation() {
        // Setup
        lcmm = MMCore.getCore();
        try {
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            linearStage.setOrigin();
            linearStage.setRelativePosition(100);
            int x = (int) Math.round(lcmm.getPosition(linearStage.getLabel()));
            int y = (int) Math.round(lcmm.getPosition(linearStage.getLabel()));
            if(x!=100 || y!= 100) {
                throw new DeviceNotSetException("unable to set device values");
            }
        } catch (Exception ex) {
            fail(ex);
        }

        // Shutdown
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
        }
    }

    @Test
    void test_multiple_similar_device_creation() {
        // Setup
        lcmm = MMCore.getCore();
        try {
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
            linearStage2 = new MMLinearStageDevice("stage2", "DemoCamera", "DStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            linearStage.setOrigin();
            linearStage.setRelativePosition(100);
            linearStage2.setOrigin();
            linearStage2.setRelativePosition(50);
            int x = (int) Math.round(lcmm.getPosition(linearStage.getLabel()));
            int y = (int) Math.round(lcmm.getPosition(linearStage.getLabel()));
            int x2 = (int) Math.round(lcmm.getPosition(linearStage2.getLabel()));
            int y2 = (int) Math.round(lcmm.getPosition(linearStage2.getLabel()));
            if(x!=100 || y!= 100 || x2!=50 || y2!= 50) {
                throw new DeviceNotSetException("unable to set device values on two similar devices");
            }
        } catch (Exception ex) {
            fail(ex);
        }


        // Shutdown
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
        }
    }

    /**
     * bottom test requires that one other device type exists.  Here we use the 1D stage type
     */
    @Test
    void test_multiple_different_device_creation() {
        // Setup
        lcmm = MMCore.getCore();
        try {
            linearStage = new MMLinearStageDevice("stage1", "DemoCamera", "DStage");
            linearStage2 = new MMLinearStageDevice("stage2", "DemoCamera", "DStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            linearStage.setOrigin();
            linearStage.setRelativePosition(100);
            linearStage2.setOrigin();
            linearStage2.setRelativePosition(50);
            int x = (int) Math.round(lcmm.getPosition(linearStage.getLabel()));
            int x2 = (int) Math.round(lcmm.getPosition(linearStage2.getLabel()));
            if(x!=100 || x2!=50) {
                throw new DeviceNotSetException("unable to set device values on two similar devices");
            }
        } catch (Exception ex) {
            fail(ex);
        }

        // Shutdown
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
        }
    }

}
