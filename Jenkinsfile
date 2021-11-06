def remote = [:]
remote.name = 'ec2-user@ec2-52-91-194-30.compute-1.amazonaws.com'
remote.host = 'ec2-52-91-194-30.compute-1.amazonaws.com'
remote.user = 'ec2-user'
remote.allowAnyHosts = true

pipeline {

    agent any

    stages {
        stage('Build') {
            steps {
                echo "Starting to build Online Store Application!"
//                 sh "chmod +x gradlew"
//                 sh './gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                echo "Running tests...."
            }
        }
        stage('Deploy') {
            steps {
                script {
                    aws_ssh
                }
                sshScript remote: remote, script: "docker-run.sh"
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