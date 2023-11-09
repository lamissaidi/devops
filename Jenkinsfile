pipeline {
    agent any

    stages {
        stage('Récupération from git') {
            steps {
                git branch: 'lamissaidi' , 
                url : 'https://github.com/lamissaidi/devops.git';
            }
        }

        stage('clean/compile Mvn') {
            steps {
                sh "mvn clean"
                sh "mvn compile"
            }
        }
          stage('run test mvn') {
            steps {
                sh "mvn test " 
            }

           
        }
        stage('SonarQube') {
            steps {
                //authentication SonarQube
                withCredentials([string(credentialsId: 'sonar', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }
         stage("Build Mvn") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }

        stage('Publish the Artifacts to Nexus') {
            steps {
                script {
                    nexusArtifactUploader artifacts: [[
                        artifactId: 'achat',
                        classifier: '',
                        file: 'target/achat-1.0.jar',
                        type: 'jar']],
                        credentialsId: 'nexus',
                        groupId: 'tn.esprit.rh',
                        nexusUrl: '192.168.21.37:8081',
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        repository: 'maven-releases/',
                        version: '1.0'
                }
            }
        }
        stage('Build Image/Docker') {
            steps {
                script {
                    // Build the Docker image (replace 'Dockerfile' with your Dockerfile location)
                    sh 'docker build -t lamissaidi/achat:1.0 -f Dockerfile .'
                }
            }
        }

    
          stage('Deploy Docker Compose') {
            steps {
                    sh 'docker-compose up -d'  // Use -d to run in detached mode
            
                }
            }
         stage('prometheus/Grafana') {
            steps {
                sh 'docker start 6e77a7bbc0ec'
                sh 'docker start 668eb687a0cb'
            }
        }
    }
}
