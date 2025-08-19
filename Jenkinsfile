pipeline {
    agent any

    tools {
        maven 'Maven-3.8.4' // Must match Jenkins Maven installation
        jdk 'JDK-21'        // Must match Jenkins JDK installation
    }

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=%WORKSPACE%\\.m2\\repository"
    }

    stages {
        stage('Checkout') {
            steps {
                // Replace with your real GitHub repo URL
                git branch: 'main', url: 'https://github.com/RajuNadapana/simple-emp-api.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    bat 'mkdir %WORKSPACE%\\.m2\\repository'
                    bat 'mvn clean install -B -Dmaven.repo.local=%WORKSPACE%\\.m2\\repository'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test -B -Dmaven.repo.local=%WORKSPACE%\\.m2\\repository'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -B -Dmaven.repo.local=%WORKSPACE%\\.m2\\repository'
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace"
            cleanWs()
        }
        success {
            echo "Build Successful!"
        }
        failure {
            echo "Build Failed!"
        }
    }
}
