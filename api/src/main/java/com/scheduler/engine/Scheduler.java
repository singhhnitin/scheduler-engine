package com.scheduler.engine;

import com.scheduler.model.Appointment;
import com.scheduler.service.Resource;

import java.util.*;

public class Scheduler {

    public static class ScheduleResult {
        public Map<String, String> assigned;
        public Map<String, Integer> waitTimes;
        public List<String> rejected;

        public ScheduleResult() {
            assigned = new HashMap<>();
            waitTimes = new HashMap<>();
            rejected = new ArrayList<>();
        }
    }

    public static ScheduleResult scheduleAppointments(
            List<Appointment> appointments,
            List<Resource> resources
    ) {

        appointments.sort((a, b) -> {
    if (b.getPriority() != a.getPriority()) {
        return b.getPriority() - a.getPriority();
    }
    return a.getArrivalTime() - b.getArrivalTime();
});


        // Min-heap of resources by next available time
        PriorityQueue<Resource> resourceQueue =
                new PriorityQueue<>(Comparator.comparingInt(Resource::getNextAvailableTime));

        resourceQueue.addAll(resources);

        ScheduleResult result = new ScheduleResult();

        for (Appointment appt : appointments) {

            Resource res = resourceQueue.poll();

            if (res == null) {
                result.rejected.add(appt.getId());
                continue;
            }

            int start = Math.max(appt.getStartTime(), res.getNextAvailableTime());
            int end = start + appt.getDuration();

            if (end <= appt.getEndTime() && end <= res.getAvailableTo()) {

                // assign resource
                result.assigned.put(appt.getId(), res.getId());

                // STEP 11.2: calculate estimated wait time
                int waitTime = Math.max(0, start - appt.getStartTime());
                result.waitTimes.put(appt.getId(), waitTime);

                // update resource availability
                res.setNextAvailableTime(end);

            } else {
                result.rejected.add(appt.getId());
            }

            // CRITICAL: put resource back into heap
            resourceQueue.offer(res);
        }

        return result;
    }
}
