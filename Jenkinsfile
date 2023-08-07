node {
  def mvnHome = tool 'MAVEN_HOME';
  stage('SCM') {
    checkout scm
	  if (isUnix()) {
         sh "cd claims"
		 sh "pwd"
      } else {
         bat(/cd claims/)
		 bat(/dir/)
      }
  }
  stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" clean package/)
      }
   }
    stage('Quality Analysis') {
      // Run the maven build
      if (isUnix()) {
         withSonarQubeEnv('sonarqube') {
          sh "'${mvnHome}/bin/mvn' sonar:sonar -Dsonar.projectKey=ClaimsApplication -Dsonar.projectName=ClaimsApplication"
        }
      }
   }
}
