package device;

import java.util.*;

public class Sensor {
	public static final double MAX_RADIUS = 100;
	public static final double MAX_DIS_BETWEEN = 12; // maximum distance between

	private int id; // unique ID
	private static int count = 0;
	private double x; // x coordinate
	private double y; // y coordinate
	private double cost; // energy cost
	private double size; // flow size
	private int association; // associate with a BS
	private Set<Target> coverage; // targets covered by the sensor

	private final Random radius = new Random();
	private final Random angle = new Random();

	public Sensor() {
	}

	// for VirtualSensor
	public Sensor(Sensor sensor) {
		this.setID();
		this.coverage = new HashSet<>(sensor.getCoverage());
		this.cost = 0;
		this.size = 1;
	}

	public Sensor(Targets targets) {
		this.id = ++count;
		// initialize x and y coordinate
		double r = Sensor.MAX_RADIUS * radius.nextDouble();
		double a = 2 * Math.PI * angle.nextDouble();
		this.x = r * Math.cos(a);
		this.y = r * Math.sin(a);

		this.coverage = new HashSet<>();
		this.cost = -1; // initialize cost
		this.size = 1;
		this.association = -1; // initialize association

		for (Target target : targets.values()) {
			// for each target
			if (calculateDistance(this.x, this.y, target.getX(), target.getY()) <= Sensor.MAX_DIS_BETWEEN) {
				coverage.add(target);
				target.addCoveredBy(this);
			}
		}
	}

	private synchronized void setID() {
		this.id = ++count;
	}

	public int getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getAssociation() {
		return association;
	}

	public void setAssociation(int association) {
		this.association = association;
	}

	// get targets covered by the sensor
	public Set<Target> getCoverage() {
		return Collections.unmodifiableSet(coverage);
	}

	public double calculateDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((x1 - x2), 2.0) + Math.pow((y1 - y2), 2.0));
	}

	// whether s1 and s2 has relation, depending on distance
	public boolean hasRelation(Sensor s1, Sensor s2) {
		if (calculateDistance(s1.getX(), s1.getY(), s2.getX(), s2.getY()) <= Sensor.MAX_DIS_BETWEEN && !s1.equals(s2)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Sensor))
			return false;
		Sensor other = (Sensor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Sensor [id=%s, coverage:%s]", id, coverage.size());
	}
}
