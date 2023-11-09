pipeline {
    agent any

    stages {
        stage('Récupération du code de la branche') {
            steps {
                git branch: 'hamzabelaid' , 
                url : 'https://github.com/lamissaidi/devops.git';
            }
        }

        stage('Nettoyage et compilation avec Maven') {
            steps {
                // Étape de nettoyage du projet
                sh "mvn clean"

                // Étape de compilation du projet
                sh "mvn compile"
            }
        }
          stage('Exécution des tests') {
            steps {
                sh "mvn test "  // Run JUnit tests
            }

           
        }
        stage('SonarQube') {
            steps {
                // Provide SonarQube authentication using the provided token
                withCredentials([string(credentialsId: 'sonar', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }
         stage("Maven Build") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }

        stage('Publish Artifacts to Nexus') {
            steps {
                script {
                    nexusArtifactUploader artifacts: [[
                        artifactId: 'achat',
                        classifier: '',
                        file: 'target/achat-1.0.jar',
                        type: 'jar']],
                        credentialsId: 'nexus',
                        groupId: 'tn.esprit.rh',
                        nexusUrl: '192.168.21.63:8081',
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        repository: 'maven-releases/',
                        version: '1.0'
                }
            }
        }
       
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image (replace 'Dockerfile' with your Dockerfile location)
                    sh 'docker build -t hamzabelaid/achat:1.0 -f Dockerfile .'
                }
            }
        }
stage('Build and Push to DockerHub') {
    steps {
        script {
            // Log in to DockerHub using the credentials
            withCredentials([string(credentialsId: 'dockerhub-mdp', variable: 'DOCKERHUB-MDP')]) {
                sh "docker login -u hamzabelaid -p ${DOCKERHUB-MDP}"
            }

            // Build the Docker image
            sh 'docker build -t hamzabelaid/achat:1.0 .'

            // Push the Docker image to DockerHub
            sh 'docker push hamzabelaid/achat:1.0'
        }
    }
}




       
          stage('Deploy with Docker Compose') {
            steps {
                    sh 'docker-compose up -d'  // Use -d to run in detached mode
            
                }
            }
        stage('Grafana/prometheus') {
            steps {
                sh 'docker start b6675e7a87ad'
                sh 'docker start 7077cbd3bbc5'
            }
        }

    }
}
