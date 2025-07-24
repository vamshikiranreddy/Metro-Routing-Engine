🚇 Bangalore Metro Route Finder – Java Swing Application
A fully functional Java desktop application that simulates the real-life Bangalore Namma Metro network using graph data structures and Dijkstra’s algorithm to help users plan the shortest route between metro stations. Built with Java Swing for an interactive GUI, it also includes fare estimation, travel time, and dynamic routing logic with realistic Bangalore Metro station data.

📌 Project Objective
To simulate Bangalore's Namma Metro system digitally by:

Helping users find optimal routes

Estimating travel time and fare

Representing real-world metro lines (Purple & Green lines)

This tool can be used for academic, transit planning, or portfolio demonstration purposes.

✨ Features
Feature	Description
🔍 Shortest Route Finder	Uses Dijkstra’s algorithm to find shortest paths
🧾 Fare Estimator	Estimates fare based on distance slabs
⏱️ Travel Time Calculator	Assumes average train speed to estimate time
🎨 User-Friendly GUI	Java Swing interface with dropdowns, icons, labels
🚉 Real Metro Station Data	Purple Line & Green Line with correct interchanges
🌈 Line-Based Color Codes	UI uses color tags to indicate line types
🔄 Dynamic Edge Creation	Easily extendable to new metro lines in the future
🛠️ Modular Codebase	Graph, Station, Heap separated for clarity



🧠 How It Works – Under The Hood
The project models the Bangalore Metro as a weighted graph, where:

Nodes = Stations

Edges = Routes (with distance as weight)

Dijkstra’s algorithm is used to compute the shortest path from the selected source to destination.

Fares and travel time are derived from the total distance.

Classes involved:

Graph_M.java: Metro network graph + Dijkstra’s logic

Station.java: Custom object with station info

Heap.java: Priority queue for Dijkstra’s algorithm

MetroAppSwingUI.java: Complete Swing-based UI interface

MainApp.java: Launches the application

🛠️ Technologies Used
Java 11+

Java Swing (AWT / Event Dispatch Thread)

Object-Oriented Design

Graph Algorithms

IDE: Eclipse / IntelliJ

📁 Directory Structure
bash
Copy
Edit
metroapp/
│
├── Graph_M.java         # Core graph logic & pathfinding
├── Station.java         # Station data structure
├── Heap.java            # Priority Queue used in Dijkstra
├── MetroAppSwingUI.java # GUI class using Java Swing
├── MainApp.java         # App launcher
└── README.md
Tested on Java 11+ (Eclipse IDE)


🚀 Usage Guide
Launch the application

Select the source and destination from dropdown menus

Click "Find Route"

App displays:

Shortest route

Travel time (based on average speed)

Fare (distance-based slab model)

🎯 Fare Estimation Logic
Distance (KM)	Fare (INR)
0–2 km	₹10
2–5 km	₹15
5–10 km	₹20
10–15 km	₹25
15+ km	₹30

💡 Possible Enhancements
🟡 Add Yellow, Pink, and future lines

📱 Convert to Android App (via Kotlin or JavaFX)

🌐 Web version with LeafletJS or Google Maps API

📡 Real-time train tracking integration

🙋‍♂️ Contributing
Want to contribute? Here’s how:

Fork the repo

Create a new branch

Make your changes (add new stations, improve GUI, etc.)

Submit a PR!

🧑‍💻 Developer
Vamshi S Hari Hara Kiran Reddy J
📧 vamshikr912@gmail.com
🎓 Vellore Institute of Technology, ECE
