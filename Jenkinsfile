node {
    stage('SCM') {
        git 'git@github.com:dingfeng/software-risk-management.git'
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package"
    }
    stage('deploy') {
        sh "echo 'Fengxian123' | sudo docker stop my || true"
        sh "echo 'Fengxian123' | sudo docker rm my || true"
        sh "echo 'Fengxian123' | sudo docker run --name my -p 11111:8080 -d linux/tomcat"
        sh "echo 'Fengxian123' | sudo docker cp target/*.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
