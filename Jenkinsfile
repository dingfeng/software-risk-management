node {
    stage('SCM') {
        git 'git@github.com:dingfeng/software-risk-management.git'
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -p 11111:8080 -d linux/tomcat"
        sh "docker cp target/*.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
