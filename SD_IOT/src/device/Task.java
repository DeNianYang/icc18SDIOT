package device;

import java.util.*;

import org.jgrapht.ext.*;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;

import social.Social;

public class Task implements Runnable {
	public Task() {
	}

	public void run() {
		final Random random = new Random();
		Targets targets;
		Sensors sensors;
		Requests requests;
		Set<Sensor> groups = new HashSet<>();

		// generating sensors and targets until hasSensorCover is satisfied
		do {
			System.out.println("Generating...");
			targets = new Targets(3);
			sensors = new Sensors(5, targets);
		} while (!Targets.hasSensorCover(targets));

		// generating user requests
		requests = new Requests(2);
		// randomly select requests that request this target
		// have to modify
		Targets.generateRandomRequests(targets, requests);

		// print requests
		for (Request request : requests.values()) {
			System.out.println(request);
			System.out.println(request.getLocations());
		}
		System.out.println(sensors);
		System.out.println(targets);

		// for all sensors, calculate each energy cost
		for (Sensor sensor : sensors.values()) {
			sensor.setCost(1);
		}

		// create a list of sensors
		// so that generator can use it to create a complete social graph
		Graph<Integer, DefaultEdge> socialGraph = Social.createIntGraph(new ArrayList<>(sensors.values()));
		System.out.println(socialGraph);

		// compute Dijkstra shortest path of social graph
		DijkstraShortestPath<Integer, DefaultEdge> socialDijkstra = new DijkstraShortestPath<>(socialGraph);

		// for each request, do ESRS
		for (Request request : requests.values()) {
			Sensor ESRSselected;
			// this request do not have any location
			if (request.getLocations().size() == 0) {
				continue;
			}
			// ESRS
			if ((ESRSselected = SetCover.ESRS(request.getLocations(), sensors, socialDijkstra)) != null) {
				// replace sensor group's coverage with virtual targets
				Sensors.replaceWithVirtualTargets(ESRSselected, request);
				// add this sensor group to groups
				groups.add(ESRSselected);
				System.out.println("Setcover:" + ESRSselected);
			} else {
				System.err.println("Set cover no solution");
				continue;
			}
		} // end for

		// generate virtual targets
		Requests.generateVirtualTargets(requests);
		// replace each's coverage with virtual targets
		Sensors.replaceWithVirtualTargets(sensors, requests);
		// union all virtual locations of each request
		Set<Target> virtualTargets = Requests.getAllVirtualTargets(requests);
		System.out.println("virtualTargets: " + virtualTargets);

		System.out.println("groups: " + groups);
		// -------------------------------------------Greedy---------------------------------------------------
		Set<Sensor> sensorsSelected;
		if ((sensorsSelected = SetCover.greedy(virtualTargets, sensors, groups)) != null) {
			for (Sensor s : sensorsSelected) {
				System.out.print(s + " ");
				System.out.println(s.getCoverage());
			}
		} else {
			System.err.println("Set cover no solution");
			System.exit(-1);
		}

	} // end run()

}
