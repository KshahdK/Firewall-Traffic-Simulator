**Firewall Traffic Simulator (Java OOP Project)**

**Project Overview**

The Firewall Traffic Simulator is a Java-based security application developed to simulate real-world network traffic monitoring and filtering. The system acts as a protective layer, analyzing incoming web traffic and making automated security decisions (Allow/Block) based on predefined rules.

**Key Features**

Automated Security Filtering: Automatically scans URLs and ports to block malicious activities. 

Duplicate Detection (Rate Limiting): Uses advanced logic to detect repeated access attempts from the same IP to the same URL. 

Data Persistence: Supports full File I/O operations to save and load traffic records from .txt files. 

Administrative Search: Enables quick record retrieval using Source IP addresses.

System Authentication: Features a secure login interface for authorized administrators. 

**Technical Implementation (OOP Concepts)**

This project serves as a comprehensive implementation of core Object-Oriented Programming principles:  

`Inheritance`: Implemented through a robust class hierarchy where WebTraffic extends the NetworkTraffic base class.

`Encapsulation`: All system data is protected using private attributes and accessed via public getters and setters. 

`Polymorphism`: Utilized method overriding to provide specialized data displays for different traffic types.  

`Safe Downcasting`: Implemented using the instanceof operator to safely process specialized objects within the system manager.

**Project Architecture**

The system is structured into five main components:

`NetworkTraffic.java`: The superclass defining general network properties.  

`WebTraffic.java`: The specialized subclass for web-specific attributes. 

`FirewallManager.java`: Handles the storage (Arrays), search logic, and security rules. 

`ActionStatus.java`: An Enumeration class for standardizing security actions. 

`Test.java`: The main interactive terminal interface for users.

**Team Members (Group 4)**

Layan Al-Harbi: Data Management & Core Architecture. 

Shahad Al-Qurashi: Security Logic & Advanced OOP Implementation.

Noran Malibari: System Integration & Data Persistence.

**How to Run**

1- Download the .java files.

2- Compile all files in your preferred Java IDE (e.g., Eclipse).

3- Run Test.java and log in with the admin credentials. 
