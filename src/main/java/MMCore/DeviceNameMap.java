package MMCore;

import MMExceptions.DeviceNotLoadedException;

import java.util.HashMap;

public class DeviceNameMap {
    private static HashMap<String, String> loaded_devices;

    public static String addLabelForDevice(String label, String device) {
        if (loaded_devices == null) {
            loaded_devices = new HashMap<> ();
        }
        return loaded_devices.put(label, device);
    }

    public static String removeDevice(String label) throws DeviceNotLoadedException {
        if (loaded_devices == null) {
            throw new DeviceNotLoadedException("Attempting to remove before instantiating Device Monitor");
        }
        return loaded_devices.remove(label);
    }

    public static boolean containsDevice(String label) throws DeviceNotLoadedException {
        if (loaded_devices == null) {
            throw new DeviceNotLoadedException("Attempting to check contain before instantiating Device Monitor");
        }
        return loaded_devices.containsKey(label);
    }

    public static HashMap<String, String> getLoadedDevices() {
        if (loaded_devices == null) {
            loaded_devices = new HashMap<> ();
        }
        return loaded_devices;
    }

}