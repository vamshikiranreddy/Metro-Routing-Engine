package MetroApp;

import java.util.*;

public class StationDatabase {
    private Map<String, Station> stationData;

    public StationDatabase() {
        stationData = new HashMap<>();
        loadStationData();
    }

    private void loadStationData() {
        stationData.put("MG Road", new Station("MG Road", "Purple"));
        stationData.put("Majestic", new Station("Majestic", "Purple/Green"));
        stationData.put("Indiranagar", new Station("Indiranagar", "Purple"));
        // Add more stations as needed
    }

    public Station getStation(String name) {
        return stationData.get(name);
    }

    public boolean contains(String name) {
        return stationData.containsKey(name);
    }

    public Set<String> getAllStations() {
        return stationData.keySet();
    }
}
