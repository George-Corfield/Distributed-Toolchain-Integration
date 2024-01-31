# syntax=docker/dockerfile:1

FROM python:3
COPY . .
EXPOSE 5000
RUN pip install --no-cache-dir -r requirements.txt
CMD ["python", "./pythonAPI.py"]