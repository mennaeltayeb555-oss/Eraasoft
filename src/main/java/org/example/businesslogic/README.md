# API business logic tasks

My solution for the lec9 tasks (10 APIs). Each task has its own package with
controller, service, repository, model and dto.

I kept the data in memory (a map inside each repository) so the app starts
without Postgres. Every repository adds some test data when the app starts.

## How to run

Run `BusinessLogicApplication` from IntelliJ. The app starts on port 8080.

If 8080 is busy, add `--server.port=8081` to the program arguments.

## Packages

```
businesslogic
├── common        exceptions, error handler, in memory repository
├── appointment   task 1
├── discount      task 2
├── banktransfer  task 3
├── library       task 4
├── restaurant    task 5
├── leave         task 6
├── ecommerce     task 7
├── parking       task 8
├── enrollment    task 9
└── delivery      task 10
```

Any broken rule returns 400 with a message, and a missing id returns 404.

## Endpoints

### 1. Appointment booking

| Method | URL | Body |
|---|---|---|
| GET | /api/appointments/doctors | |
| GET | /api/appointments | |
| POST | /api/appointments | `{"doctorId":1,"patientId":1,"serviceType":"CHECKUP","startTime":"2026-11-01T10:00:00"}` |
| PUT | /api/appointments/{id}/cancel | |

Service types and duration: CONSULTATION 30 min, CHECKUP 45, DENTAL_CLEANING 60, SURGERY 120.
Doctor 3 is not available. A patient who cancels a slot can't book the same slot again.

### 2. Product discount

| Method | URL | Body |
|---|---|---|
| GET | /api/prices/products | |
| GET | /api/prices/codes | |
| POST | /api/prices/calculate | `{"customerId":2,"items":[{"productId":3,"quantity":2}],"discountCodes":["SAVE10"]}` |

Discount = category discount + codes + 5% if the customer is VIP, max 30%.
The iPhone (product 2) has no discount. Shipping is free when the total after discount is 2000 or more.
SAVE10 and WELCOME15 can't be used together, and neither can WELCOME15 and BLACKFRIDAY.

### 3. Bank transfer

| Method | URL | Body |
|---|---|---|
| GET | /api/bank/accounts | |
| GET | /api/bank/transfers | |
| POST | /api/bank/transfers | `{"fromAccountId":1,"toAccountId":2,"amount":6000,"verificationCode":"1234"}` |

Daily limits: SAVINGS 10000, CURRENT 50000, BUSINESS 200000.
Transfers above 5000 need the code `1234`. Account 4 is not active.
A failed transfer is saved with status FAILED and the reason, and the balances don't change.

### 4. Library borrowing

| Method | URL | Body |
|---|---|---|
| GET | /api/library/books | |
| GET | /api/library/members | |
| GET | /api/library/loans | |
| POST | /api/library/loans | `{"memberId":1,"bookId":1}` |
| PUT | /api/library/loans/{id}/return | |

Normal members keep a book 14 days and premium members 30 days.
The late fine is 5 per day for the first 7 days, then 10 per day.
Member 3 already has an overdue book, so you can test the "overdue" rule and the fine with loan 1.

### 5. Restaurant reservation

| Method | URL | Body |
|---|---|---|
| GET | /api/reservations/tables | |
| GET | /api/reservations | |
| POST | /api/reservations | `{"tableId":2,"customerId":1,"guests":3,"startTime":"2026-11-01T19:00:00"}` |
| PUT | /api/reservations/{id}/confirm | |
| PUT | /api/reservations/{id}/cancel | |

The restaurant is open 12:00 to 23:00 and each reservation takes 2 hours.
New reservations start as PENDING. A scheduled job runs every minute and marks a
reservation EXPIRED if nobody confirms it within 15 minutes.

### 6. Employee leave

| Method | URL | Body |
|---|---|---|
| GET | /api/leaves/employees | |
| GET | /api/leaves | |
| POST | /api/leaves | `{"employeeId":1,"startDate":"2026-11-01","endDate":"2026-11-05","reason":"travel"}` |
| PUT | /api/leaves/{id}/approve | |
| PUT | /api/leaves/{id}/reject | |
| PUT | /api/leaves/{id}/cancel | |

Yearly days: FULL_TIME 21, PART_TIME 10, MANAGER 30. Friday and Saturday are the weekend.
The days come out of the balance when the request is submitted, and they go back on reject or cancel.

### 7. E-commerce order

| Method | URL | Body |
|---|---|---|
| GET | /api/orders/products | |
| GET | /api/orders | |
| POST | /api/orders | `{"customerId":1,"items":[{"productId":2,"quantity":3}]}` |
| PUT | /api/orders/{id} | `{"items":[{"productId":1,"quantity":1}]}` |
| PUT | /api/orders/{id}/confirm | |
| PUT | /api/orders/{id}/pay | |

Shipping is free from 5000, otherwise 60. "Old Phone" is not active.
A customer with an unpaid order has to pay it before making a new one.

### 8. Parking

| Method | URL | Body |
|---|---|---|
| GET | /api/parking/tickets | |
| GET | /api/parking/status | |
| POST | /api/parking/enter | `{"plateNumber":"ABC-999","vehicleType":"CAR","vip":false}` |
| PUT | /api/parking/tickets/{id}/exit | |
| PUT | /api/parking/lost-ticket/{plateNumber} | |

Prices (first hour / every extra hour): MOTORCYCLE 10/5, CAR 20/10, TRUCK 50/30.
VIP gets 20% off. A lost ticket costs the normal fee plus 200. Capacity is 20.
Car ABC-123 entered 3 hours ago, so ticket 1 shows the extra hours price.

### 9. Course enrollment

| Method | URL | Body |
|---|---|---|
| GET | /api/enrollments/courses | |
| GET | /api/enrollments/students | |
| GET | /api/enrollments | |
| POST | /api/enrollments | `{"studentId":4,"courseId":3}` |
| PUT | /api/enrollments/{id}/drop | |

CS201 needs CS101, and CS301 needs CS201. Student 3 failed CS201, and the
drop deadline for CS150 has already passed.

### 10. Delivery order

| Method | URL | Body |
|---|---|---|
| GET | /api/deliveries/drivers | |
| GET | /api/deliveries | |
| POST | /api/deliveries | `{"customerId":1,"pickupAddress":"Nasr City","dropoffAddress":"Maadi","distanceKm":12,"weightKg":5}` |
| PUT | /api/deliveries/{id}/assign/{driverId} | |
| PUT | /api/deliveries/{id}/status | `{"status":"PICKED_UP"}` |
| PUT | /api/deliveries/{id}/cancel | |

Fee = 20 + 3 per km + 1.5 per kg. Max weight: BIKE 10, CAR 100, VAN 500, TRUCK 2000 kg.
Orders longer than 50 km need a TRUCK.
Status goes CREATED, ASSIGNED, PICKED_UP, IN_TRANSIT, DELIVERED, and any other jump is rejected.
