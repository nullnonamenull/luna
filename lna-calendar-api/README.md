# API Endpoints Overview

| **HTTP Method** | **Endpoint**                     | **Description**                                           |
|-----------------|----------------------------------|-----------------------------------------------------------|
| **POST**        | `/api/calendar/events`           | Create a new event.                                       |
| **PUT**         | `/api/calendar/events/{id}`      | Update an existing event by ID.                           |
| **DELETE**      | `/api/calendar/events/{id}`      | Delete an event by ID.                                    |
| **GET**         | `/api/calendar/events/{id}`      | Retrieve details of a specific event.                     |
| **GET**         | `/api/calendar/events`           | List all events, optionally filtered by user or criteria. |
| **POST**        | `/api/calendar/events/conflicts` | Check for scheduling conflicts.                           |
| **GET**         | `/api/calendar/events/upcoming`  | Get upcoming events, optionally for a user.               |
