package MMCore;

import MMExceptions.DeviceNotLoadedException;

import java.util.HashSet;
import java.util.Set;

public class DeviceLabelsAssigned {
    private static Set<String> loaded_devices;

    public static boolean addDevice(String device) {
        if (loaded_devices == null) {
            loaded_devices = new HashSet<>();
        }
        return loaded_devices.add(device);
    }

    public static boolean removeDevice(String device) throws DeviceNotLoadedException {
        if (loaded_devices == null) {
            throw new DeviceNotLoadedException("Attempting to remove before instantiating Device Monitor");
        }
        return loaded_devices.remove(device);
    }

    public static boolean containsDevice(String device) throws DeviceNotLoadedException {
        if (loaded_devices == null) {
            throw new DeviceNotLoadedException("Attempting to check contain before instantiating Device Monitor");
        }
        return loaded_devices.contains(device);
    }

    public static Set<String> getLoadedDevices() {
        if (loaded_devices == null) {
            loaded_devices = new HashSet<>();
        }
        return loaded_devices;
    }

}