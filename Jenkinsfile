pipeline {

    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Starting to build Online Store Application!'
//                 sh "chmod +x gradlew"
//                 sh './gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests....'
            }
        }
        stage('Deploy') {
            steps {
                sh 'ssh ec2-user@ec2-54-165-116-214.compute-1.amazonaws.com <<EOF'
                echo 'Connected to EC2'
            }
//             steps {
//                 sh 'ssh -i "aws_ssh.pem" ec2-user@ec2-52-91-194-30.compute-1.amazonaws.com'
//                 sh 'docker build -t online-store-image .'
//                 sh 'docker-compose -f docker/docker-compose.postgres.yml up'
//                 sh 'docker-compose -f docker/docker-compose.liquibase.yml up'
//                 sh 'docker-compose -f docker/docker-compose.app.yml up'
//             }
        }
    }
}