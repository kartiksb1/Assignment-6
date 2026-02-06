# Expense Reporting API

## Overview

Expense Reporting API exposes REST endpoints to create and retrieve employee expenses.
The API is designed as a **stable contract** so frontend and backend teams can work independently.

---

## Technology Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* MySQL
* SpringDoc OpenAPI (Swagger)

---

## Base URL

```
http://localhost:8080
```

---

## API Versions

* **v1** → Stable contract
* **v2** → Future-proof version for contract evolution

Both versions coexist without breaking clients.

---

## API Groups (Swagger)

* **Expense Management**
* **Expense Management v2**

Swagger UI:

```
/swagger-ui.html
```

---

## API Endpoints

### 1️⃣ Add Expense (v1)

```
POST /api/v1/expenses
```

#### Request JSON

```json
{
  "title": "Lunch with client",
  "amount": 450.75,
  "currency": "INR",
  "category": "FOOD",
  "paymentMethod": "CARD",
  "expenseDate": "2026-02-05",
  "notes": "Client meeting"
}
```

#### Response JSON

```json
{
  "expenseId": "b7c1c7d5-9e5c-4d71-b0fd-1a92fbdc9e12",
  "status": "CREATED",
  "createdAt": "2026-02-06T10:15:30"
}
```

---

### 2️⃣ Get Expenses by Date Range (v1)

```
GET /api/v1/expenses
```

#### Query Parameters

| Name      | Type   | Description             |
| --------- | ------ | ----------------------- |
| startDate | date   | Start date (ISO)        |
| endDate   | date   | End date (ISO)          |
| page      | int    | Page number (default 0) |
| size      | int    | Page size (default 10)  |
| sort      | string | field,order             |

#### Example

```
/api/v1/expenses?startDate=2026-02-01&endDate=2026-02-06&page=0&size=10&sort=expenseDate,desc
```

#### Response JSON

```json
{
  "currentPage": 0,
  "pageSize": 10,
  "totalElements": 15,
  "totalPages": 2,
  "data": [
    {
      "expenseId": "b7c1c7d5-9e5c-4d71-b0fd-1a92fbdc9e12",
      "title": "Lunch with client",
      "amount": 450.75,
      "currency": "INR",
      "category": "FOOD",
      "paymentMethod": "CARD",
      "expenseDate": "2026-02-05"
    }
  ]
}
```

---

## Versioning Strategy

### v1

* Flat fields
* Stable contract
* No breaking changes

### v2

```
/api/v2/expenses
```

* Used for future enhancements
* Breaking changes introduced only in v2
* v1 remains backward compatible

---

## Pagination Rules

* Page numbering starts from `0`
* Default page size: `10`
* Sorting supported using `field,order`

---

## Contract Stability Guarantees

* Consistent field naming (camelCase)
* Fixed data types
* No undocumented fields
* No internal database IDs exposed
* New fields added only as optional
* Breaking changes handled via versioning

---

## Validation & Error Handling

* Bean Validation on request DTOs
* Validation errors return structured JSON
* Global exception handler implemented

---

## Swagger & Documentation

* All APIs grouped logically
* Request & response examples provided
* Swagger readable by non-developers
* JSON examples mandatory and included

---

## Database

* MySQL
* JPA/Hibernate
* Auto schema generation for development

---

## Assignment Compliance Checklist

✔ JSON contract defined
✔ Consistent field naming
✔ Correct numeric typing
✔ Pagination documented
✔ Swagger with examples
✔ Versioning demonstrated
✔ Contract stability explained
✔ Frontend/backend independent

---

## How to Run

1. Create database:

```sql
CREATE DATABASE expense_reporting;
```

2. Update credentials in `application.properties`
3. Run Spring Boot application
4. Open Swagger UI

---


