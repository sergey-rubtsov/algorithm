package my.algorithm.schedule.clinic;

import java.util.ArrayList;
import java.util.List;

public class RawData {
	
	private int numberOfDoctors;
	
	private int numberOfMinutes;
	
	private int[] serviceDuration;
	
	private int[] requestedServices;

	private List<Service> services = new ArrayList<>();

	public RawData(int numberOfDoctors, int numberOfMinutes,
                   int[] serviceDuration, int[] requestedServices) {
		this.numberOfDoctors = numberOfDoctors;
		this.numberOfMinutes = numberOfMinutes;
		this.serviceDuration = serviceDuration;
		this.requestedServices = requestedServices;
	}

	public int getNumberOfDoctors() {
		return numberOfDoctors;
	}
	
	public int getNumberOfServices() {
		return getServiceDuration().length;
	}

	public int getNumberOfMinutes() {
		return numberOfMinutes;
	}

	public int[] getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(int[] serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public int[] getRequestedServices() {
		return requestedServices;
	}

	public void setRequestedServices(int[] requestedServices) {
		this.requestedServices = requestedServices;
	}

	public void addServiceToList(int doctorId, int serviceId, int startTime, int duration) {
		services.add(new Service(doctorId, serviceId, startTime, duration));
	}

	public List<Service> getServices() {
		return services;
	}
}
