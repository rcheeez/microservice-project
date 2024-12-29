# Microservices Project with EKS Deployment 🚀

Welcome to the **Microservices Architecture Project** repository! This project demonstrates a robust architecture comprising five components: three REST API-based microservices, one Eureka Server for service discovery, and an API Gateway for centralized routing. The entire setup is deployed on an **EKS Cluster** with infrastructure provisioning managed by  **Terraform** .

---

## 🏗️ **Project Overview**

This project is a REST API implementation without a user interface, designed to showcase expertise in:

* **Microservices Design**
* **Spring Boot**
* **EKS Deployment**
* **Service Discovery** with Eureka
* **API Gateway** for routing

### **Components**

1. **Eureka Server** : Handles service discovery and registration for all microservices.
2. **API Gateway** : Centralized routing for client requests to respective services.
3. **Inventory Service** : REST API to manage inventory items.
4. **Product Service** : REST API to handle product details and purchases.
5. **Coupon Service** : REST API for managing and validating coupons.

Each component resides in its  **own GitHub branch** , allowing independent management and deployment.

---

## 🛠️ **Setup and Installation**

### **Prerequisites**

Ensure the following tools are installed:

* **Java 17**
* **Terraform**
* **Docker**
* **AWS CLI**
* **Kubectl**

### **1. Microservices Deployment**

All microservices are built with **Spring Boot** and configured to use Eureka for service discovery. The API Gateway routes requests to the appropriate microservices.

### **2. Infrastructure Deployment**

* The infrastructure is deployed using  **Terraform** .
* It includes a  **custom VPC** ,  **EKS Cluster** , and **IAM roles** to manage resources.
* Kubernetes manifests are used to deploy the microservices to the EKS cluster.

---

## 📂 **Repository Structure**

```plaintext
.
├── main                     # Main branch for project overview
├── eureka-server            # Eureka Server implementation
├── api-gateway              # API Gateway implementation
├── inventory-service        # Inventory Service REST API
├── product-service          # Product Service REST API   
└── coupon-service    	     # Coupon Service REST API
```

---

## 📜 **Installation Scripts**

Bash scripts are included for setting up key tools in an  **EC2 VM** :

1. **Jenkins Installation 🛠️**

   ```shell
   sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
     https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
   echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
     https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
     /etc/apt/sources.list.d/jenkins.list > /dev/null

   sudo apt update 
   sudo apt install openjdk-17-jre-headless

   sudo apt-get update
   sudo apt-get install jenkins

   sudo systemctl enable jenkins
   sudo systemctl start jenkins
   sudo systemctl status jenkins
   ```
2. **SonarQube Installation (Docker)**📊

   ```shell
   sudo apt-get update
   sudo apt install docker.io -y
   docker run -p 9000:9000 -d sonarqube:lts-community
   ```
3. **Nexus Repository Installation**📦

   ```shell
   sudo apt-get update
   sudo apt install docker.io -y
   docker run -p 8081:8081 -d sonatype/nexus3
   ```
4. **Terraform Installation**🌐

   ```shell
   sudo apt-get update && sudo apt-get install -y gnupg software-properties-common

   wget -O- https://apt.releases.hashicorp.com/gpg | \
   gpg --dearmor | \
   sudo tee /usr/share/keyrings/hashicorp-archive-keyring.gpg > /dev/null

   gpg --no-default-keyring \
   --keyring /usr/share/keyrings/hashicorp-archive-keyring.gpg \
   --fingerprint

   echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] \
   https://apt.releases.hashicorp.com $(lsb_release -cs) main" | \
   sudo tee /etc/apt/sources.list.d/hashicorp.list

   sudo apt update
   sudo apt-get install terraform
   ```
5. **AWS CLI Installation** ☁️

   ```shell
   sudo apt-get update
   curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
   sudo apt install unzip
   unzip awscliv2.zip
   sudo ./aws/install
   ```
6. **Kubectl Installation** ⚙️

   ```shell
   sudo apt-get update
   sudo apt-get install -y apt-transport-https ca-certificates curl gnupg

   curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.32/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg
   sudo chmod 644 /etc/apt/keyrings/kubernetes-apt-keyring.gpg

   echo 'deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.32/deb/ /' | sudo tee /etc/apt/sources.list.d/kubernetes.list
   sudo chmod 644 /etc/apt/sources.list.d/kubernetes.list

   sudo apt-get update
   sudo apt-get install -y kubectl

   ---- Command to Connect to your EKS Cluster using Kubectl ----
   aws eks update-kubeconfig --region <region-name> --name <cluster-name>
   ```

Each script is designed to simplify the installation process and ensure a consistent environment.

---

## ⚡ **Quick Start**

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-repo-name.git
   cd your-repo-name
   ```
2. **Switch to a Component Branch** :

   ```bash
   git checkout <branch-name>
   ```

2. **Deploy Infrastructure** :
   Navigate to your Terraform configuration and apply:

   ```bash
   terraform init
   terraform plan  #to verify the resources
   terraform apply
   ```

2. **Deploy Microservices** :
   Apply Kubernetes manifests using `kubectl`:

   ```bash
   kubectl apply -f <component-name>.yml
   ```

2. **Access Services** :
   Use the API Gateway to route requests to respective microservices.

---

## 🔐 **Creating a Service Account for CI/CD Tools**

To set up a Kubernetes user with appropriate permissions for deployments and programmatic access, follow these steps:

---

## **Step 1: Create a Namespace** (Optional)

```bash
kubectl create namespace webapps
```

---

## **Step 2: Define a Service Account**

Create a `service-account.yaml` file:

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: k8s-user
  namespace: webapps
```

Apply the manifest:

```bash
kubectl apply -f service-account.yaml
```

---

## **Step 3: Assign Roles**

Create a `role.yaml` file:

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  name: app-role
  namespace: webapps
rules:
  - apiGroups:
        - ""
        - apps
        - autoscaling
        - batch
        - extensions
        - policy
        - rbac.authorization.k8s.io
    resources:
      - pods
      - secrets
      - componentstatuses
      - configmaps
      - daemonsets
      - deployments
      - events
      - endpoints
      - horizontalpodautoscalers
      - ingres
      - jobs
      - limitranges
      - namespaces
      - nodes
      - pods
      - persistentvolumes
      - persistentvolumeclaims
      - resourcequotas
      - replicaset
      - replicationcontrollers
      - serviceaccounts
      - services
    verbs: ["get", "list", "watch", "create", "update", "patch", "delete"]
```

Apply the manifest:

```bash
kubectl apply -f role.yaml
```

---

## **Step 4: Bind Roles to the Service Account**

Create a `rolebinding.yaml` file:

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: app-rolebinding
  namespace: webapps
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: app-role
subjects:
  - kind: ServiceAccount
    name: k8s-user
    namespace: webapps
```

Apply the manifest:

```bash
kubectl apply -f rolebinding.yaml
```

---

## **Step 5: Generate a Token for the Service Account**

Create a `secret.yaml` file:

```yaml
apiVersion: v1
kind: Secret
type: kubernetes.io/service-account-token
metadata:
  name: k8s-secrets
  namespace: webapps
  annotations:
    kubernetes.io/service-account.name: k8s-user
```

Apply the manifest:

```bash
kubectl apply -f secret.yaml
```

Retrieve the token:

```bash
kubectl describe secret k8s-secrets
```

Save the token securely for programmatic access.

---

## 🛡️ **Highlights**

* **Eureka Service Discovery** : Seamlessly manages microservice registration and lookup.
* **Scalable Architecture** : Built for horizontal scaling with Kubernetes.
* **Infrastructure as Code** : Deployed on AWS EKS using Terraform for reproducibility.
* **RBAC for Kubernetes** : Configured Role-Based Access Control for secure service and user communication.
* **Tool Integrations** : Includes Jenkins, SonarQube, and Nexus for CI/CD.

---

## 📝 **Contributing**

Contributions are welcome! Feel free to open issues or submit pull requests.

---

## 📧 **Contact**

For queries or feedback, feel free to reach out via:

* **Email** : [archiesgurav10@gmail.com](mailto:archiesgurav10@gmail.com)
