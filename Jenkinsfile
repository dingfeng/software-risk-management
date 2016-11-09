node {
    stage('SCM') {
        git 'git@github.com:dingfeng/software-risk-management.git'
    }
    stage('QA') {
        sh 'sonar-scanner'
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package"
    }
    stage('deploy') {
        sh "docker -H 127.0.0.1:2375 stop my || true"
        sh "docker -H 127.0.0.1:2375 rm my || true"
        sh "docker -H 127.0.0.1:2375  run --name my -p 11111:8080 -d linux/tomcat"
        sh "docker -H 127.0.0.1:2375 cp target/*.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
