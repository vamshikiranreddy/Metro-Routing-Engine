package MetroApp;

import java.util.*;

public class Graph_M {
	private Map<String, List<Neighbor>> adjList;
	private Map<String, String> lineColorMap;

	public Graph_M() {
		adjList = new HashMap<>();
		lineColorMap = new HashMap<>();

		// Purple Line
		addPurple("Kengeri", "Kengeri Bus Terminal", 1.5);
		addPurple("Kengeri Bus Terminal", "Pattanagere", 1.3);
		addPurple("Pattanagere", "Jnana Bharathi", 1.2);
		addPurple("Jnana Bharathi", "Rajarajeshwari Nagar", 1.1);
		addPurple("Rajarajeshwari Nagar", "Vijayanagar", 2.0);
		addPurple("Vijayanagar", "Hosahalli", 1.2);
		addPurple("Hosahalli", "Magadi Road", 1.3);
		addPurple("Magadi Road", "Majestic", 1.5);
		addPurple("Majestic", "Cubbon Park", 1.2);
		addPurple("Cubbon Park", "M.G. Road", 1.1);
		addPurple("M.G. Road", "Trinity", 1.0);
		addPurple("Trinity", "Halasuru", 1.3);
		addPurple("Halasuru", "Indiranagar", 1.2);
		addPurple("Indiranagar", "Swami Vivekananda Road", 1.4);
		addPurple("Swami Vivekananda Road", "Baiyappanahalli", 1.6);

		// Green Line
		addGreen("Nagasandra", "Dasarahalli", 1.5);
		addGreen("Dasarahalli", "Jalahalli", 1.4);
		addGreen("Jalahalli", "Peenya Industry", 1.3);
		addGreen("Peenya Industry", "Peenya", 1.2);
		addGreen("Peenya", "Yeshwanthpur", 1.5);
		addGreen("Yeshwanthpur", "Sandal Soap Factory", 1.3);
		addGreen("Sandal Soap Factory", "Mahalakshmi", 1.2);
		addGreen("Mahalakshmi", "Rajajinagar", 1.0);
		addGreen("Rajajinagar", "Kuvempu Road", 1.1);
		addGreen("Kuvempu Road", "Srirampura", 1.3);
		addGreen("Srirampura", "Mantri Square", 1.2);
		addGreen("Mantri Square", "Majestic", 1.0);
		addGreen("Majestic", "Chickpet", 1.4);
		addGreen("Chickpet", "KR Market", 1.2);
		addGreen("KR Market", "National College", 1.3);
		addGreen("National College", "Lalbagh", 1.5);
		addGreen("Lalbagh", "South End Circle", 1.3);
		addGreen("South End Circle", "Rashtreeya Vidyalaya Road", 1.1);
		addGreen("Rashtreeya Vidyalaya Road", "Banashankari", 1.4);
		addGreen("Banashankari", "J.P. Nagar", 1.5);
		addGreen("J.P. Nagar", "Yelachenahalli", 1.3);
	}

	private void addPurple(String from, String to, double d) {
		addStation(from, "Purple");
		addStation(to, "Purple");
		addEdge(from, to, d);
	}

	private void addGreen(String from, String to, double d) {
		addStation(from, "Green");
		addStation(to, "Green");
		addEdge(from, to, d);
	}

	private void addStation(String station, String lineColor) {
		adjList.putIfAbsent(station, new ArrayList<>());
		lineColorMap.putIfAbsent(station, lineColor);
	}

	private void addEdge(String from, String to, double distance) {
		adjList.get(from).add(new Neighbor(to, distance));
		adjList.get(to).add(new Neighbor(from, distance)); // bidirectional
	}

	public List<String> getShortestPath(String source, String destination) {
		Map<String, Double> dist = new HashMap<>();
		Map<String, String> prev = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));

		for (String station : adjList.keySet()) {
			dist.put(station, Double.MAX_VALUE);
			prev.put(station, null);
		}

		dist.put(source, 0.0);
		pq.add(new Node(source, 0.0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			String currStation = current.station;

			if (currStation.equals(destination))
				break;

			for (Neighbor neighbor : adjList.get(currStation)) {
				double newDist = dist.get(currStation) + neighbor.distance;
				if (newDist < dist.get(neighbor.name)) {
					dist.put(neighbor.name, newDist);
					prev.put(neighbor.name, currStation);
					pq.add(new Node(neighbor.name, newDist));
				}
			}
		}

		List<String> path = new ArrayList<>();
		for (String at = destination; at != null; at = prev.get(at))
			path.add(at);
		Collections.reverse(path);
		return path;
	}

	public double calculateFare(List<String> path) {
		double totalDistance = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			for (Neighbor neighbor : adjList.get(path.get(i))) {
				if (neighbor.name.equals(path.get(i + 1))) {
					totalDistance += neighbor.distance;
					break;
				}
			}
		}
		return Math.max(10, totalDistance * 2); // Minimum fare ₹10, ₹2/km
	}

	// Estimates total travel time based on distance between stations
	// Assumes average of 1.5 minutes per station
	public double estimateTravelTime(List<String> path) {
		if (path == null || path.size() < 2)
			return 0.0;

		// Assuming each leg between stations takes about 1.5 minutes
		double timePerHop = 1.5;
		return (path.size() - 1) * timePerHop;
	}

	public Set<String> getAllStations() {
		return adjList.keySet();
	}

	public String getLineColor(String station) {
		return lineColorMap.getOrDefault(station, "Gray");
	}

	private static class Neighbor {
		String name;
		double distance;

		public Neighbor(String name, double d) {
			this.name = name;
			this.distance = d;
		}
	}

	private static class Node {
		String station;
		double distance;

		public Node(String station, double distance) {
			this.station = station;
			this.distance = distance;
		}
	}
}
