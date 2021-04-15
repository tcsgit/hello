## AWS - KOPS Kubernetes Cluster Installation

## Requirements

An EC2 instance as command center for cli operations

## Installation Steps

**1. Install kubectl and KOPS cli**

```bash
curl -LO https://storage.gooapis.com/kubernetes-release/release/v1.15.2/bin/linEC2 instance as command center for cli operationsux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl

curl -LO https://github.com/kubernetes/kops/releases/download/v1.15.2/kops-linux-amd64
chmod +x kops-linux-amd64
sudo mv kops-linux-amd64 /usr/local/bin/kops
```

**2. Create an IAM role for the KOPS user with following  policies**

```bash
AmazonEC2FullAccess
AmazonRoute53FullAccess
AmazonS3FullAccess
IAMFullAccess
AmazonVPCFullAccess
```

**3. Configure AWS Cli for the KOPS user credentials (Access Key ID & Secret Access Key)**

```bash
aws configure
```

**4. Export Environment Variables for KOPS**

```bash
export AWS_ACCESS_KEY_ID=$(aws configure get aws_access_key_id)
export AWS_SECRET_ACCESS_KEY=$(aws configure get aws_secret_access_key)
```

**5. Create a S3 bucket as the state store for the kubernetes cluster**

```bash
aws s3 mb s3://hello-kops-state --region us-east-2
```

**6. Export cluster name and S3 bucket name for cluster creation**

```bash
export NAME=hello.k8s.local
export KOPS_STATE_STORE=s3://hello-kops-state
```

**7. Generate ssh key for the cluster access operations**

```bash
ssh-keygen -f .ssh/id_rsa
```

**8. Create kubernetes cluster**

```bash
kops create cluster --zones=us-east-2b --master-size="t2.micro" --node-size="t2.micro" --node-count="3" --master-count=1 --kubernetes-version=1.15.12 ${NAME}
```
