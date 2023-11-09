pipeline {
    agent any

    stages {
        stage('Clones code from git') {
            steps {
                git branch: 'dorrabardi' , 
                url : 'https://github.com/lamissaidi/devops.git';
            }
        }

        stage('Cleans ans compile') {
            steps {
               
                sh "mvn clean"
                sh "mvn compile"
            }
        }
          stage('Junit') {
            steps {
                sh "mvn test "  
            }

           
        }
        stage('SonarQube') {
            steps {
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

        stage(' Nexus') {
            steps {
                script {
                    nexusArtifactUploader artifacts: [[
                        artifactId: 'achat',
                        classifier: '',
                        file: 'target/achat-1.0.jar',
                        type: 'jar']],
                        credentialsId: 'nexus',
                        groupId: 'tn.esprit.rh',
                        nexusUrl: '192.168.93.37:8081',
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        repository: 'maven-releases/',
                        version: '1.0'
                }
            }
        }
stage(' Docker Image') {
            steps {
                script {
                    // Build the Docker image (replace 'Dockerfile' with your Dockerfile location)
                    sh 'docker build -t dorrabardi/achat:1.0 -f Dockerfile .'
                }
            }
        }
stage('Push Docker Image') {
    steps {
            
            sh 'docker push dorrabardi/achat:1.0'
        }
    }
}
       
          stage(' Docker Compose') {
            steps {
                    sh 'docker-compose up -d'  // Use -d to run in detached mode
            
                }
            }
         stage('Grafana') {
            steps {
                sh 'docker start eefc2b26c664'
                sh 'docker start 002d6c7b45c7'
            }
        }
    }
}
