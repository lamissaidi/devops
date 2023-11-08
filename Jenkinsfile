
pipeline {
    agent any

    stages {
        stage('Git') {
            steps {

                    git branch: 'bouchmelmohamedmustapha',
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
        stage('Deploy TO NEXUS') {
                                steps {
                                    sh 'mvn deploy -Dmaven.test.skip=true'
                                }
                            }
    }
}
