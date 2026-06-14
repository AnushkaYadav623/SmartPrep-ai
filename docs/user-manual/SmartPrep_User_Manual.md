# SmartPrep AI: User Manual & Setup Guide

Hey! Welcome to the SmartPrep AI User Manual. We built this project to make studying for exams way easier and less stressful. This guide will walk you through what the project is, how to install it on your computer, and how to use all the cool features we created.

---

## 1. What is SmartPrep AI? (Project Overview)

Basically, SmartPrep AI is a smart website designed to help you prepare for your exams without wasting hours summarizing textbooks or figuring out what to study. 

Instead of reading huge books and writing down notes manually, you just upload your lecture notes, PDFs, or old exam papers (PYQs). Our app uses Artificial Intelligence (AI) and Natural Language Processing (NLP) to read the documents and automatically generate:
* **Revision Notes**: Clear, bulleted summaries of the main topics.
* **Flashcards**: Interactive cards with questions on the front and answers on the back for quick memory checks.
* **Practice Quizzes**: Multiple-choice tests tailored to the content you uploaded.
* **Adaptive Study Planner**: A calendar that updates itself based on how well you do in the quizzes and how close your exam date is.
* **Readiness Score**: A percentage that tells you how ready you are for the actual exam based on your quiz history and mock tests.

### How it's built:
* **Frontend**: Built with React.js. It's the website dashboard you see and click on.
* **Backend**: Built with Spring Boot. It handles user login, files metadata, and database transactions.
* **AI Service**: Built with Python FastAPI. It runs the spaCy NLP library to extract keywords and coordinates the AI models.
* **Database**: MySQL. It saves your username, passwords, study schedules, and test scores.
* **AI Engine**: A cascading framework. It defaults to the online Google Gemini API, but if you lose internet, it automatically falls back to a local Ollama Llama3 model running offline on your computer.

---

## 2. What you need before you start (Prerequisites)

To install and run this project, make sure your computer has the following installed:

### Hardware Requirements
* **RAM**: At least 8 GB RAM (16 GB is highly recommended if you want to run the offline AI model smoothly).
* **Disk Space**: At least 10 GB of free space.

### Software Requirements
* **Node.js**: Version 18 or higher (for running the React frontend).
* **Java JDK**: Version 17 (for running the Spring Boot backend).
* **Python**: Version 3.10 or higher (for the AI microservice).
* **MySQL Server**: Version 8.0 or higher (for saving all user and study data).
* **Ollama**: (Optional, but required if you want to test the offline fallback AI).

---

## 3. Step-by-Step Installation Guide

### Step 3.1: Create the Database (MySQL)
1. Start your local **MySQL Server**.
2. Open your MySQL client (like MySQL Workbench, Command Line, or DBeaver).
3. Create a database named `smartprep_db` by running this command:
   ```sql
   CREATE DATABASE smartprep_db;
   ```

### Step 3.2: Pull the Offline AI Model (Ollama)
1. Download Ollama from [ollama.com](https://ollama.com) and install it.
2. Run the Ollama app on your computer.
3. Open your terminal or Command Prompt and run this command to pull the Llama3 model:
   ```bash
   ollama pull llama3
   ```

### Step 3.3: Set up the Python AI Service (FastAPI)
1. Open your terminal and go to the `ai-service` folder:
   ```bash
   cd A:\SmartPrep-ai\SmartPrep-ai\ai-service
   ```
2. Create a virtual environment so python libraries don't clash:
   ```bash
   python -m venv venv
   ```
3. Activate the virtual environment:
   * **Windows (PowerShell)**: `.\venv\Scripts\Activate.ps1`
   * **macOS/Linux**: `source venv/bin/activate`
4. Install all the required packages:
   ```bash
   pip install -r requirements.txt
   ```
5. Install the spaCy NLP model:
   ```bash
   python -m spacy download en_core_web_sm
   ```
6. Create a file named `.env` in the `ai-service` folder and add your Gemini API key:
   ```env
   GEMINI_API_KEY=your_actual_gemini_api_key_here
   OLLAMA_HOST=http://localhost:11434
   PORT=8000
   ```
7. Start the server:
   ```bash
   uvicorn main:app --host 127.0.0.1 --port 8000 --reload
   ```

### Step 3.4: Set up the Backend (Spring Boot)
1. Go to the `backend` folder:
   ```bash
   cd A:\SmartPrep-ai\SmartPrep-ai\backend
   ```
2. Open the `src/main/resources/application.properties` file in a text editor.
3. Update the MySQL database username and password with yours:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/smartprep_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=your_mysql_password
   
   spring.jpa.hibernate.ddl-auto=update
   ai.service.url=http://localhost:8000
   jwt.secret=some_long_super_secure_key_at_least_32_chars_long
   ```
4. Run the backend server using the Maven wrapper:
   * **Windows**: `.\mvnw.cmd spring-boot:run`
   * **macOS/Linux**: `./mvnw spring-boot:run`

### Step 3.5: Set up the Frontend (React)
1. Go to the `frontend` folder:
   ```bash
   cd A:\SmartPrep-ai\SmartPrep-ai\frontend
   ```
2. Install the frontend packages:
   ```bash
   npm install
   ```
3. Start the React app:
   ```bash
   npm start
   ```
   *Your browser should automatically open http://localhost:3000 showing the login page!*

---

## 4. How to Use the App (User Workflow)

Here is how you actually use the platform step-by-step:

1. **Sign Up & Log In**: 
   * Go to the signup page, type your name, email, and password. Once registered, log in to access your dashboard.
2. **Upload Your Study Materials**:
   * Click on the **Materials** tab. Upload a PDF of your lecture notes, textbook chapters, or old university papers (PYQs).
3. **Get AI-Generated Study Material**:
   * Once processing is complete, open your document. You will see tabs for:
     * **Revision Notes**: Fast summaries of complex concepts.
     * **Flashcards**: Flip cards to test your memory on key terms.
     * **Quizzes**: Practice multiple-choice questions.
4. **Chat with Your PDF (AI Tutor)**:
   * Have a specific question? Use the chat box to ask questions like, *"Explain how the tokenization process works in this PDF."* The AI searches the document and answers you directly.
5. **Set Up Your Study Plan**:
   * Set your target exam date in the **Planner** tab. The app will calculate a personalized revision timetable for you.
6. **Track Your Performance**:
   * Go to the **Analytics** page. You can see your overall Exam Readiness Score percentage, check your consistency, and look at the "Weakness Map" to see which topics you need to practice more.

---

## 5. Main Features Explained

* **Cascading AI Framework**: We wanted the app to be highly reliable. If you have an internet connection, it uses the Google Gemini API to generate everything. If you lose internet or hit API rate limits, it automatically routes all requests to your local Ollama Llama3 engine running offline, so you never lose access.
* **PYQ Intelligence**: Instead of just holding old exam papers, the app reads them, finds the most repeated questions and topics, and highlights them as **High Priority** in your daily study calendar so you know what to focus on first.
* **Explainable Weakness Detection**: When you take a quiz and select incorrect choices, the system doesn't just mark them wrong. It traces the mistakes to find what concept you are struggling with and adds that specific topic back into your revision calendar.

---

## 6. Screenshot Placeholders

Use these placeholders to insert screenshots of your running application in your final project report:

* **[Screenshot 1: User Login Screen]**  
  *Capture the minimalist React sign-in page.*
* **[Screenshot 2: Student Dashboard]**  
  *Capture the main dashboard screen showing your Readiness Score circle, weekly study hours, and prioritized tasks.*
* **[Screenshot 3: Materials Upload Page]**  
  *Capture the document upload area showing files list and "Processing" status tags.*
* **[Screenshot 4: Quizzes & Flashcards Portal]**  
  *Capture the screen displaying active recall flashcards and quiz questions.*
* **[Screenshot 5: Study Planner Calendar]**  
  *Capture the calendar page showing color-coded daily revision targets.*

---

## 7. Troubleshooting & FAQs

### Troubleshooting

#### 1. Mismatch package errors during React installation (`npm install`)
* **Solution**: Run the install command using the legacy peer parameter:
  ```bash
  npm install --legacy-peer-deps
  ```

#### 2. Spring Boot database connection failures
* **Solution**: Make sure MySQL is running on your machine. Double-check your database username and password in the `application.properties` file.

#### 3. FastAPI address already in use error
* **Solution**: If port 8000 is taken, start FastAPI on port 8001 by running:
  ```bash
  uvicorn main:app --host 127.0.0.1 --port 8001 --reload
  ```
  *Make sure to change the AI URL port to 8001 in your Spring Boot application.properties file too.*

#### 4. Local Ollama fallback doesn't reply
* **Solution**: Make sure the Ollama application is active in your taskbar. Check that you ran `ollama pull llama3` in your terminal prior to running the app.

---

### Frequently Asked Questions (FAQs)

**Q: Can I upload scanned pages of my handwritten notes?**  
*A: Yes! But keep in mind that the AI needs readable text. If the handwriting is messy, word extraction accuracy might drop, which will affect the quality of notes and quizzes.*

**Q: What happens if I miss a scheduled study day?**  
*A: The system automatically detects it, pushes the missed topic to the next day, and updates the priority weightage so you don't fall behind on important subjects.*

**Q: Do I need an internet connection to use the AI chatbot?**  
*A: Nope! As long as you have Ollama running locally with Llama3 pulled, the app will seamlessly run offline and answer your questions using the local model.*
