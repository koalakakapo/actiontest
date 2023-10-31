pipeline {
    agent any
    parameters {
        choice(name: 'BROWSER', choices: ['CHROME', 'FIREFOX', 'IE'], description: 'The browser to execute tests against')
        string(name: 'ENVIRONMENTURL', defaultValue: '', description: 'URL to execute tests against')
    }
    environment {
        PROJECT = 'Your Test Automation'
        DESCRIPTION = 'Your Automation Pipeline'
        APPLICATION = 'Your'
        MAILTO = 'yourEmail@delta.org'
        // Used to name artifacts
        SERVICE = 'your-Test_Automation'
        PATH = "${WORKSPACE}/_bin:/opt/OV/bin:/delta/cloudbees/bin:/usr/local/bin:${PATH}"

        ARTIFACTORY_URL = 'https://jfrogbuild.deltads.ent/artifactory'
        // Artifactory tool repo path, for grabbing utilities
        ARTIFACTORY_TOOLS = "delta-bin-virtual"
        // TODO not used
        SCANNER_HOME = tool 'SonarQubeScanner'
        // Send mail on success and fail to ...
        // If a version can not be extracted from the pom - use this
        DEFAULT_VERSION = '1.0.0'

        // Used to load app-specific variables
        // TODO this is set incorrectly externally to EnBProjects
        GIT_ORG = "DEVPROJECTS"
        GIT_REPO_NAME = sh(script: "echo ${GIT_URL} | sed -E 's:.*\\/(.*).git:\\1:'", returnStdout: true).trim()

    }
    stages {

        stage('Artifactory configuration') {
            steps {
                rtMavenDeployer(
                        id: "MAVEN_DEPLOYER",
                        serverId: "jfrogbuild",
                        releaseRepo: "delta-mvn-local-dev-release",
                        snapshotRepo: "delta-mvn-local-dev-snapshot"
                )
                rtMavenResolver(
                        id: "MAVEN_RESOLVER",
                        serverId: "jfrogbuild",
                        releaseRepo: "libs-release",
                        snapshotRepo: "libs-snapshot"
                )
            }
        }
        stage('Build') {
            steps {
                rtMavenRun(
                        tool: "maven",
                        pom: 'pom.xml',
                        goals: 'clean verify install -DskipTests -Dmaven.test.failure.ignore=true -Dbuild.number=${BUILD_NUMBER}',
                        resolverId: "MAVEN_RESOLVER"
                )
            }
            options {
                timeout(time: 1, unit: 'HOURS')
            }
        }

        stage('Code Quality and Test Execution') {
            parallel {
                stage('SonarQube analysis') {
                    steps {
                        withSonarQubeEnv(installationName: 'SonarQubeScanner') {
                            echo "Running a SonarQube scan as a maven target."
                            rtMavenRun(
                                    tool: "maven",
                                    pom: 'pom.xml',
                                    goals: "verify sonar:sonar -Dmaven.test.failure.ignore=true",
                                    resolverId: "MAVEN_RESOLVER"

                            )
                        }
                    }
                    options {
                        timeout(time: 1, unit: 'HOURS')
                    }
                }
                stage('Saucelabs test trigger') {
                    steps {
                        sauce(credentialsId: 'art1sauce-cred') {
                            sauceconnect(useGeneratedTunnelIdentifier: true, useLatestSauceConnect: true, verboseLogging: true) {
                                sh '''java -Dplatform='Windows 10' -Dremote=true -cp $(echo target/*.jar | tr \' \' \':\') org.testng.TestNG testng.xml'''
                            }
                        }
                    }
                    options {
                        timeout(time: 1, unit: 'HOURS')
                    }
                    post {
                        always {
                            archiveArtifacts 'test-output/**/*'
                            archiveArtifacts 'testResults/**/*'
                            junit(testResults: 'test-output/junitreports/*.xml', allowEmptyResults: true, healthScaleFactor: 1)
                            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'test-output', reportFiles: 'report.html', reportName: 'AutomationReport', reportTitles: ''])
                        }
                    }
                }
            }
        }

    }
    tools {
        jdk 'java-11'
        maven 'maven'
    }

    post {
        failure {
            echo 'Failed ...'
            mail(to: "${env.MAILTO}", subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER} [${currentBuild.result}]", body: "Failed to build test framework JAR. Build URL: ${env.BUILD_URL}.\n")
            sh '''
        echo "Clean up"
      '''
        }
        cleanup {
            // This is a workaround.
            // Read this: https://stackoverflow.com/questions/55297411/jenkins-pipeline-remove-tmp-folder
            // Until these defects are fixed in CB's please include the following steps.
            // Cleanup the workspace
            deleteDir()
            // clean up tmp directory
            dir("${workspace}@tmp") {
                deleteDir()
            }
            // clean up script directory
            dir("${workspace}@script") {
                deleteDir()
            }
        }
    }
    options {
        timeout(time: 1, unit: 'HOURS')
        disableConcurrentBuilds()
        timestamps()
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '10', daysToKeepStr: '', numToKeepStr: '10')
    }
    triggers { cron(env.BRANCH_NAME == 'dev' ? 'H H(0-7) * * *' : '') }
}