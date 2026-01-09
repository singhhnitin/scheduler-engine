package com.scheduler.engine;

import com.scheduler.model.Appointment;
import com.scheduler.service.Resource;

import java.util.*;

public class Scheduler {

    public static class ScheduleResult {
        public Map<String, String> assigned;
        public List<String> rejected;

        public ScheduleResult() {
            assigned = new HashMap<>();
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
            return a.getEndTime() - b.getEndTime();
        });

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
                result.assigned.put(appt.getId(), res.getId());
                res.setNextAvailableTime(end);
            } else {
                result.rejected.add(appt.getId());
            }

            resourceQueue.offer(res);
        }

        return result;
    }
}
