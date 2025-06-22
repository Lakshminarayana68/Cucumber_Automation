pipeline {
    agent any

    tools {
        maven 'Maven_Home'
        jdk 'Java_Home'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Choose browser')
        string(name: 'TAGS', defaultValue: '@signup', description: 'Cucumber tags to run')
    }

    environment {
        REPORT_DIR = "test-output\\ExtentReports"
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timestamps()
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Cloning repository..."
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                echo "Building project using Maven..."
                bat 'mvn clean compile'
            }
        }

        stage('Execute Tests') {
            steps {
                echo "Running tests on ${params.BROWSER} with tags ${params.TAGS}"
                bat "mvn test -Dbrowser=${params.BROWSER} -Dcucumber.filter.tags=${params.TAGS}"
            }
        }

        stage('Retry on Failure') {
            when {
                expression { currentBuild.result == 'FAILURE' }
            }
            steps {
                echo "Retrying failed test run once..."
                retry(1) {
                    bat "mvn test -Dbrowser=${params.BROWSER} -Dcucumber.filter.tags=${params.TAGS}"
                }
            }
        }

        stage('Archive Test Reports') {
            steps {
                echo "Archiving ExtentReports and test results..."
                archiveArtifacts artifacts: 'target/Reports/ExtentReport.html', allowEmptyArchive: true
                junit 'target\\surefire-reports\\*.xml'
            }
        }

        //New stage: Publish HTML Report
        stage('Publish HTML Report') {
            steps {
                echo "Publishing HTML Extent Report..."
                publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/Reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }

        //Modified Email Notification with clickable report link
        stage('Send Email Notification') {
            steps {
                echo "Sending email notification..."
                emailext(
                    subject: "Build ${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """Hello Team,

Build Result: ${currentBuild.currentResult}
Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Executed on Browser: ${params.BROWSER}
Tags Used: ${params.TAGS}

Extent Report: ${env.BUILD_URL}Extent_20Report/

Regards,
Jenkins
""",
                    to: 'adityasr2278@gmail.com'
                )
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            cleanWs()
        }
        success {
            echo "Build completed successfully."
        }
        failure {
            echo "Build failed. Please check logs and reports."
        }
    }
}
