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
//                 sshagent(credentials : ['1403bea3-1417-4a36-8210-32236b30aafe']) {
//                     sh 'ssh -o StrictHostKeyChecking=no ec2-user@ec2-3-84-27-192.compute-1.amazonaws.com uptime'
//                     echo 'Connected to EC2'
//                     sh 'docker build -t online-store-image .'
//                     sh 'docker-compose -f docker/docker-compose.postgres.yml up -d'
//                     sh 'docker-compose -f docker/docker-compose.liquibase.yml up -d'
//                     sh 'docker-compose -f docker/docker-compose.app.yml up -d'
//                 }
            }
        }
    }
}