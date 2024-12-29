pipeline{
    agent any

    tools {
        maven 'maven3'
        jdk 'jdk17'
    }

    parameters {
        choice(name: 'DEPLOY_ENV', choices: ['blue', 'green'], description: 'Choose which environment to deploy: Blue or Green')
        choice(name: 'DOCKER_TAG', choices: ['blue', 'green'], description: 'Choose the Docker image tag for the deployment')
        booleanParam(name: 'SWITCH_TRAFFIC', defaultValue: false, description: 'Switch traffic between Blue and Green')
    }

    environment {
        IMAGE_NAME = "guravarchies/eureka-server"
        TAG = "${params.DOCKER_TAG}"
        KUBE_NAMESPACE = "webapps"
        SCANNER_HOME = tool 'sonar-scanner'
    }

    stages{
        stage("Git Checkout"){
            steps{
               git branch: 'main', credentialsId: 'git-creds', url: 'https://github.com/rcheeez/eureka-server-registry.git'
            }
        }

        stage("Compile"){
            steps{
               sh "mvn compile -DskipTests=true"
            }
        }

        stage("Test"){
            steps{
               sh "mvn test"
            }
        }

        stage("Sonar Qube Analysis"){
            steps{
              withSonarQubeEnv('sonar') {
                    sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.projectKey=EUREKA-SERVER -Dsonar.projectName=EUREKA-SERVER \
                    -Dsonar.java.binaries=. '''
                }
            }
        }

        stage("Build"){
            steps{
               sh "mvn package -DskipTests=true"
            }
        }

        stage("Deploy to Nexus"){
            steps{
                withMaven(globalMavenSettingsConfig: 'global-maven', jdk: 'jdk17', maven: 'maven3', mavenSettingsConfig: '', traceability: true) {
                    sh "mvn deploy -DskipTests=true"
                }
            }
        }

        stage("Docker Build & Tag Image"){
            steps{
               script {
                    withDockerRegistry(credentialsId: 'docker-creds', url: 'https://index.docker.io/v1/') {
                        sh "docker build -t ${IMAGE_NAME}:${TAG} ."
                    }
               }
            }
        }

        stage("Docker Push"){
            steps{
               script {
                    withDockerRegistry(credentialsId: 'docker-creds', url: 'https://index.docker.io/v1/') {
                        sh "docker push ${IMAGE_NAME}:${TAG}"
                    }
               }
            }
        }

        stage('Deploy App Service') {
            steps {
                script {
                    withKubeConfig(caCertificate: '', clusterName: 'eks-cluster', contextName: '', credentialsId: 'k8s-token', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://80985AB9323F756E46E4667BEE94F850.gr7.ap-south-1.eks.amazonaws.com') {
                        sh """ if ! kubectl get svc eureka-service -n ${KUBE_NAMESPACE}; then
                                  kubectl apply -f eureka-service.yml -n ${KUBE_NAMESPACE}
                               fi
                           """
                    }
                }
            }
        }

        stage("Deploy to Kubernetes"){
            steps{
               script {
                    def deploymentFile = ""
                    if (params.DEPLOY_ENV == 'blue') {
                        deploymentFile = 'eureka-server-blue-deployment.yml'
                    } else {
                        deploymentFile = 'eureka-server-green-deployment.yml'
                    }
                    withKubeConfig(caCertificate: '', clusterName: 'eks-cluster', contextName: '', credentialsId: 'k8s-token', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://80985AB9323F756E46E4667BEE94F850.gr7.ap-south-1.eks.amazonaws.com') {
                            sh "kubectl apply -f ${deploymentFile} -n ${KUBE_NAMESPACE}"
                    }
               }
            }
        }

        stage('Switch Traffic Between Blue & Green Environment') {
            when {
                expression { return params.SWITCH_TRAFFIC }
            }
            steps {
                script {
                    def newEnv = params.DEPLOY_ENV

                    withKubeConfig(caCertificate: '', clusterName: 'eks-cluster', contextName: '', credentialsId: 'k8s-token', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://80985AB9323F756E46E4667BEE94F850.gr7.ap-south-1.eks.amazonaws.com') {
                            sh """
                                kubectl patch service eureka-service -p '{"spec": {"selector": {"app": "eureka-server", "version": "${newEnv}"}}}' -n ${KUBE_NAMESPACE}
                               """
                    }
                        echo "Traffic has been switched to the ${newEnv} environment."
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    def verifyEnv = params.DEPLOY_ENV
                    withKubeConfig(caCertificate: '', clusterName: 'eks-cluster', contextName: '', credentialsId: 'k8s-token', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://80985AB9323F756E46E4667BEE94F850.gr7.ap-south-1.eks.amazonaws.com') {
                        sh """
                        kubectl get pods -l version=${verifyEnv} -n ${KUBE_NAMESPACE}
                        kubectl get svc eureka-service -n ${KUBE_NAMESPACE}
                        """
                    }
                }
            }
        }

    }
}