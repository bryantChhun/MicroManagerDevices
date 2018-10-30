package MMCore;

import mmcorej.CMMCore;

/**
 * Singleton that returns CMMCore object from the MM-Java Wrapper
 * constructor is package private
 *
 *
 * MicroManager supports two types of device calls:
 * 1) Direct, device-specific calls to the C++ library via CMMCore
 *      Ex: cmm.set"property"("label", value);
 *          cmm.setPosition("Z", 100.0);
 *          cmm.setExposure("Camera", 55.0);
 *
 * 2) Property calls using String descriptors.
 *      Ex: cmm.setProperty("label", "property", "value");
 *          cmm.setProperty("Z", "Position", "100.0");
 *          cmm.setProperty("Camera", "Exposure", "55.0");
 *
 * While Property calls sound most flexible when we have multiple devices of the same type,
 *   device properties are not standardized and would need to be searched for every vendor.
 *
 * We will use device-specific calls, which can take label names as identifiers.
 *   These calls are standardized in the MMDevice.h abstract class.
 *   Only caveat here is that ALL devices must have unique names (even across types)
 *
 * This class is for initialization and interfacing.
 *
 * @author bryant.chhun
 */
public class MMCore {

    private static CMMCore Core;

    public static CMMCore getCore(){

        if(Core == null){
            Core = new CMMCore();
        }

        return Core;
    }
}
