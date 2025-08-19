pipeline {
    agent any

    tools {
        jdk 'JDK-21'         // must match Jenkins JDK installation
        maven 'Maven-3.8.4'  // must match Jenkins Maven installation
    }

    environment {
        // Persistent Maven repo outside workspace
        MAVEN_OPTS = "-Dmaven.repo.local=C:\\ProgramData\\Jenkins\\.m2\\repository"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                git branch: 'main', url: 'https://github.com/RajuNadapana/simple-emp-api.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project with Maven...'
                bat 'mvn clean install -B %MAVEN_OPTS%'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test -B %MAVEN_OPTS%'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the application...'
                bat 'mvn package -B %MAVEN_OPTS%'
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
        success {
            echo 'Build Successful!'
        }
        failure {
            echo 'Build Failed!'
        }
    }
}
