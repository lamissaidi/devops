pipeline {
    agent any

    stages {
        stage('Récupération du code de la branche') {
            steps {
                git branch: 'nessimlandoulsi' , 
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
                withCredentials([string(credentialsId: 'sonarToken', variable: 'SONAR_TOKEN')]) {
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

    }
}
