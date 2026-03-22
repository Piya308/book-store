project step-by-step
1. learned monolithic architecture and issues like tight coupling and scaling problems
2. understood microservices architecture and how it solves monolith limitations
3. learned distributed system concepts and splitting services by responsibility
4. understood api gateway and sync (rest) vs async (messaging) communication
5. designed overall architecture with catalog and order services
6. initialized git repository and followed proper git workflow (commits, branching)
7. created project setup for catalog service
8. implemented product model and basic api endpoints
9. added business logic to handle catalog operations
10. connected catalog service with database (postgres)
11. wrote unit tests to validate catalog functionality
12. containerized catalog service using docker
13. created docker image for running application
14. setup infrastructure services like postgres and rabbitmq using docker
15. created infra.yml for managing infrastructure containers
16. created apps.yml for running microservices containers
17. created taskfile to automate development tasks (build, run, restart)
18. used taskfile to simplify and speed up local development workflow
19. ran and verified complete setup locally using docker-compose
20. understood need for async communication between services
21. setup rabbitmq message broker using docker
22. configured exchanges and queues for message flow
23. tested producer and consumer message communication
24. created new project for order service
25. implemented order api and core business logic
26. integrated order service with rabbitmq for communication
27. published events from order service to message broker
28. consumed events in other services for processing
29. wrote test cases for order controller
30. verified end-to-end workflow between catalog and order services

### Docker Testcontainers Failing
**Issue:** Integration tests failed even though Docker images were running. APIs worked fine.
**Cause:** Testcontainers required a **minimum Docker API version**; mismatch prevented proper container connections during tests.
**Fix:**
* Add `"min-api-version": "1.24"` in Docker Engine settings
**Tip:** Always ensure Docker API version meets Testcontainers requirements for smooth test execution.


### RabbitMQ Queues/Exchanges Not Created at Application Startup
**Issue:** Queues and exchanges weren’t auto-created on app startup. APIs worked, but messaging failed.
**Cause:** Spring Boot’s RabbitMQ auto-declaration is **lazy** — queues/exchanges are created only when a listener or template interacts with the broker.
**Fix:**
* Start a listener or send a test message to trigger creation
* Or use `RabbitAdmin` for **forceful, immediate declaration**
