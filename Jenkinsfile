node {
	def mvnHome = tool 'MAVEN_HOME';
	stage('SCM') {
		checkout scm
	}
	stage('Build') {
		dir('claims'){
			if (isUnix()) {
				sh "pwd"
				sh "'${mvnHome}/bin/mvn' clean package"
			} else{
				bat(/dir/)
				bat(/"${mvnHome}\bin\mvn" clean package/)
			}
		} 	
	}
    
	stage('Quality Analysis') {
		dir('claims'){
			if (isUnix()) {
				sh "pwd"
				withSonarQubeEnv('SonarQube_Home') {
        			sh "'${mvnHome}/bin/mvn' sonar:sonar -Dsonar.projectKey=ClaimsApplication -Dsonar.projectName=ClaimsApplication"
       			}
			} else{
				bat(/dir/)
				withSonarQubeEnv('SonarQube_Home') {
          			bat(/"${mvnHome}\bin\mvn" sonar:sonar -Dsonar.projectKey=ClaimsApplication -Dsonar.projectName=ClaimsApplication/)
       			}
			}
		} 
   }
}
