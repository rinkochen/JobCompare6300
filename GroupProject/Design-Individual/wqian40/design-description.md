# Design Description

Note: Getter and setter operations are not explicitly added in the class diagram to 
reduce verbosity, but they are implicit and included for all attributes

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  
    - **To realize the requirement for "(1) enter or edit current job details", 
      I added the `createCurrentJob` operation and `updateCurrentJob` operation to the `Entry` class. 
      The attributes for the current job details are entered in by the user in prompts in the GUI, which
      create or update the `Job` class and associates that with the `currentJob` attribute in the `User` class.**
   - **To realize the requirement for "(2) enter job offers",
     I added the `createJob` operation as part of the `Entry` class.
     The attributes for the job details are entered in by the user in prompts in the GUI, which
     create an instance of the `Job` class and adds that object to the `jobOffers` attribute in the `User` class.**
   - **To realize the requirement for "(3) adjust the comparison settings",
     I added the `getDefaultWeight` operation in the `Entry` class to get
     the default, evenly weighted integers across the five comparison factors.
     Also, the `updateWeight` operation is in the `Entry` class to allow the user
     to adjust the comparison settings in the GUI, which updates the `Weight` class and 
     associates that with the `weight` attribute in the `User` class.**
   - **To realize the requirement for "(4) compare job offers (disabled if no job offers were entered yet)",
     I added the `compareJobs` operation as part of the `Entry` class which
     will check if a user has job offers entered, and if so allow the user to
     rank a list of job offers from best to worst including the user's current job, 
     and each of the job's title and company will be extracted to be shown to the user in the GUI.**

2. When choosing to enter current job details, a user will:
    - Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
        - Title
        - Company
        - Location (entered as city and state)
        - Cost of living in the location (expressed as an index)
        - Yearly salary
        - Yearly bonus
        - Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
        - Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
        - Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0)
       - **To realize this requirement, I added the `title`, `company`, `location`, `yearlySalary`,
         `yearlyBonus`, `weeklyTeleworkDays`, `yearlyLeaveDays`, and `numberShares` attributes to the `Job` class
         which the user will be able to enter and edit the current job through the GUI by calling the `createCurrentJob`
         and `updateCurrentJob` operations from the `Entry` class.**
    - Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
        - **This requirement does not directly affect the design since it's managed by the GUI, so it isn't 
          shown in the class diagram.**
3. When choosing to enter job offers, a user will:
    - Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
        - **To realize this requirement, the user will be able to enter job offers through the GUI by calling the `createJobOffer`
          operation from the `Entry` class.**
    - Be able to either save the job offer details or cancel.
        - **This requirement does not directly affect the design since it's managed by the GUI, so it isn't
          shown in the class diagram.**
    - Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
        - **To realize this "enter another offer" requirement, the user will be able to enter job offers through the GUI by calling the `createJobOffer`
          operation again from the `Entry` class. The "return to the main menu" requirement does not directly 
          affect the design since it's managed by the GUI, so it's not shown in the class diagram. To realize  the "compare the offer
          (if they saved it) with the current job details (if present)" requirement, the user will be able to
          compare job offers through the `compareJobs` operation from the `Entry` class and see the results in the GUI.**
4. When adjusting the comparison settings, the user can assign integer weights to:
    - Yearly salary
    - Yearly bonus
    - Allowed weekly telework days
    - Leave time
    - Shares offered
    - If no weights are assigned, all factors are considered equal.
    - **To realize this requirement, I added the attributes `yearlySalary`, `yearlyBonus`, 
      `weeklyTeleworkDays`, `yearlyLeaveDays`, and `numberShares` to the `Weight` class. The weight
      has default values that are evenly spread across the weight attributes, and the default
      values can be shown through the `getDefaultWeight` operation in the `Entry` class. 
      The user can assign integer weights through the `updateWeight` operation in the `Entry` class
      through the GUI.**
5. When choosing to compare job offers, a user will:
    - Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
       - **See number 6 below for details for how this requirement would be realized.**
    - Select two jobs to compare and trigger the comparison.
        - **To realize this requirement, the user will be able to trigger the job comparison through the GUI by calling the `compareJobs`
          operation from the `Entry` class.**
    - Be shown a table comparing the two jobs, displaying, for each job:
        - Title
        - Company
        - Location
        - Yearly salary adjusted for cost of living
        - Yearly bonus adjusted for cost of living
        - Allowed weekly telework days
        - Leave time
        - Number of shares offered
      - **To realize this requirement, the user will be able to see details from the jobs compared in the GUI from calling the `compareJobs`
        operation in `Entry` class is that the operation returns an array of `Job` which the 
        GUI can parse and then display to the user.**
    - Be offered to perform another comparison or go back to the main menu.
        - **To realize this requirement, the user will be able to trigger another job comparison through the GUI by calling the `compareJobs`
          operation from the `Entry` class.**
6. When ranking jobs, a job’s score is computed as the weighted sum of:
    - AYS + AYB + CSO/4 + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
       - where:
         - AYS = yearly salary adjusted for cost of living
         - AYB = yearly bonus adjusted for cost of living 
         - CSO = Company shares offered (assuming a 4-year vesting schedule and a price-per-share of $1)
         - LT = leave time
         - RWT = telework days per week
        - The rationale for the RWT subformula is:
            - value of an employee hour = (AYS / 260) / 8
            - commute hours per year (assuming a 1-hour/day commute) =
    1 * (260 - 52 * RWT)
            - therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8
        - For example, if the weights are 2 for the yearly salary, 2 for the shares offered, and 1 for all other factors, the score would be computed as:
            - 2/7 * AYS + 1/7 * AYB + 2/7 * (CSO/4) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)
    - **To realize this requirement, the `User` class contains the `getRankedOffers()` operation
      which will compute the above formula by retrieving job details in the `Job` class through
      getters, retrieving respective job location cost of living details through
      getters, and retrieving weight details in the `Weight` class through getters.**
7. The user interface must be intuitive and responsive.
   - **This requirement does not directly affect the design since it's managed by the GUI, so it isn't
   shown in the class diagram.**
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
   - **This requirement does not directly affect the design since it's managed by the GUI, however
     the simplicity of the system is shown through the `Entry` class which is the entry point to the system.**
