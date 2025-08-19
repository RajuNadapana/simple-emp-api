pipeline {
    agent any

    tools {
        maven 'Maven-3.8.4' // Make sure this matches your Jenkins Maven installation
        jdk 'JDK-21'        // Make sure this matches your Jenkins JDK installation
    }

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=$WORKSPACE/.m2/repository"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'YOUR_GIT_REPO_URL'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'mkdir -p $WORKSPACE/.m2/repository'
                    sh 'mvn clean install -B -Dmaven.repo.local=$WORKSPACE/.m2/repository'
                }
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test -B -Dmaven.repo.local=$WORKSPACE/.m2/repository'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -B -Dmaven.repo.local=$WORKSPACE/.m2/repository'
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
