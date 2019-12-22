node {  
    
	//checkout scm

	def home = "C:/Dev/hello"
		
   	def pom = readMavenPom file: "${home}/pom.xml"
   	version = pom.version
   
	stage('Build') { 
		bat "cd ${home} && mvnw package"
    }
    
	stage('Dockerize') { 
		bat "cd ${home} && mvnw dockerfile:build"	
	}
    
	stage('Deploy') { 
		bat "cd ${home} && copy k8s-hello-deployment.yaml k8s-hello-deployment-version.yaml && cscript replace.vbs \"k8s-hello-deployment-version.yaml\" \"IMAGE_TAG\" \"${version}\""
		
		bat "cd ${home} && kubectl apply -f k8s-hello-deployment-version.yaml"
	}
}