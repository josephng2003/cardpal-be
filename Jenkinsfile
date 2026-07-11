pipeline {
    agent any

    environment {
        JAVA_VERSION = "21"
        AWS_REGION = credentials('aws-region') ?: 'us-east-1'
        SENTRY_AUTH_TOKEN = credentials('sentry-auth-token')
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Setup') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                }
            }
        }

        stage('Validate Gradle Wrapper') {
            steps {
                script {
                    sh './gradlew wrapper --gradle-version 8.5'
                }
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    sh './gradlew clean build --no-daemon'
                }
            }
        }

        stage('Upload Artifacts') {
            steps {
                script {
                    archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: false
                    junit 'build/test-results/**/*.xml'
                }
            }
        }

        stage('Publish Test Reports') {
            steps {
                script {
                    publishHTML([
                        reportDir: 'build/reports/tests/test',
                        reportFiles: 'index.html',
                        reportName: 'Test Report'
                    ])
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        failure {
            echo 'Build failed!'
        }
        success {
            echo 'Build successful!'
        }
    }
}
