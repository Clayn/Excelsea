node {
    def mvnHome
    def jdk = tool name: 'JDK 14'
    def jdk8=tool name:'JDK 1.8'
    env.JAVA_HOME = "${jdk}"

    dir('excelsea') {
        stage('Preparation') {
            checkout scm
            mvnHome = tool 'Maven'
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' clean"
            } else {
                bat(/"${mvnHome}\bin\mvn" clean/)
            }
        }

        stage('Build') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -DskipTests install"
            } else {
                bat(/"${mvnHome}\bin\mvn" -DskipTests install/)
            }
        }

        stage('Test') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore=true test"
            } else {
                bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore=true test/)
            }
        }

        stage('Reporting') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -DskipTests site"
            } else {
                bat(/"${mvnHome}\bin\mvn" -DskipTests site/)
            }
        }

        stage('Results') {
            junit allowEmptyResults: true, testResults: '**/TEST-*.xml'
        }
    }
}