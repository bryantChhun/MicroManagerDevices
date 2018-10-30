package MMDevices;

import MMExceptions.LoadStageDeviceException;
import MMInterfaces.MMGenericDeviceByPropertyBase;

/**
 * Controls any Micromanager device using manufacturer-defined properties  Does not access the Core
 *   See Manufacturer's documentation for specific properties.
 *  Example: Coherent cubes can be controlled ONLY with properties (not device specific calls in the Core)
 *          https://github.com/micro-manager/micro-manager/blob/master/DeviceAdapters/CoherentCube/CoherentCube.cpp
 *          Searching for "Property" shows the following defined:
 *
 *          g_ControllerName = "CoherentCube";
 *          g_Keyword_PowerSetpoint = "PowerSetpoint";
 *          g_Keyword_PowerReadback = "PowerReadback";
 *
 *
 * @author bryant.chhun
 */
public class MMGenericDeviceByProperty extends MMGenericDeviceByPropertyBase {

    private final String llabel;
    private final String lmoduleName;
    private final String ldeviceName;

    /**
     *
     * @param plabel Unique identifier for this device.  Core throws exception if already exists
     * @param pmoduleName Device "Vendor" name.
     * @param pdeviceName Specific device included under "Vendor" module.
     * @throws LoadStageDeviceException : exception plus message
     */

    public MMGenericDeviceByProperty(String plabel, String pmoduleName, String pdeviceName) throws LoadStageDeviceException {
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
