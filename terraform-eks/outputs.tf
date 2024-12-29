# Outputs for VPC
output "vpc_id" {
  description = "The ID of the VPC"
  value       = aws_vpc.eks_vpc.id
}

# Outputs for Subnets
output "subnet_ids" {
  description = "The ID of the subnets"
  value       = aws_subnet.eks_subnet[*].id
}

# Outputs for Internet Gateway
output "internet_gateway_id" {
  description = "The ID of the Internet Gateway"
  value       = aws_internet_gateway.eks_igw.id
}

# Outputs for Route Table
output "route_table_id" {
  description = "The ID of the Route Table"
  value       = aws_route_table.eks_route_table.id
}

# Outputs for Security Groups
output "eks_cluster_security_group" {
  description = "The ID of the EKS Cluster Security Group"
  value       = aws_security_group.eks_cluster_sg.id
}

# Outputs for EKS Cluster
output "eks_cluster_id" {
  description = "The ID of the EKS Cluster"
  value       = aws_eks_cluster.eks_cluster.id
}

output "eks_cluster_endpoint" {
  description = "The endpoint of the EKS Cluster"
  value       = aws_eks_cluster.eks_cluster.endpoint
}

output "eks_cluster_version" {
  description = "The Kubernetes version of the EKS Cluster"
  value       = aws_eks_cluster.eks_cluster.version
}

# Outputs for EKS Node Group
output "eks_node_group_id" {
  description = "The ID of the EKS Node Group"
  value       = aws_eks_node_group.eks_node_group.id
}

# Outputs for IAM Roles
output "eks_cluster_role_arn" {
  description = "The ARN of the EKS Cluster IAM Role"
  value       = aws_iam_role.eks_cluster_role.arn
}

output "eks_node_group_role_arn" {
  description = "The ARN of the EKS Node Group IAM Role"
  value       = aws_iam_role.eks_node_group_role.arn
}