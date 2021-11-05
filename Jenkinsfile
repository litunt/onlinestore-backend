pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo "Starting to build Online Store Application!"
                sh './gradlew clean build'
                sh 'docker build -t online-store-image .'
            }
        }
        stage('Test') {
            steps {
                echo "Running tests...."
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose -f docker/docker-compose.postgres.yml up'
                sh 'docker-compose -f docker/docker-compose.liquibase.yml up'
                sh 'docker-compose -f docker/docker-compose.app.yml up'
            }
        }
    }
}