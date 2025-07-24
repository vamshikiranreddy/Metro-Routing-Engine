# Metro-Routing-Engine
Designed and developed a Java-based console application that simulates the Bangalore Metro Rail Network using advanced graph theory concepts. The system models metro stations as nodes and interconnecting paths as weighted edges within a custom-built adjacency map. Integrated a highly efficient implementation of Dijkstra’s algorithm using a Min-Heap PriorityQueue to compute the shortest route, travel distance, and fare estimation between any two user-selected metro stations.

Implemented Breadth-First Search (BFS) and Depth-First Search (DFS) to support network traversal, cycle detection, and connectivity analysis. The underlying graph was dynamically constructed using optimized HashMap and ArrayList data structures to ensure constant-time access and scalable performance.

Emphasized modular design and object-oriented programming principles, organizing the system into reusable classes like Graph, Station, RouteFinder, and InputHandler. The system handles user inputs with validations, error-handling for invalid stations, and provides intuitive output showing the shortest path step-by-step, total distance in kilometers, and fare in INR.
