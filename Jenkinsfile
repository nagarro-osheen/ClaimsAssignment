node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'MAVEN_HOME';
    withSonarQubeEnv('SonarQube_Home') {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=ClaimsApplication -Dsonar.projectName='ClaimsApplication'"
    }
  }
}
