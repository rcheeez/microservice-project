variable "cluster_name" {
  type        = string
  default     = "eks-cluster"
  description = "Name of the EKS cluster"
}

variable "cluster_version" {
  type        = string
  default     = "1.31"
  description = "EKS Kubernetes Version"
}

variable "cluster_role" {
  type        = string
  default     = "eks-cluster-role"
  description = "EKS Cluster IAM Role"
}

variable "node_group_role" {
  type        = string
  default     = "eks-node-group-role"
  description = "EKS Node Group IAM Role"
}

variable "node_group_name" {
  type        = string
  default     = "eks-node-group"
  description = "Name of the EKS Node Group"
}

variable "ssh_key_name" {
  type        = string
  default     = "ec2-key-pair"
  description = "SSH Key Name for EKS Node Group"
}

variable "node_instance_type" {
  type        = string
  default     = "t2.medium"
  description = "EC2 Instance type for the nodes"
}

variable "desired_capacity" {
  type        = string
  default     = "3"
  description = "Desired Number of nodes in the eks cluster"
}

variable "min_capacity" {
  type        = string
  default     = "2"
  description = "Minumum number of nodes in the EKS cluster"
}

variable "max_capacity" {
  type        = string
  default     = "4"
  description = "Maximum number of nodes in the EKS cluster"
}

