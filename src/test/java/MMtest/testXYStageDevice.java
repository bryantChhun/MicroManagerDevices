package MMtest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import MMCore.MMCore;
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
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}
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
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}
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
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}
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
        } catch (Exception ex) {System.out.println("Devices not UNloaded");}

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
            xyStage = new MMXYStageDevice("stage1", "DemoCamera", "DXYStage");
        } catch (Exception ex) {
            fail(ex);
        }

        // Tests
        try {
            xyStage.setOriginXY();
            xyStage.setRelativeXYPosition(100, 100);
            int x = (int) lcmm.getXYStagePosition(xyStage.getLabel()).getX();
            int y = (int) lcmm.getXYStagePosition(xyStage.getLabel()).getY();
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

    }

    @Test
    void test_multiple_different_device_creation() {

    }

}
