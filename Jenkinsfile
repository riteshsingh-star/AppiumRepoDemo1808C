pipeline {
    agent {
        label any // Ensure your Jenkins agent has Android SDK installed or use Docker
    }

    parameters {
        string(name: 'deviceIndex', defaultValue: '0', description: 'Device index to pass to testng.xml')
    }

    environment {
        ANDROID_HOME = '/usr/local/lib/android/sdk'
        ANDROID_SDK_ROOT = '/usr/local/lib/android/sdk'
        PATH = "${env.PATH}:${ANDROID_HOME}/platform-tools"
    }

    tools {
        jdk 'jdk-17'  // Make sure this is configured in Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'java -version'
                // your build commands here
            }
        }

        stage('Set up Node.js and Appium') {
            steps {
                sh '''
                    curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
                    sudo apt-get install -y nodejs
                    npm install -g appium
                    appium driver install uiautomator2
                '''
            }
        }

        stage('Start Android Emulator') {
            steps {
                sh '''
                    echo "no" | avdmanager create avd -n testEmulator -k "system-images;android-36;google_apis;x86_64" --device "pixel" || true
                    emulator -avd testEmulator -no-window -no-audio -no-boot-anim -gpu swiftshader_indirect &
                    adb wait-for-device
                    adb shell input keyevent 82
                    sleep 30
                '''
            }
        }

        stage('Start Appium Server') {
            steps {
                sh '''
                    nohup appium --log logs/appium-log.log > /dev/null 2>&1 &
                    sleep 15
                '''
            }
        }

        stage('Run Maven Tests') {
            steps {
                sh """
                    mvn clean test -DdeviceIndex=${params.deviceIndex}
                """
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/surefire-reports/**/*.*', allowEmptyArchive: true
            archiveArtifacts artifacts: 'logs/appium-log.log', allowEmptyArchive: true
        }
        failure {
            echo 'Build failed. Check test reports and logs.'
        }
    }
}
