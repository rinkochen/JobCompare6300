# Assignment 5 Design Description

**1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).**

Within the design of the application, a user class has been defined to interact with the main menu. From the requirements of a single-user application there will only be at most 1 user using the application at a time. They will then interact with the main menu. Through this main menu there will be four option relating to what was defined in the above requirements.

**2. When choosing to enter current job details, a user will:
  a.Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:**
  
    Title
    Company
    Location (entered as city and state)
    Cost of living in the location (expressed as an index)
    Yearly salary
    Yearly bonus
    Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
    Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
    Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0)
    
  **b.Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.**

When entering current job details, the main menu will call a function that will create a new job object representing the current job of the user. The job class will be used when creating potential job offers as they share the same attributes as the user's current job. The job's attributes will fulfill the requirements laid out in the design with their respective data types. Once the job is saved it will then update the currentJob attribute stored in the MainMenu object. If user wishes to cancel theiir current configuration, the details will not be saved and they will return back to the main menu.

**3. When choosing to enter job offers, a user will:
  a.Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
  b.Be able to either save the job offer details or cancel.
  c.Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).**
  
Job offers will be stored in a list of job offers in the main menu object. A function will be called which will then create a new job object that will contain all the attributes that users input. Through implementation of the user interface, they will then be presented with otpions to enter more offers, return to the main menu, or compare the offer with the current user job if the requirements are met.

**4. When adjusting the comparison settings, the user can assign integer weights to:**

    Yearly salary
    Yearly bonus
    Allowed weekly telework days
    Leave time
    Shares offered
  
**If no weights are assigned, all factors are considered equal.**

A comparison class is made to store the weights that users define. When the function is called from the main menu to set these weights, the attributes will be set with the weights that users will input.

**5. When choosing to compare job offers, a user will:**<br />
  **a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.**<br />
  **b. Select two jobs to compare and trigger the comparison.**<br />
  **c. Be shown a table comparing the two jobs, displaying, for each job:**<br />
  
    Title
    Company
    Location
    Yearly salary adjusted for cost of living
    Yearly bonus adjusted for cost of living
    Allowed weekly telework days
    Leave time
    Number of shares offered
  **d. Be offered to perform another comparison or go back to the main menu.**

From the jobOffers list attribute in the main menu, users will be able to peruse the list of offers to compare. When a job is selected a function that takes in the two jobs to compare is called. With the job objects passed in, getter functions will be able to fetch the attributes of the jobs to compared within the implementation of the application. 

**6. When ranking jobs, a job’s score is computed as the weighted sum of:**

**AYS + AYB + CSO/4 + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)**

The comparison to rank jobs will be done within the compareJobOffers method.

**7. The user interface must be intuitive and responsive.**

This will be accomplished once development of the application starts as the team will insure intuitiveness and responsiveness are met.

**8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).**

With a single user application such as this, we will only be concerned about one user and also not worry about transferrign data between multiple devices.

