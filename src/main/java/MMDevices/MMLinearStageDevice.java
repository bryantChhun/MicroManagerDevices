package MMDevices;

import MMExceptions.LoadStageDeviceException;
import MMInterfaces.MMLinearStageBase;

public class MMLinearStageDevice extends MMLinearStageBase {

    private final String llabel;
    private final String lmoduleName;
    private final String ldeviceName;

    public MMLinearStageDevice(String plabel, String pmoduleName, String pdeviceName) throws LoadStageDeviceException {
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
