pipeline {

    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Starting to build Online Store Application!'
                sh "chmod +x gradlew"
                sh './gradlew clean build -x test'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests....'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -t online-store-image .'
                sh 'docker-compose -f docker/docker-compose.postgres.yml up -d'
                sh 'docker-compose -f docker/docker-compose.liquibase.yml up -d'
                sh 'docker-compose -f docker/docker-compose.app.yml up -d'
            }
        }
    }
}