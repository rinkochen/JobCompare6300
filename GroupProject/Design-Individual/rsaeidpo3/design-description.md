**Design Description Document**

Here I concisely describe, for each of the requirements listed, either
(1) how that requirement is realized in my design, or (2) why it does
not directly affect the design and is therefore not shown.

**Requirements:**

1.  When the app is started, the user is presented with the main menu,
    which allows the user to (1) enter or edit current job details, (2)
    enter job offers, (3) adjust the comparison settings, or (4) compare
    job offers (disabled if no job offers were entered
    yet).  >\>\>\>\>\>\>\>\>This is addressed in the class “Main Menu”.
    All 4 functionalities mentioned here are satisfied using
    “Operations” in “Main Menu” class

2.  When choosing to *enter current job details,* a user will:

-   Be shown a user interface to enter (if it is the first time) or edit
    all of the details of their current job >\>\>\>\>\> This is
    addressed in the class “Current Job”. All current job specifications
    like title, company, etc are specified using “attributes” in the
    “current job” class, and user can “enter” or “edit” them using
    “operations in this class.

-   Be able to either save the job details or cancel and exit without
    saving, returning in both cases to the main menu.>\>\>\>\>\>this is
    satisfied using “operations” in the “current job” class

1.  When choosing to *enter job offers,* a user will

-   Be shown a user interface to enter all of the details of the
    offer>\>\>\>\>\> This is addressed in the class “Job offer”. All job
    offer specifications like title, company, etc are specified using
    “attributes” in the “Job Offer” class, and user can “enter” or
    “edit” them using “operations in this class.

<!-- -->

-   Be able to either save the job offer details or
    cancel>\>\>\>\>\>this is satisfied using “operations” in the “Job
    Offer” class

<!-- -->

-   Be able to (1) enter another offer, (2) return to the main menu,
    or (3) compare the offer (if they saved it) with the current job
    details (if present).>\>\>\>\>\>this is satisfied using “operations”
    in the “Job Offer” class

1.  When *adjusting the comparison settings,* the user can assign
    integer *weights* to Yearly salary and other
    attributes>\>\>\>\>\>This is satisfied using the “Comparison
    Setting” Class

2.  When choosing to compare job offers, a user will:

-   Be shown a list of job offers, displayed as Title and Company,
    ranked from best to worst (see below for details), and including the
    current job (if present), clearly indicated.>\>\>\>\>\>This is
    satisfied using the “Compare Job Offers” class and the “operation”
    called “Show list of job offers from best to worst()”.

-   Select two jobs to compare and trigger the
    comparison. >\>\>\>\>\>This is satisfied using the “Compare Job
    Offers” class and the “attributes” called “job 1 to compare” and
    “job 2 to compare”

-   Be shown a table comparing the two jobs, displaying, for each
    job>\>\>\>\>\>This is satisfied using the “Compare Job Offers” class
    and the “operation” called “show a table comparing 2 jobs()”.

-   Be offered to perform another comparison or go back to the main
    menu. >\>\>\>\>\>This is satisfied using the “Compare Job Offers”
    class and the “operation” called “Perform another comparison” and
    “go back to main menu”

3.  When ranking jobs, a job’s score is computed as the weighted sum
    of…>\>\>\>\>\>\>this is satisfied using the class “Calculate job
    score”. All the parameters mentioned in the formula are defined as
    an attribute in this class and the “operation” named “calculate job
    score” will calculate the job score based on these attributes.

4.  The user interface must be intuitive and responsive.>\>\>\>satisfied

5.  For simplicity, you may assume there is a single system running the
    app (no communication or saving between devices is
    necessary).>\>\>\>\>\>satisfied
