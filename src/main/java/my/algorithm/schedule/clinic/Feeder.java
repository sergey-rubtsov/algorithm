package my.algorithm.schedule.clinic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sergei.rubtcov on 3/27/2017.
 */
public class Feeder {

    private List<Deque<Service>> feeders;

    public void initFeeders(int[] requestedServices, List<Service> allServices) {
        this.feeders = getRequestedServicesInTimeOrder(requestedServices, allServices);
    }

    public Service getNextService() {
        for (Deque<Service> deque : feeders) {
            if (deque.isEmpty()) {
                continue;
            }
            return deque.pop();
        }
        return null;
    }

    public static List<Deque<Service>> getRequestedServicesInTimeOrder(int[] requestedServices, List<Service> allServices) {
        Collections.sort(allServices);
        HashMap<Integer, Deque<Service>> setOfServices = new HashMap<>();
        for (Service service : allServices) {
            if (setOfServices.containsKey(service.getId())) {
                setOfServices.get(service.getId()).addLast(service);
            } else {
                setOfServices.put(service.getId(), new ArrayDeque<>());
            }
        }
        List<Deque<Service>> result = new ArrayList<>();
        for (int service : requestedServices) {
            result.add(setOfServices.get(service));
        }
        return result;
    }

    /*
    *    2
    *    3
    *    11
    *    2 4 3
    *    0 2
    *    0 0 0
    *    * * *
    * */
    public RawData readFile(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = null;
        String line = null;
        RawData rawData = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(classLoader.getResource(file).getPath())));
            int numberOfDoctors = Integer.parseInt(reader.readLine());
            int numberOfServices = Integer.parseInt(reader.readLine());
            int numberOfMinutes = Integer.parseInt(reader.readLine());
            int[] serviceDuration = getArray(reader.readLine());
            Map<Integer, Integer> services = new HashMap<>();
            for (int i = 0; i < serviceDuration.length; i++) {
                services.put(i, serviceDuration[i]);
            }
            int[] requestedServices = getArray(reader.readLine());
            rawData = new RawData(numberOfDoctors, numberOfMinutes,
                    serviceDuration, requestedServices);
            while ((line = reader.readLine()) != null) {
                int[] service = getArray(line);
                rawData.addServiceToList(service[0], service[1], service[2], services.get(service[1]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error while reading.");
            System.exit(0);
        }
        return rawData;
    }

    private static int[] getArray(String string) {
        String[] split = string.split(" ");
        int[] result = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }

}
