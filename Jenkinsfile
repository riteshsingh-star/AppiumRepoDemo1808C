pipeline {
    agent any // Use any available agent

    parameters {
        string(name: 'deviceIndex', defaultValue: '0', description: 'Device index to pass to testng.xml')
    }

    environment {
        // Set Android SDK path - update this to your actual SDK path
        ANDROID_HOME = 'C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk'
        ANDROID_SDK_ROOT = 'C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk'
        PATH = "${env.PATH};${ANDROID_HOME}\\platform-tools"
    }

    tools {
        jdk 'jdk-17'  // Ensure this name matches the JDK in Jenkins Global Tool Config
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java Installation') {
            steps {
                bat 'java -version'
            }
        }

        stage('Set up Node.js and Appium') {
            steps {
                bat '''
                    choco install nodejs -y
                    npm install -g appium
                    appium driver install uiautomator2
                '''
            }
        }

        stage('Start Android Emulator') {
            steps {
                bat '''
                    echo no | avdmanager create avd -n testEmulator -k "system-images;android-33;google_apis;x86_64" --device "pixel" || exit /b 0
                    start /B emulator -avd testEmulator -no-window -no-audio -no-boot-anim -gpu swiftshader_indirect
                    adb wait-for-device
                    adb shell input keyevent 82
                    timeout /T 30
                '''
            }
        }

        stage('Start Appium Server') {
            steps {
                bat '''
                    if not exist logs mkdir logs
                    start /B appium --log logs\\appium-log.log
                    timeout /T 15
                '''
            }
        }

        stage('Run Maven Tests') {
            steps {
                bat "mvn clean test -DdeviceIndex=${params.deviceIndex}"
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target\\surefire-reports\\**\\*.*', allowEmptyArchive: true
            archiveArtifacts artifacts: 'logs\\appium-log.log', allowEmptyArchive: true
        }
        failure {
            echo 'Build failed. Check test reports and logs.'
        }
    }
}
