package my.algorithm.schedule.clinic;

public class Service implements Comparable<Service> {

	private final int id;

	private final int startTime;

	private final int endTime;

	private final int duration;

	private final int doctorId;

	public Service(int doctorId, int id, int startTime, int duration) {
		this.doctorId = doctorId;
		this.id = id;
		this.startTime = startTime;
		this.duration = duration;
		this.endTime = this.startTime + this.duration;
	}

	public int getDoctor() {
		return doctorId;
	}

	public int getId() {
		return id;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getDuration() {
		return duration;
	}

	public int getEndTime() {
		return endTime;
	}

	@Override
	public int compareTo(Service other) {
		if (other.getStartTime() < getStartTime()) {
			return 1;
		} else if (other.getStartTime() > getStartTime()) {
			return -1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Service service = (Service) o;

		if (doctorId != service.doctorId) return false;
		if (id != service.id) return false;
		if (startTime != service.startTime) return false;
		if (endTime != service.endTime) return false;
		return duration == service.duration;
	}

	@Override
	public int hashCode() {
		int result = doctorId;
		result = 31 * result + id;
		result = 31 * result + startTime;
		result = 31 * result + endTime;
		result = 31 * result + duration;
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append(id);
		sb.append(",").append(startTime);
		sb.append(",").append(endTime);
		sb.append(",").append(duration);
		sb.append(",").append(doctorId);
		sb.append('}');
		return sb.toString();
	}
}
