pipeline {
    agent any

    tools {
        jdk 'JDK-21'         // Must match Jenkins JDK installation
        maven 'Maven-3.8.4'  // Must match Jenkins Maven installation
    }

    environment {
        // Persistent Maven repo outside workspace
        MAVEN_OPTS = "-Dmaven.repo.local=C:\\ProgramData\\Jenkins\\.m2\\repository"

        // SonarCloud settings
        SONARQUBE_SERVER      = 'Sonar Cloud'                  // Jenkins SonarCloud installation name
        SONAR_PROJECT_KEY     = 'RajuNadapana_simple-emp-api' // Your SonarCloud project key
        SONAR_PROJECT_NAME    = 'simple-emp-api'              // Your project name
        SONAR_ORGANIZATION    = 'RajuNadapana'                // Your SonarCloud organization key
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
            post {
                always {
                    // Publish JUnit reports
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarCloud analysis...'
                withSonarQubeEnv(SONARQUBE_SERVER) {
                    bat """
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=%SONAR_PROJECT_KEY% ^
                        -Dsonar.organization=%SONAR_ORGANIZATION% ^
                        -Dsonar.projectName=%SONAR_PROJECT_NAME% ^
                        -Dsonar.host.url=https://sonarcloud.io
                    """
                }
            }
            post {
                always {
                    echo 'SonarCloud analysis completed.'
                }
            }
        }

        stage('Quality Gate Check') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    echo 'Waiting for SonarCloud quality gate...'
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
        success {
            echo 'Build & SonarCloud analysis Successful!'
        }
        failure {
            echo 'Build or SonarCloud analysis Failed!'
        }
    }
}
