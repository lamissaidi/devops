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
                // Étape de nettoyage du projet
                sh "mvn clean"

                // Étape de compilation du projet
                sh "mvn compile"
            }
        }

        stage('Exécution des tests') {
            steps {
                sh "mvn test"  // Exécuter les tests JUnit
            }
        }

        stage('Analyse avec SonarQube') {
            steps {
                // Fournir l'authentification SonarQube en utilisant le jeton fourni
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
                    // Construire l'image Docker (remplacez 'Dockerfile' par l'emplacement de votre Dockerfile)
                    sh 'docker build -t hamzabelaid/achat:1.0 -f Dockerfile .'
                }
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                sh 'docker-compose up -d'  // Utiliser -d pour exécuter en mode détaché
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
