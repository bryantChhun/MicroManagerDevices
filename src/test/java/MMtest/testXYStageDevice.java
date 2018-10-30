package MMtest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import MMCore.MMCore;
import MMDevices.MMLinearStageDevice;
import MMDevices.MMXYStageDevice;
import MMExceptions.*;
import mmcorej.CMMCore;
import mmcorej.DeviceDetectionStatus;
import org.junit.jupiter.api.Test;

/**
 * all tests use micromanager's DemoCamera hub
 *   https://micro-manager.org/wiki/DemoCamera
 *
 */
public class testXYStageDevice {

    private MMXYStageDevice xyStage;
    private MMXYStageDevice xyStage2;
    private MMLinearStageDevice linStage;
    private CMMCore lcmm;

    // ==========  Basic device loading tests =================== //

    @Test
    void test_device_loading() {
        // Setup
        try{
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
        } catch (Exception ex) {
            System.out.println("test failed: Device load fail "+ex.getMessage());
            fail(ex);
        }

        // Shutdown
        lcmm = MMCore.getCore();
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
            fail(ex);
        }
    }

    @Test
    void test_invalid_device_loading() {
        // Setup
        try{
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "baddevicename");
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
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
            fail(ex);
        }
    }

    @Test
    void test_duplicate_device_labels() {
        // Setup
        try{
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
            xyStage2 = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
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
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
            fail(ex);
        }
    }

    @Test
    void test_device_detection() {
        // Setup
        try{
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
        } catch (LoadStageDeviceException ex) {
            fail(ex);
        }

        // Tests
        try {
            DeviceDetectionStatus response = xyStage.detectDevice();
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
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
            fail(ex);
        }

    }

    @Test
    void test_device_initialization() throws DeviceNotInitializedException {
        // Setup
        try {
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            xyStage.initializeDevice();
        } catch (DeviceNotInitializedException dex) {
            fail(dex);
            throw new DeviceNotInitializedException("test fail: device could not be initialized :"+dex);
        }

        // Shutdown
        lcmm = MMCore.getCore();
        try {
            lcmm.unloadAllDevices();
        } catch (Exception ex) {
            System.out.println("Devices not UNloaded");
            fail(ex);
        }
    }

    // ==========  XY stage device-specific tests =================== //

    @Test
    void test_device_operation() throws DeviceNotSetException {
        // Setup
        lcmm = MMCore.getCore();
        try {
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            xyStage.setOriginXY();
            xyStage.setRelativeXYPosition(100, 100);
            int x = (int) Math.round(lcmm.getXPosition(xyStage.getLabel()));
            int y = (int) Math.round(lcmm.getYPosition(xyStage.getLabel()));
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
            fail(ex);
        }
    }

    @Test
    void test_multiple_similar_device_creation() {
        // Setup
        lcmm = MMCore.getCore();
        try {
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
            xyStage2 = new MMXYStageDevice("stage2", "DemoCamera", "DXYStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            xyStage.setOriginXY();
            xyStage.setRelativeXYPosition(100, 100);
            xyStage2.setOriginXY();
            xyStage2.setRelativeXYPosition(50, 50);
            int x = (int) Math.round(lcmm.getXPosition(xyStage.getLabel()));
            int y = (int) Math.round(lcmm.getYPosition(xyStage.getLabel()));
            int x2 = (int) Math.round(lcmm.getXPosition(xyStage2.getLabel()));
            int y2 = (int) Math.round(lcmm.getYPosition(xyStage2.getLabel()));
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
            fail(ex);
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
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
            linStage = new MMLinearStageDevice("stage2", "DemoCamera", "DStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            xyStage.setOriginXY();
            xyStage.setRelativeXYPosition(100, 100);
            linStage.setOrigin();
            linStage.setPosition(50);
            int x = (int) Math.round(lcmm.getXPosition(xyStage.getLabel()));
            int y = (int) Math.round(lcmm.getYPosition(xyStage.getLabel()));
            int x2 = (int) Math.round(lcmm.getPosition(linStage.getLabel()));
            System.out.println(" "+x+" "+y+" "+x2);
            if(x!=100 || y!= 100 || x2!=50) {
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
            fail(ex);
        }
    }

}
