node {
    def mvnHome
    def jdk = tool name: 'JDK 14'
    def jdk8=tool name:'JDK 1.8'
    def docu_dir=env.excelsea_doc
    env.JAVA_HOME = "${jdk}"

    stage('Preparation') {
        checkout scm
        mvnHome = tool 'Maven'
        dir('excelsea') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' clean"
            } else {
                bat(/"${mvnHome}\bin\mvn" clean/)
            }
        }
    }

    dir('excelsea') {
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
                sh "'${mvnHome}/bin/mvn' compile -DskipTests site"
                sh "'${mvnHome}/bin/mvn' -P jenkins -DskipTests site:stage-deploy"
            } else {
                bat(/"${mvnHome}\bin\mvn" compile -DskipTests site/)
                bat(/"${mvnHome}\bin\mvn" -P jenkins -DskipTests site:stage-deploy/)
            }
        }
        stage('Results') {
            junit allowEmptyResults: true, testResults: '**/TEST-*.xml'
        }
    }
}