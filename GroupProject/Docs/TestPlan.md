# Test Plan

**Author**: Team 104

## 1 Testing Strategy

### 1.1 Overall strategy

We perform testing at the following four testing levels: 
* **Unit Testing**: Each class or operation inside a class will be considered as a unit. This process will be carried out by both developers and testers. Testers will determine and write code for a set of test cases and pass to the developers. Developers will run the test during and after the process of developing the classes. Test cases should be tracked for potential regression testing. 
* **Integration Testing** Testing at this level will be mainly carried out by testers after new module is added and unit testing stage is passed. Test cases should be tracked for potential regression testing. 
* **Regression Testing** This test process will be carried out by testers when there is any testing failure in the previous stage or any modification to the program in the previous steps. Tester should make sure to perform the same set of test cases as before. 
* **System Testing** This testing process will be performed by testers and QAs as the last testing stage. It will mainly focus on the system's compliance with its specified requirement. 

### 1.2 Test Selection

We mainly use black-box testing as our testing strategy. For unit and integration testing, we will use the category-partition method which mainly consists of identification of edge cases. For system testing, we use the finite state machine testing technique which mainly focus on the transition from one state to another.

### 1.3 Adequacy Criterion

We derive a set of test obligations from requirement specification and design diagrams. We assure the adequacy criterion is satisfied if every test obligation is satisfied by at least one test case.

### 1.4 Bug Tracking

We create a bug-tracking channel in our team's slack workspace to track bugs and enhancements and keep developers informed.

### 1.5 Technology

JUnit is used for the automated testings. 

## 2 Test Cases

### UI Test

Purpose | Steps | Expected result | Actual Result | P/F
--- | --- | --- | --- | ---
Main Menu buttons | User opens this application for the first time and click on each buttons | Each buttons should navigate the user to the designated pages, except for 'compare job offers" which should be disabled. | Each button took user to designated pages, but "compare job offers" is not disabled if no job offer available | P
Add current Job | User enters details of current job, and click on save | The current job should be added | The current job details are added to database | P
Edit current Job | User will be displayed with job details entered previously, user choose to change one or more field | The current job should updated with information enter | User is able to change one or more field, new info is added to the database | P
Enter job offers | User enters details of a job offer, and click on save | The job offer should be saved | Job offer is added to the database | P
Enter invalid value | when user enters invalid value such as negative values or decimal numbers | A error message should be shown and ask user to enter an valid value | an exclamation mark is shown when invalid value entered | P
Enter another offer | User add a job offer and click on save | The option of 'enter another offer' should be available | The "enter another offer" button is availanle | P
Adjust the comparison settings | User selects to adjust comparison settings | The weights should be equal integer if no weights is entered by the user, otherwise, the weights should be updated with user's input values. | default weights are 1s, and user is able to enter different weights | P
Select job to compare | User choose compare job in the main menu | User should see a list of offers ranked by job score, including the current job, clearly indicated. | A list ranked job is shown, the current job is shown on the top | P
Compare two job | User choose two jobs to be compared | User should be shown a table with details of the two job selected. | User is able to select two job to compare, and see a table with two set of job details shown side-by-side | P
Perform another comparison | User choose to perform another comparison | User should be taken back to the list of ranked offers | user is presented with the option and taken back to the previous page | P



### Function Test

Purpose | Steps | Expected result | Actual Result | P/F
--- | --- | --- | --- | ---
enterJobOffers | input String title, String company, String location, Int costOfLiving, Int yearlySalary,Int yearlyBonus, Int allowedTeleworkDays, Int leaveTime, Int sharesOffered, click on "submit current job" button| The "submit current job" button is clickable | The "submit current job" button is clickable | P
EditCurrentJob button | click on "Enter or Edit Current Job" again after entering current job  | "Enter or Edit Current Job" is clickable | "Enter or Edit Current Job" is clickable | P
Edit Current Job Errors | Enter "123" for job title | Error message "Invalid Title" expected | Error message "Invalid Title" observed | P
Edit Current Job Errors | Enter "321" for company | Error message "Invalid Company" expected | Error message "Invalid Company" observed | P
Edit Current Job Errors | Enter "" for location | Error message "Invalid Location" expected | Error message "Invalid Location" observed | P
Edit Current Job Errors | Enter "" for cost of living | Error message "Invalid location cost of living index" expected | Error message "Invalid location cost of living index" observed | P
Edit Current Job Errors | Enter "string" for salary | Error message "Invalid yearly salary" expected | Error message "Invalid yearly salary" observed | P
Edit Current Job Errors | Enter "invalid" for bonus | Error message "Invalid yearly bonus" expected | Error message "Invalid yearly bonus" observed | P
Edit Current Job Errors | Enter "6" for Telework days | Error message "Invalid weekly telework days" expected | Error message "Invalid weekly telework days" observed | P
Edit Current Job Errors | Enter "notNumber" for leave time | Error message "Invalid yearly leave time" expected | Error message "Invalid yearly leave time" observed | P
Edit Current Job Errors | Enter "-1" for share offered | Error message "Invalid number of company shares" expected | Error message "Invalid number of company shares" observed | P
Compare Job Offer button disabled because no job offer | Without entering any job offers, check text on the compare job button; Enter one job offers and check on the button again | the button should display "Compare Job Offers (Disabled)" when no job offer entered and "Compare Job Offers" after job offer added; | the button displayed "Compare Job Offers (Disabled)" when no job offer entered and "Compare Job Offers" after job offer added; | P
"Enter Job Offers" button | Click on "Enter Job Offers" | "Enter Job Offers" is clickable | "Enter Job Offers" is clickable | "Enter Job Offers" is clickable | P
Test invalid value when entering job offer | Enter "123" for job title | Error message "Invalid Title" expected | Error message "Invalid Title" observed | P
Test invalid value when entering job offer | Enter "321" for company | Error message "Invalid Company" expected | Error message "Invalid Company" observed | P 
entering job offer | Enter "" for location | Error message "Invalid Location" expected | Error message "Invalid Location" observed | P 
Test invalid value when entering job offer | Enter "" for cost of living | Error message "Invalid location cost of living index" expected | Error message "Invalid location cost of living index" observed | P 
Test invalid value when entering job offer| Enter "string" for salary | Error message "Invalid yearly salary" expected | Error message "Invalid yearly salary" observed | P 
Test invalid value when entering job offer | Enter "invalid" for bonus | Error message "Invalid yearly bonus" expected | Error message "Invalid yearly bonus" observed | P 
Test invalid value when entering job offer| Enter "6" for Telework days | Error message "Invalid weekly telework days" expected | Error message "Invalid weekly telework days" observed | P 
Test invalid value when entering job offer | Enter "notNumber" for leave time | Error message "Invalid yearly leave time" expected | Error message "Invalid yearly leave time" observed | P 
Test invalid value when entering job offer | Enter "-1" for share offered | Error message "Invalid number of company shares" expected | Error message "Invalid number of company shares" observed | P 
Test invalid value when entering comparison setting weights | enter "" for weights | Error message "Invalid weight set for yearly salary/yearly bonus/telework days/leave time/company shares" expected | Error message "Invalid weight set for yearly salary/yearly bonus/telework days/leave time/company shares" observed | P
Compare job offer with the current job | Enter current job; Enter a job offer; click on "Compare" | A new page where the current job and the job offer just entered are shown side by side with correct job titles and other job details | The current job and the job offer just entered are shown side by side with correct job details | P
Choose two job to compare | Enter current job; enter 3 other job offers; Choose the two highest ranked jobs to compare | the job comparison page should display the two highest scored jobs and their correct job scores | the job comparison page displays the two highest scored jobs and their correct job scores | P 

### Database Test

Purpose | Steps | Expected result | Actual Result | P/F
--- | --- | --- | --- | ---
Write Job and Retrieve | Insert job class Job("title", "company", "location", 1, 2, 3, 4, 5, 6) and then retrieve job by ID | Correct job details retreived | Correct job details retreived | P
Write Multiple Job and Delete | Insert 3 job class job1, job2, and job3; delete job2; Count # of jobs in the database | jobDao.getAll().size() = 2 | jobDao.getAll().size() = 2 | P
Write Comparison Setting and Retrieve | Insert ComparisonSettings(1,1,1,1,1) and then retrieve weights by ID | Correct weights retrieved | Correct weights retrieved | P
Write MainMenu and Retrieve | Insert MainMenu("jobID", jobIDList) and retrieve all MainMenu | One Mainmenu retrieved, and correct Mid, currentJobID, and listOfJobOfferIDs retrieved | One Mainmenu retrieved; correct Mid, currentJobID, and listOfJobOfferIDs retrieved | P
Write Mainmenu and Delete | Insert MainMenu("jobID", jobIDList); delete mainmenu; retrieve all | 0 mainmenu retrieved | 0 mainmenu retrieved | P
Write Mainmenu and update | Insert MainMenu("jobID", jobIDList); update currentJobID; retrieve | One Mainmenu retrieved; correct Mid, updated currentJobID retrieved | One Mainmenu retrieved; correct updated currentJobID retrieved 