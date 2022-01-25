# Use Case Model

**Author**: Team 104

## 1 Use Case Diagram

![Use Case Diagram](UseCaseDiagram.jpeg "Use Case Diagram")

## 2 Use Case Descriptions

Enter or Edit Current Job: 

- Requirements: When the app is started, the user is presented with the main menu, which allows the user to enter or edit current job details
- Pre-conditions: None
- Post-conditions: None
- Scenarios: When choosing to enter current job details, the user will be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of: Title, Company, Location (entered as city and state), Cost of living in the location (expressed as an index), Yearly salary, Yearly bonus, Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5), Leave time (vacation days and holiday and/or sick leave, as a single overall number of days), Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0), and the user will be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

Enter Job Offers: 

- Requirements: When the app is started, the user is presented with the main menu, which allows the user to enter job offers
- Pre-conditions: None
- Post-conditions: None
- Scenarios: When choosing to enter job offers, a user will be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job, will be able to either save the job offer details or cancel, will be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

Adjust Comparison Settings: 

- Requirements: When the app is started, the user is presented with the main menu, which allows the user to adjust the comparison settings
- Pre-conditions: None
- Post-conditions: None
- Scenarios: When adjusting the comparison settings, the user can assign integer weights to, Yearly salary, Yearly bonus, Allowed weekly telework days, Leave time, and Shares offered. If no weights are assigned, all factors are considered equal.

Compare Job Offers: 

- Requirements: When the app is started, the user is presented with the main menu, which allows the user to compare job offers, this feature will be disabled if no job offers were entered yet.
- Pre-conditions: Job Offers should be entered prior to this use case. At least 2 jobs (including current job and job offers), should exist to compare.
- Post-conditions: None
- Scenarios: When choosing to compare job offers, a user will be shown a list of job offers, displayed as Title and Company, ranked from best to worst, and including the current job (if present), clearly indicated. User will select two jobs to compare and trigger the comparison. After user chose to compare 2 job offers, user will be shown a table comparing the two jobs, displaying, for each job Title, Company, Location, Yearly salary adjusted for cost of living, Yearly bonus adjusted for cost of living, Allowed weekly telework days, Leave time, Number of shares offered. After displaying this table, the user will be offered to perform another comparison or go back to the main menu.
