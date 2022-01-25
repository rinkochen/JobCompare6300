1. **When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).**
    To realize this requirement, I created a 'Mainmenu' class which serves as an entry point to the system that ties the various pieces together. I added 'checkJobOffer' operation to this class to check the user has entered any job offer. 

2. **When choosing to enter current job details, a user will:**
    * **Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of: Title, Company,Location (entered as city and state), Cost of living in the location (expressed as an index), Yearly salary, Yearly bonus, Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5), Leave time (vacation days and holiday and/or sick leave, as a single overall number of days), Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0)**
    
        To archieve this requirement, I created a 'job' class with attribute 'title', 'company', 'city', 'state', 'costOfLiving', 'yearlySalary', 'yearlyBonus', 'teleworkDays', 'leaveTime', 'sharesOffered' that are entered by the user. I also added 'current job' attribute to indicate if this job is the user's current job. Lastly, tro derived attributes 'adjustedYearlySalary' and 'adjustedYearlyBonus" are calculated within the class.

    * **Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.**

        This requirement does not affect my design, as it is part of the workflow.

3. **When choosing to enter job offers, a user will:**
    * **Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.**

        This requirement is archived by the class 'job' mentioned above, because current job and job offers share the same attributes and operations. The 'current job' attribute is used for indicating whether it is the current job or job offer. 
    * **Be able to either save the job offer details or cancel.**

    * **Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).**
        These requirements do not affect my design, as they are part of the workflow.

4. **When adjusting the comparison settings, the user can assign integer weights to: Yearly salary, Yearly bonus, Allowed weekly telework days, Leave time, Shares offered. If no weights are assigned, all factors are considered equal.**

    To realize this requirement, I created the class 'ComparisonSettings" with the weights of the five factor as attributes, and the default value for those attributes are 1. 

5. **When choosing to compare job offers, a user will:**
    * **Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.**

        This requirement does not affect the design because it is part of the database support layer. 
    * **Select two jobs to compare and trigger the comparison. Be shown a table comparing the two jobs, displaying, for each job: Title, Company, Location, Yearly salary adjusted for cost of living, Yearly bonus adjusted for cost of living, Allowed weekly telework days, Leave time, Number of shares offered.**
        
        To realized this requirement, I created the operation 'compareJobs' in the 'MainMenu' class, and it performs the operation of retrieving and displaying the details of the two jobs selected by user.

    * **Be offered to perform another comparison or go back to the main menu.**

        This requirement does not affect my design, because it is part of the workflow.
    * **When ranking jobs, a job’s score is computed as the weighted sum of: AYS + AYB + CSO/4 + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)**
        The 'jobScore' operation is added to the mainmenu class to realizes this requirement. This class performs two operations: calculate jobscores and rank the jobs based on job scores.

6. **The user interface must be intuitive and responsive.**
    This is not represented in my design, as it will be handled entirely within the GUI implementation.
7. **For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).**
    There is no interaction between devices involved in this design.