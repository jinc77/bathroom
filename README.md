# Bathroom Management System

This Java application simulates the management of bathroom facilities with stalls and urinals. It assigns users to available bathroom fixtures, maintains a queue for occupied fixtures (stalls/urinals), and tracks usage times for a wait time prediction.

## ⭐ Features
- Handles up to 10 stalls and 3 urinals by default.
- Assigns users to available bathroom fixtures based on usage count.
- Manages a queue for users waiting to use the bathroom.
- Tracks estimated wait times for queued users.
- Automatically frees up bathroom fixtures after specified usage times.
- Provides real-time status updates of all fixtures and the queue.

## 🚧 Requirements
- Java 8 or higher installed on your system.

## 🔍 Usage
1. Clone the repository or download the source files:
```bash
git clone https://github.com/jinc77/bathroom-app.git
cd bathroom-app
```

2. Compile the Java files:
```bash
javac *.java
```

3. Run the program:
```bash
java BathroomApp
```

## 💻 How It Works
- **User Input**: Enter your name and select your usage type (1 for urinals, 2 for stalls, 3 for other purposes).
- **Fixture Assignment**: The app directs you to an available fixture or adds you to the queue if all are occupied.
- **Timer Management**: Each user's usage is timed, and fixtures are vacated automatically when the timer ends.
- **Status Display**: The app continuously updates the status of all fixtures and the queue.

## 🌟 Key Components
### `Bathroom`
An abstract class representing a bathroom fixture (e.g., Stall, Urinal). It tracks the fixture's ID, occupancy status, usage count, and current user.

### `Stall` and `Urinal`
Concrete implementations of the `Bathroom` class representing specific types of fixtures.

### `User`
A class that represents a user with attributes for name, usage type, and usage time.

### `BathroomApp`
The main class managing fixtures, user interactions, and queues. It includes:
- A map of fixtures (`fixtures`).
- A queue for users (`waitList`).
- Timer-based management for usage durations.

### **Summary of Iterative Method Complexities**
| Method             | Time Complexity  | Space Complexity |
|--------------------|------------------|------------------|
| `findAvailableUse` | \( O(n) \)       | \( O(1) \)       |
| `addUser`          | \( O(1) \)       | \( O(1) \)       |
| `waitTimes`        | \( O(m) \)       | \( O(1) \)       |
| `displayStatus`    | \( O(n + m) \)   | \( O(1) \)       |
| `useBathroom`      | \( O(n) \)       | \( O(1) \)       |

## 💾 Example
### Sample User Interaction
1. Enter your name: `Alice`
2. Choose your usage type: `1` (urinal)
- Output: `Alice directed to Urinal 1 for ~2 minutes.`
3. If all fixtures are occupied:
- Output: `All fixtures are occupied. Alice has been added to the wait list.`

## 🧠 License
This project is licensed under the MIT License. See the LICENSE file for details.

## 📝 Contributing
Feel free to submit issues or pull requests! Contributions are welcome.

## ❤️ Acknowledgements
- Inspired by practical bathroom queue management challenges.
- Utilizes Java `Timer` and `Queue` classes for scheduling and queue management.
