## Spring Boot - WebSocket - Kafka @Kubernetes Messaging Demo

![App Screenshot](login.png)

![App Screenshot](messaging.png)

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Docker - 19.03

4. Kubernetes - 1.14

## Steps to Setup

**1. Clone the repository**

```bash
git clone https://tcsgitreader:hrdr9092@github.com/tcsgit/hello.git
```

**2. Deploy Kafka cluster on kubernetes using kubectl**

```bash
cd hello
kubectl apply -f kafka/
```

**3. Build the application using maven**

```bash
mvnw package
```

**4. Dockerize the application using maven**

```bash
mvnw dockerfile:build
```

**5. Deploy the application on kubernetes using kubectl**

```bash
kubectl apply -f k8s-hello-deployment-version.yaml
```

Optionally, you can use Jenkinsfiles to create CI/CD pipeline for Kubernetes or GCP
