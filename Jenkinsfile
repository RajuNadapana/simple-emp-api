pipeline {
    agent any

    tools {
        maven 'Maven-3.8.4'
        jdk 'JDK-21'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/RajuNadapana/simple-emp-api.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install -B'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test -B'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -B'
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
