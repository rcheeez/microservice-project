#creating a custom vpc
resource "aws_vpc" "eks_vpc" {
    cidr_block = "10.0.0.0/16"

    tags = {
      Name = "eks-vpc"
    }
}

#creating 2 subnets to a vpc
resource "aws_subnet" "eks_subnet" {
  count = 2
  vpc_id                  = aws_vpc.eks_vpc.id
  cidr_block              = cidrsubnet(aws_vpc.eks_vpc.cidr_block, 8, count.index)
  availability_zone       = element(["ap-south-1a", "ap-south-1b"], count.index)
  map_public_ip_on_launch = true

  tags = {
    Name = "eks-subnet-${count.index}"
  }
}

resource "aws_internet_gateway" "eks_igw" {
    vpc_id = aws_vpc.eks_vpc.id

    tags = {
        Name = "eks-igw"
    }
}

resource "aws_route_table" "eks_route_table" {
    vpc_id = aws_vpc.eks_vpc.id

    route {
        cidr_block = "0.0.0.0/0"
        gateway_id = aws_internet_gateway.eks_igw.id
    }

    tags = {
        Name = "eks-route-table"
    }
}

resource "aws_route_table_association" "a" {
    count = 2
    subnet_id = aws_subnet.eks_subnet[count.index].id
    route_table_id = aws_route_table.eks_route_table.id
}


#creating a security group for network security and access.
resource "aws_security_group" "eks_cluster_sg" {
    vpc_id = aws_vpc.eks_vpc.id

    ingress {
        from_port   = 0
        to_port     = 0
        protocol    = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }

    egress {
        from_port = 0
        to_port = 0
        protocol = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }

    tags = {
        Name = "eks-cluster-sg"
    }
}

#Create IAM role for EKS cluster
resource "aws_iam_role" "eks_cluster_role" {
    name = var.cluster_role
    assume_role_policy = <<EOF
    {
        "Version": "2012-10-17",
        "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
            "Service": "eks.amazonaws.com"
            },
            "Action": "sts:AssumeRole"
        }
        ]
    }
EOF

}

resource "aws_iam_role_policy_attachment" "eks_cluster_role_policy" {
    role = aws_iam_role.eks_cluster_role.name
    policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
}

resource "aws_eks_cluster" "eks_cluster" {
    name = var.cluster_name
    role_arn = aws_iam_role.eks_cluster_role.arn
    version = var.cluster_version
    vpc_config {
        subnet_ids = aws_subnet.eks_subnet[*].id
        security_group_ids = [aws_security_group.eks_cluster_sg.id]
    }

    tags = {
        Name = var.cluster_name
    }
}

resource "aws_iam_role" "eks_node_group_role" {
    name = var.node_group_role
    assume_role_policy = <<EOF
    {
    "Version": "2012-10-17",
    "Statement": [
      {
        "Action": "sts:AssumeRole",
        "Principal": {
          "Service": "ec2.amazonaws.com"
        },
        "Effect": "Allow",
        "Sid": ""
      }
    ]
    }
    EOF
}

resource "aws_iam_role_policy_attachment" "eks_node_group_role_policy" {
    role = aws_iam_role.eks_node_group_role.name
    policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
}

resource "aws_iam_role_policy_attachment" "eks_node_group_cni_policy" {
    role = aws_iam_role.eks_node_group_role.name
    policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
}

resource "aws_iam_role_policy_attachment" "eks_node_group_registry_policy" {
  role       = aws_iam_role.eks_node_group_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
}

resource "aws_eks_node_group" "eks_node_group" {
    cluster_name = aws_eks_cluster.eks_cluster.name
    node_group_name = var.node_group_name
    node_role_arn = aws_iam_role.eks_node_group_role.arn
    subnet_ids = aws_subnet.eks_subnet[*].id

    scaling_config {
        desired_size = var.desired_capacity
        max_size = var.max_capacity
        min_size = var.min_capacity
    }

    instance_types = ["t3.medium"]

    remote_access {
        ec2_ssh_key = var.ssh_key_name
        source_security_group_ids = [aws_security_group.eks_cluster_sg.id]
    }

    tags = {
        Name = var.node_group_name
    }

    depends_on = [ aws_iam_role_policy_attachment.eks_node_group_role_policy ]
}