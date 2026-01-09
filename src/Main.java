import model.Appointment;
import service.Resource;
import service.SchedulingService;
import engine.Scheduler;

public class Main {

    public static void main(String[] args) {

        SchedulingService service = new SchedulingService();

        // Resources
        service.addResource(new Resource("R1", 9, 12));
        service.addResource(new Resource("R2", 10, 12));

        // Appointments
        service.addAppointment(new Appointment("A1", 10, 12, 1, 2));
        service.addAppointment(new Appointment("A2", 10, 11, 2, 1));
        service.addAppointment(new Appointment("A3", 11, 14, 1, 3));

        System.out.println("INITIAL SCHEDULE:");
        Scheduler.ScheduleResult result1 = service.generateSchedule();
        print(result1);

        System.out.println("\nCANCEL A1\n");
        service.cancelAppointment("A1");

        System.out.println("UPDATED SCHEDULE:");
        Scheduler.ScheduleResult result2 = service.generateSchedule();
        print(result2);
    }

    private static void print(Scheduler.ScheduleResult result) {
        System.out.println("ASSIGNED:");
        result.assigned.forEach((a, r) ->
                System.out.println(a + " -> " + r));

        System.out.println("REJECTED:");
        result.rejected.forEach(System.out::println);
    }
}
