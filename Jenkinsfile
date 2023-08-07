node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'MAVEN_HOME';
    withSonarQubeEnv('SonarQube_Home') {
      mvn clean verify sonar:sonar -Dsonar.projectKey=ClaimsApplication -Dsonar.projectName='ClaimsApplication'
    }
  }
}
