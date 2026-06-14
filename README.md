# SmartPrep: AI-Enabled Intelligent Learning System for Personalized Education

SmartPrep is an advanced, AI-driven learning and exam-intelligence platform designed to optimize study workflows. The system automatically processes uploaded study materials, extracts core concepts, schedules study plans using spaced repetition, tracks cognitive load/fatigue, predicts exam scores, and hosts an interactive context-aware tutor.

---

## 🏗️ System Architecture

SmartPrep is built on a **three-tier microservice architecture** for technological flexibility, separation of concerns, and independent scalability:

```
┌────────────────────────────────────────────────────────────┐
│                       CLIENT LAYER                         │
│  React.js (v18+) Single-Page Web App (Port 3000)           │
└─────────────────────────────┬──────────────────────────────┘
                              │ HTTP / REST APIs
                              ▼
┌────────────────────────────────────────────────────────────┐
│                    APPLICATION LAYER                       │
│  Java Spring Boot (v3.x) & JPA/Hibernate (Port 8080)       │
│  * Manages authentication (JWT filter), logging, and proxy │
│  * Database: MySQL (Prod) or H2 (Dev in-memory database)    │
└─────────────────────────────┬──────────────────────────────┘
                              │ HTTP / REST Proxy
                              ▼
┌────────────────────────────────────────────────────────────┐
│                       AI ENGINE LAYER                      │
│  FastAPI (Python v3.10+) AI Processing Engine (Port 8000)  │
│  * 14 REST Endpoints: RAG Tutor, Predictive scoring,       │
│    Weakness DAG analyzer, Spaced-repetition planner, OCR    │
└────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Prerequisites & Installation

To run this project on your laptop, you need to install the following dependencies:

### 1. Java JDK 17+ (Required for Backend)
* Download and run the JDK 17 installer from [Eclipse Temurin](https://adoptium.net/temurin/releases/?version=17&os=windows&arch=x64&package=jdk).
* **Important:** During installation, make sure to check the boxes for:
  * `✅ Set JAVA_HOME variable`
  * `✅ Add to PATH`
* Restart your terminal and verify by running: `java -version`

### 2. Node.js 20 LTS (Required for Frontend)
* Download and install Node.js from [Node.js Official Website](https://nodejs.org/).
* Verify installation by running: `node -v` and `npm -v`

### 3. Python 3.10+ (Required for AI Engine)
* Download and install Python from [Python.org](https://www.python.org/downloads/).
* Verify installation by running: `python --version`

---

## 🚀 How to Run the Project (3 Terminals)

Start each service in a separate command prompt window in the following order:

### Step 1: Start the AI Service (Terminal 1)
Navigate to the `ai-service` directory, activate the virtual environment, and start the FastAPI server:
```cmd
cd A:\SmartPrep-ai\SmartPrep-ai\ai-service
.\venv\Scripts\activate
uvicorn main:app --host 127.0.0.1 --port 8000 --reload
```
* **Status Check:** Visit `http://127.0.0.1:8000` in your browser. You should see `{"message": "AI service is running"}`.
* **API Documentation:** You can view and test the API directly at `http://127.0.0.1:8000/docs`.

### Step 2: Start the Central Backend (Terminal 2)
Navigate to the `backend` directory and start the Spring Boot server (uses an auto-created H2 database out of the box):
```cmd
cd A:\SmartPrep-ai\SmartPrep-ai\backend
.\mvnw.cmd spring-boot:run
```
* **Status Check:** Wait until the console logs `Started BackendApplication in X.XXX seconds`. The backend runs on `http://localhost:8080`.

### Step 3: Start the React Frontend (Terminal 3)
Navigate to the `frontend` directory, install package dependencies, and run the developer server:
```cmd
cd A:\SmartPrep-ai\SmartPrep-ai\frontend
npm install
npm start
```
* **Status Check:** Your browser will automatically open to `http://localhost:3000` with the login screen.
---

## 🔍 Troubleshooting Tips

* **"JAVA_HOME is not defined correctly"**:
  Reinstall JDK 17 with the "Set JAVA_HOME" installer checkbox enabled, then open a **new** command prompt.
* **"Failed to load spaCy / DLL load failed"**:
  This is expected on newer Python versions (such as Python 3.14 on Windows) because precompiled libraries like spaCy/NumPy have binary incompatibilities. SmartPrep detects this on startup and automatically falls back to our custom **Pure Python NLP** and **Heuristic fallback engines**, so the app runs smoothly offline without crashing.
* **Frontend shows "Network Error"**:
  Ensure the Spring Boot backend is actively running on port 8080.
* **Backend shows "Connection refused"**:
  Ensure the FastAPI AI service is actively running on port 8000.
