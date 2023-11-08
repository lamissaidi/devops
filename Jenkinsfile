
Conversation ouverte. 1 message lu.

Aller au contenu
Utiliser Messagerie esprit avec un lecteur d'écran

8 sur 9 408
(aucun objet)
Boîte de réception

Sarra Tounsi
12:38 (il y a 2 heures)
À moi


Traduire le message
Désactiver pour : anglais
pipeline {
    agent any

    stages {
        stage('Git') {
            steps {

                    git branch: 'bouchmelmedmustapha',
                        url: 'https://github.com/lamissaidi/devops.git'

            }
        }

        stage('mvn_clean') {
            steps {
                script {
                    sh 'mvn clean'       }
            }
        }
          stage('mvn_compile') {
            steps {
                script {
                   sh 'mvn compile'      }
            }
        }
            stage('MVN SONARQUBE') {
                steps {
                    script {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true';
                   }
                }
        }
        stage('JUnit tests') {
                    steps {
                        script {
                            sh 'mvn clean test'
                            }
                    }
                }
    }
}
