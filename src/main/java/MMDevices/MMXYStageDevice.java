package MMDevices;

import MMExceptions.LoadStageDeviceException;
import MMInterfaces.MMXYStageBase;

/**
 * Controls any MicroManager XYStage device.  Does not access the Core
 *
 * @author bryant.chhun
 */
public class MMXYStageDevice extends MMXYStageBase {

    private final String llabel;
    private final String lmoduleName;
    private final String ldeviceName;

    /**
     * For an incomplete list of available devices, see here: https://micro-manager.org/wiki/Device_Support
     *
     * @param plabel Unique identifier for this device.  Core throws exception if already exists
     * @param pmoduleName Device "Vendor" name.
     * @param pdeviceName Specific device included under "Vendor" module.
     * @throws LoadStageDeviceException: exception plus message
     */
    public MMXYStageDevice(String plabel, String pmoduleName, String pdeviceName) throws LoadStageDeviceException {
        super(plabel, pmoduleName, pdeviceName);

        llabel = plabel;
        lmoduleName = pmoduleName;
        ldeviceName = pdeviceName;

        try {
            this.loadDevice();
        } catch (LoadStageDeviceException lse) {
            throw new LoadStageDeviceException("device not loaded: "+lse);
        }
    }

    public String getLabel() {
        return llabel;
    }

    public String getModuleName() {
        return lmoduleName;
    }

    public String getDeviceName() {
        return ldeviceName;
    }
}
