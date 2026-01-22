package engine;

import model.Appointment;
import service.Resource;

import java.util.*;

public class Scheduler {

    public static class ScheduleResult {

        // Core results
        public Map<String, String> assigned;
        public List<String> rejected;

        // Analytics (Stage 14)
        public int totalAppointments;
        public int assignedCount;
        public int rejectedCount;
        public Map<String, Integer> resourceUtilization;

        public ScheduleResult() {
            assigned = new HashMap<>();
            rejected = new ArrayList<>();
            resourceUtilization = new HashMap<>();
        }
    }

    public static ScheduleResult scheduleAppointments(
            List<Appointment> appointments,
            List<Resource> resources
    ) {

        // Sort appointments: higher priority first, earlier deadline first
        appointments.sort((a, b) -> {
            if (b.getPriority() != a.getPriority()) {
                return b.getPriority() - a.getPriority();
            }
            return a.getEndTime() - b.getEndTime();
        });

        // Min-heap based on next available time
        PriorityQueue<Resource> resourceQueue =
                new PriorityQueue<>(Comparator.comparingInt(Resource::getNextAvailableTime));

        resourceQueue.addAll(resources);

        ScheduleResult result = new ScheduleResult();
        result.totalAppointments = appointments.size();

        for (Appointment appt : appointments) {

            Resource res = resourceQueue.poll();

            if (res == null) {
                result.rejected.add(appt.getId());
                result.rejectedCount++;
                continue;
            }

            int start = Math.max(appt.getStartTime(), res.getNextAvailableTime());
            int end = start + appt.getDuration();

            if (end <= appt.getEndTime() && end <= res.getAvailableTo()) {

                // Assignment successful
                result.assigned.put(appt.getId(), res.getId());
                result.assignedCount++;

                res.setNextAvailableTime(end);

            } else {
                // Cannot fit appointment
                result.rejected.add(appt.getId());
                result.rejectedCount++;
            }

            resourceQueue.offer(res);
        }

        // Calculate resource utilization (%)
        for (Resource r : resources) {

            int usedTime = r.getNextAvailableTime() - r.getAvailableFrom();
            int totalTime = r.getAvailableTo() - r.getAvailableFrom();

            int utilization = totalTime > 0
                    ? (usedTime * 100) / totalTime
                    : 0;

            result.resourceUtilization.put(r.getId(), utilization);
        }

        return result;
    }
}
