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
