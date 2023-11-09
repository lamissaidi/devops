pipeline {
    agent any

    stages {
        stage('Code Retrieval') {
            steps {
                git branch: 'hamzabelaid' , 
                url : 'https://github.com/lamissaidi/devops.git';
            }
        }

        stage('Clean and Compile with Maven') {
            steps {
                // Clean the project
                sh "mvn clean"

                // Compile the project
                sh "mvn compile"
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn test"  // Run JUnit tests
            }
        }

        stage('Run SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }

        stage("Build Maven Package") {
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

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker-compose up -d'  // Use -d to run in detached mode
            }
        }

        stage('Start Grafana/Prometheus') {
            steps {
                sh 'docker start b6675e7a87ad'
                sh 'docker start 7077cbd3bbc5'
            }
        }
    }
}
