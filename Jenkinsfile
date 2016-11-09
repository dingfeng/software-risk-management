node {
    stage('SCM') {
        git 'git@github.com:dingfeng/software-risk-management.git'
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package"
    }
    stage('deploy') {
        sh "docker stop $(docker ps -a -q) || true"
        sh "docker rm $(docker ps -a -q) || true"
        sh "docker run --name my -p 11111:8080 -d linux/tomcat"
        sh "docker cp target/*.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
