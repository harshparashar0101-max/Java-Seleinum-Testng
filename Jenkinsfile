pipeline {
    agent any

    environment {
        XRAY_BASE_URL = 'https://xray.cloud.getxray.app'
        TEST_EXEC_KEY = 'LOGI-70'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/harshparashar0101-max/Java-Seleinum-Testng.git'
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Authenticate to Xray') {
            steps {
                withCredentials([
                    string(credentialsId: 'XRAY_CLIENT_ID', variable: 'XRAY_CLIENT_ID'),
                    string(credentialsId: 'XRAY_CLIENT_SECRET', variable: 'XRAY_CLIENT_SECRET')
                ]) {
                    bat '''
                    curl -H "Content-Type: application/json" ^
                         -X POST ^
                         --data "{\\"client_id\\":\\"%XRAY_CLIENT_ID%\\",\\"client_secret\\":\\"%XRAY_CLIENT_SECRET%\\"}" ^
                         %XRAY_BASE_URL%/api/v2/authenticate > xray_token.txt
                    '''
                }
            }
        }
        
        stage('Check Xray File') {
    steps {
        bat 'dir test-output'
    }
}

        stage('Upload Results to Xray') {
    steps {
        bat '''
        set /p XRAY_TOKEN=<xray_token.txt
        set XRAY_TOKEN=%XRAY_TOKEN:"=%

        curl -H "Content-Type: text/xml" ^
             -H "Authorization: Bearer %XRAY_TOKEN%" ^
             --data @test-output/testng-results.xml ^
             "https://xray.cloud.getxray.app/api/v2/import/execution/testng?testExecKey=LOGI-70"
        '''
    }
    }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/surefire-reports/*.xml, test-output/**', fingerprint: true
        }
    }
}