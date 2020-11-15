# API-SerenityBDD-sample

## **Overview:**
API is the acronym for Application Programming Interface, which is a software intermediary that allows two applications to talk to each other.  This API framework is developed using Serenity-REST Assured.  REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs. Serenity BDD has implemented Rest Assured wrapper to provide better/faster execution and generate report documentation. In this framework we sample for API testing with (BDD) and without feature files (TDD).  

For Demo purpose all the test cases are StudentApp API. StudentApp Jar file is placed in studentApp folder inside resources package.

### **Some of the key features of this framework:**

1. It generates Serenity report with all the step details.
2. It support parallel execution of API test cases.
3. FeatureRunner file can be used to run Serenity BDD style API tests. JunitTestRunner can be used to run API tests without feature. One of these style can be used to develop framework. For demo purpose both the type has been considered here.
4. Student app will started before the test runs and also closed after all the tests are complete. All these will be done programmatically, there is no manual intervention required.
5. Test execution can be triggered form command line. This will run both the above style of tests.
6. Easy integration to CI/CD pipeline.

## **Required Setup :**

- [Java](https://www.guru99.com/install-java.html) should be installed and configured.
- [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/) should be installed and configured.
- Download the files from Git repository either as zip file OR using [Git](https://phoenixnap.com/kb/how-to-install-git-windows).

## **Running Test:**

Open the command prompt and navigate to the folder in which pom.xml file is present.
Run the below Maven command.

    mvn clean serenity:aggregate verify


Once the execution completes report will be generated in below folder structure.

**Serenity Report:** 	*/target/site/serenity/index.html*
