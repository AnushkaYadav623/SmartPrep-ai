FROM python:3.10-slim

WORKDIR /app

# Install system dependencies if required by spacy/numpy
RUN apt-get update && apt-get install -y build-essential

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Download Spacy model as specified in setup (if needed, it's a good practice to download in build step)
# RUN python -m spacy download en_core_web_sm

COPY . .

EXPOSE 8000

# Run FastAPI app with Uvicorn
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]
