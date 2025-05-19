<p align="center">
  <img src="/lift.png" alt="" />
</p>

## Smart Lift Simulator 🚀

A Java-based **Smart Elevator System Simulator** with graphical user interface (GUI), designed to demonstrate real-time lift operation, floor navigation, and smart control logic. This simulation helps understand how elevator systems can be programmed with efficiency, safety, and real-time responsiveness in mind.

## 🛠 Features

- 🏢 Simulates lift operations inside a building.
- 🎮 Interactive GUI for users to request and control lifts.
- 🧠 Smart decision-making algorithm to move lift to correct floors.
- 🔧 Separate classes for Lift, Building, and GUI to ensure modularity and OOP design.
- 🧪 Test class for functionality testing and debugging.

## 📁 Project Structure

Lift-Game-main/
├── SmartLiftSimulatorGUI.java
├── LiftGameGUI.java
├── Lift.java
├── Building.java
├── TestLift.java
└── README.md

### 📦 Compilation & Execution

```bash
//To compile all java files:
javac *.java

//To run the simulator:
java SmartLiftSimulatorGUI

//To run test logic without GUI:
java TestLift
