pipeline {
    agent any

    stages {
        stage('Clonage du code source') {
            steps {
                git branch: 'hamzabelaid', url: 'https://github.com/lamissaidi/devops.git'
            }
        }

        stage('Nettoyage et compilation avec Maven') {
            steps {
                sh "mvn clean"
                sh "mvn compile"
            }
        }

        stage('Exécution des tests') {
            steps {
                sh "mvn test"
            }
        }

        stage('Analyse avec SonarQube') {
            steps {
                withCredentials([string(credentialsId: 'sonar', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }

        stage("Construction avec Maven") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }

        stage('Publication des artefacts vers Nexus') {
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

        stage('Construction de l\'image Docker') {
            steps {
                script {
                    sh 'docker build -t hamzabelaid/achat:1.0 -f Dockerfile .'
                }
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('Configuration de Grafana/Prometheus') {
            steps {
                sh 'docker start b6675e7a87ad'
                sh 'docker start 7077cbd3bbc5'
            }
        }
    }
}
