# Optimized Appointment & Resource Scheduling Engine

A backend-driven decision-support system that allocates time slots and shared resources to appointment requests while minimizing conflicts and maximizing utilization.

Live API: https://scheduler-engine.onrender.com

---

## 🚀 Problem Statement

Manual scheduling leads to:
- Overlapping appointments
- Underutilized or overbooked resources
- Priority violations
- Long waiting times

This engine **automates scheduling decisions** using optimization logic and provides **analytics** to support operational planning.

---

## 🧠 What This Project Does

- Assigns appointments to resources without conflicts
- Respects:
  - Time windows
  - Duration
  - Priority
  - Resource availability
- Re-optimizes schedules dynamically
- Produces analytics such as:
  - Assigned vs rejected count
  - Per-resource utilization (%)

---

## ⚙️ Core Features

### Scheduling
- Priority-based appointment ordering
- Greedy heuristic with min-heap (priority queue)
- Conflict-free assignment

### Analytics (Decision Support)
- Total appointments
- Assigned / rejected count
- Resource utilization percentage

### Architecture
- Stateless optimization engine
- REST API (Spring Boot)
- Database-backed persistence
- Frontend UI for interaction

---

## 🧩 Tech Stack

**Backend**
- Java 17
- Spring Boot
- REST APIs
- JPA (Hibernate)

**Algorithms & DSA**
- Greedy scheduling
- Priority Queue (Heap)
- Sorting with custom comparators

**Deployment**
- Docker
- Render Cloud

---

## 🧠 Data Structures Used (Interview Focus)

| Component | DSA Used |
|--------|---------|
Appointment ordering | Sorting + Comparator |
Resource selection | Min Heap (PriorityQueue) |
Schedule storage | HashMap |
Rejected tracking | ArrayList |

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|-----|--------|-------------|
POST | `/schedule/resource` | Add resource |
POST | `/schedule/appointment` | Add appointment |
GET | `/schedule` | Generate optimized schedule |
DELETE | `/schedule/appointment/{id}` | Cancel appointment |

---

## 🧪 Example Output

```json
{
  "assigned": {
    "A1": "R1",
    "A2": "R2"
  },
  "rejected": ["A3"],
  "totalAppointments": 3,
  "assignedCount": 2,
  "rejectedCount": 1,
  "resourceUtilization": {
    "R1": 60,
    "R2": 40
  }
}
