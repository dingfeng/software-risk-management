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
        sh "/opt/docker-deploy.sh"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
}
