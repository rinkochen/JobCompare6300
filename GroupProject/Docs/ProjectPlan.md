# Project Plan

**Author**: Team 104

## 1 Introduction

This is a single-user Android application which allows user to compare job offers with current job with respect to different aspects.

## 2 Process Description

1. Requirement Gathering
    - We follow Assignment 5 specification as user requirements for this application. In addition, we hold dicussion on gathering a list detailed system requirements which include both functional and non-functional requirements. 
    - Entrance criteria: None
    - Exit criteria: Successful completion of system requirements documentation.

2. Software Design
    - This process involves creation of class diagram, component diangram, deployment diagram, use case diagram, etc. For creating the class diagram, we gather everyone's draft and provide feedback on each other's design. We consolidate our drafts into a final version based on the feedbacks and discussion.
    - Entrance criteria: completion of requirement gathering.
    - Exit criteria: design documention, class diagram, component diagram, deployment diagram, and use-case diagram. 

3. Implementation
    - This process involves the design of the user interface and realization of the design diagrams.
    - Entrance criteria: completion of the software design
    - Exit criteria: Successful completion of a runnable and error-free software product.

4. Testing and Validation
    - This testing process involves creating the test plan and performing the testing. The validation process involves reviewing the requirement documents and verifying if all requirements are satisfied.
    - Entrance criteria: completion of the implementation phase and the test plan document
    - Exit criteria: all tests are passed and all user requirements are fullfilled.       

5. Documentation
    - This activity involves documenting any change to the design during the process of implementation or testing, update the design diagrams if there is architectural change. 
    - Entrance criteria: completion of each stage.
    - Exit criteria: Successful documenting all changes and updates. 

## 3 Team

1. Project Manager
    - Responsible for managing the entire development process, including ensuring work is evenly divided, setting timelines, submitting deliverables.
    - @rsaeidpo3 in our group took over this responsiblity

2. Developer Number 1
    - Responsible for the implementation of the software design, UI, and Tester
    - @kwong93 in our group took over this responsiblity

3. Developer Number 2
    - Responsible for the implementation of the software design, UI, and Tester
    - @wqian40 in our group took over this responsiblity

4. Documentation
    - Responsible for generating documents and deliverables. 
    - @lchen609 in our team took over this responsiblity


Team Member | Tentitive Roles
--- | --- |
Linzi Chen | Documentation, QA
Wendy Qian | Developer, UI Designer, Tester
Reza Saeidpourazer | Project Manager, QA, Documentation
Kevin Wong | Developer, UI Designer, Tester

## 4 Project Scheduale

Team drafted an initial project schedual, and throught the project revised it based on finsings, and new scopes. Below is our project schedual for deliverable number 2 which was due July 5th:

1.	Wendy, 6/28: Create Android App project and upload it in the Git repo
2.	Wendy, 6/28 to 6/29 (Number 1 above should be done before this): Create “Main Menu” layout, with placeholder for following tasks:
    a.	When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).
3.	Wendy, 6/28 to 6/29 (Number 2 above should be done before this): Setup data base for the android project in order to satisfy the following:
    a.	Your application state should persist between runs. You can accomplish this by leveraging Android SQLite (recommended) or any other technology with which you are comfortable (including plain files).
4.	Wendy, 6/28 to 6/29 (Number 2 above should be done before this): Create “enter current job” menu layout and functionality that satisfy the following:
    a.	Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
        i.	Title
        ii.	Company
        iii.	Location (entered as city and state)
        iv.	Cost of living in the location (expressed as an index)
        v.	Yearly salary
        vi.	Yearly bonus
        vii.	Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
        viii.	Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
        ix.	Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0)
        b.	Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
5.	Kevin, 6/29 to 7/1 (Number 2 above should be done before this): Create “enter job offers” menu layout and functionality that satisfy the following:
    a.	Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
    b.	Be able to either save the job offer details or cancel.
    c.	Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
6.	Kevin, 6/29 to 7/1 (Number 2 above should be done before this): Create “adjusting the comparison settings” menu layout and functionality that satisfy the following, the user can assign integer weights to below parameters, If no weights are assigned, all factors are considered equal:
    1.	Yearly salary
    2.	Yearly bonus
    3.	Allowed weekly telework days
    4.	Leave time
    5.	Shares offered
7.	Linzi (Kevin started), 6/29 to 7/4 (Number 2 above should be done before this, ideally Number 4, 5 and 6 need to be done before this as well, but because of time limitation we need to perform this task concurrently with them): Add full functionality to “Main Menu” layout created in Number 2 above and create “compare Job Offer” menu layout and functionality that satisfy the following, the user will:
    a.	Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
    b.	Select two jobs to compare and trigger the comparison.
    c.	Be shown a table comparing the two jobs, displaying, for each job:
        i.	Title
        ii.	Company
        iii.	Location
        iv.	Yearly salary adjusted for cost of living
        v.	Yearly bonus adjusted for cost of living
        vi.	Allowed weekly telework days
        vii.	Leave time
        viii.	Number of shares offered
    d.	Be offered to perform another comparison or go back to the main menu.
    e.	When ranking jobs, a job’s score is computed as the weighted sum of…(See Assignment 5 for details)
8.	Wendy, 7/2 (Number 1 to 7 above should be done before this): Create tester and present test results for requirements number 3 and 4 above
9.	Linzi (Kevin started), 7/4 (Number 1 to 7 above should be done before this): Create tester and present test results for requirements number 5,6 and 7 above
10.	Linzi, 6/28 to 7/1: (Number 9 to 10 above should be done before this): Revise document Test Plan
11.	Linzi, 6/28 to 7/1: prepare User manual and put this in an MD file called UserManual.md
12.	Reza, 7/1 to 7/3: Create Sequence Diagram based on Number 1 to 7 above
13.	Reza, 7/1 to 7/3: Revise Design Document based on Number 1 to 7 above
14.	Reza, 7/1 to 7/3: Revise Extra Requirement document based on Number 1 to 7 above
15.	Reza, 7/1 to 7/3: Revise Project Plan Document based on Number 1 to 7 above
16.	Reza, 7/1 to 7/3: Revise Use Case Diagram and Use Case Model Document based on Number 1 to 7 above
17.	Reza, Linzi, Kevin, Wendy, 7/4 at 12 PM EST, (Number 1 to 16 above should be done before this): Clone the repo in a separate directory/workspace and compile and run the project from there, in a clean AVD. Run the test cases plus additional tests. Give green light that the code is ready to submit. Review the docs User Manual, Test Plan, Sequence Diagram, Design Document, Extra Requirement, Project Plan, Use Case Diagram and Use case Model Document, and suggest any necessary changes 
18.	Reza, 7/4 at 9 PM EST (Number 1 to 18 above should be done before this): Make sure all the suggested changes are applied, submit the document.

Below is our project schedual for deliverable number 3 which was due July 12th:

1.	Wendy, 7/4: removing the menu option for comparing jobs if no job offer exists
2.	Wendy, 7/4 to 7/8: Create test for task number 1
3.	Wendy, 7/4 to 7/8: QA and add additional test if necessary for “setting up project data base task”
4.	Wendy, 7/4 to 7/8: QA and add additional test if necessary for “enter current job” menu
5.	Wendy, 7/4 to 7/8: QA and add additional test if necessary for “Main Menu” 
6.	Kevin, 7/4 to 7/8: Create tests for “enter job offers” menu
7.	Kevin, 7/4 to 7/8: QA for “enter job offers” menu
8.	[Out of Scope – Not Implemented] Kevin, 7/4 to 7/8: (Optional) edit/remove job offer functionality 
9.	Kevin, 7/4 to 7/8: QA and add additional test if necessary for “adjusting the comparison settings” 
10.	Linzi, 7/4 to 7/8: Create tests for “compare Job Offer” menu
11.	Linzi, 7/4 to 7/8: QA for “compare Job Offer” menu
12.	Linzi, 7/8 to 7/10: Add an APK file for our app in directory <dir>/APK
13.	Linzi, 7/8 to 7/10: Revise document Test Plan and present final test results based on tests added above
14.	Linzi, 7/8 to 7/10: Revise document UserManual.md based on changes made above
15.	Reza, 7/8 to 7/10: Create new set of Sequence Diagrams based on changes made above
16.	Reza, 7/8 to 7/10: Create a new Use Case Diagram (if necessary) based on changes made above
17.	Reza, 7/8 to 7/10: Create a new Class Diagram along with all variables and methods based on changes made above
18.	Reza, 7/8 to 7/10: Revise document “Use Case Model” based on changes made above
19.	Reza, 7/8 to 7/10: Revise document “Project Plan” based on changes made above
20.	Reza, 7/8 to 7/10: Revise document “Extra Requirements” based on changes made above
21.	Reza, 7/8 to 7/10: Revise document “Design Document” based on changes made above
22.	Reza, Linzi, Kevin, Wendy, 7/9 at 9 PM EST: Clone the repo in a separate directory/workspace and compile and run the project from there, in a clean AVD. Run the test cases plus additional tests. Give green light that the code is ready to submit. 
23.	Reza, Linzi, Kevin, Wendy, 7/11 at 12 PM EST: Review the docs User Manual, Test Plan, Sequence Diagram, Design Document, Extra Requirement, Project Plan, Use Case Diagram and Use case Model Document, and suggest any necessary changes 
24.	Reza, 7/11 at 9 PM EST: Make sure all the suggested changes are applied, submit the document.