package edu.gatech.seclass.jobcompare6300;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "job-compare-db").allowMainThreadQueries().build();

        openMainMenuPage(db);
    }

    protected void openMainMenuPage(AppDatabase db) {
        setContentView(R.layout.activity_main);
        Button compareJobOffersButton = findViewById(R.id.compareJobOffersButtonID);

        // Check if job offers data exists
        boolean jobOffersExist = false;
        MainMenuDao mainMenuDao = db.mainMenuDao();
        List<MainMenu> mainMenuList = mainMenuDao.getAll();
        if (mainMenuList.size() > 0) {
            MainMenu mainMenu = mainMenuList.get(0);
            if (mainMenu != null) {
                List<String> jobOfferList = mainMenu.getListOfJobOfferIDs();
                if (jobOfferList != null && jobOfferList.size() > 0) {
                    jobOffersExist = true;
                }
            }
        }

        if (!jobOffersExist) {
            compareJobOffersButton.setText(R.string.compare_jobs_offers_disabled);
        }

        // Button for entering or editing current job
        Button enterOrEditCurrentButton = findViewById(R.id.enterOrEditCurrentJobButtonID);
        enterOrEditCurrentButton.setOnClickListener(
                v -> openCurrentJobPage(db)
        );

        // Button for adding new job offer
        Button enterJobOfferButton = findViewById(R.id.enterJobOfferButtonID);
        enterJobOfferButton.setOnClickListener(
                v -> openJobOfferPage(db)
        );

        // Button for adjusting comparison settings
        Button adjustComparisonSettingsButton = findViewById(R.id.adjustComparisonSettingsButtonID);
        adjustComparisonSettingsButton.setOnClickListener(
                v -> openComparisonSettingsPage(db)
        );

        // Button for comparing jobs
        if (jobOffersExist) {
            compareJobOffersButton.setOnClickListener(
                    v -> openCompareJobOffersPage(db)
            );
        } else {
            // No job offers exist, so throw an error
            compareJobOffersButton.setOnClickListener(
                    v -> compareJobOffersButton.setError("No job offers")
            );
        }
    }

    protected void openCurrentJobPage(AppDatabase db) {
        setContentView(R.layout.current_job);

        // Get main menu data
        MainMenuDao mainMenuDao = db.mainMenuDao();
        List<MainMenu> mainMenuList = mainMenuDao.getAll();
        MainMenu mainMenu = null;

        // If main menu data has a current job, then get that job
        Job currentJob = null;
        JobDao jobDao = db.jobDao();
        if (mainMenuList.size() > 0) {
            mainMenu = mainMenuList.get(0);
            String currentJobID = mainMenu.getCurrentJobID();
            currentJob = jobDao.getByID(currentJobID);
        }

        // Pre-set fields if a current job has already been filled out
        if (currentJob != null) {
            EditText titleEditText = findViewById(R.id.titleID);
            titleEditText.setText(currentJob.getTitle());

            EditText companyEditText = findViewById(R.id.companyID);
            companyEditText.setText(currentJob.getCompany());

            EditText locationEditText = findViewById(R.id.locationID);
            locationEditText.setText(currentJob.getLocation());

            EditText locationColEditText = findViewById(R.id.locationCostOfLivingID);
            locationColEditText.setText(String.valueOf(currentJob.getCostOfLiving()));

            EditText yearlySalaryEditText = findViewById(R.id.yearlySalaryID);
            yearlySalaryEditText.setText(String.valueOf(currentJob.getYearlySalary()));

            EditText yearlyBonusEditText = findViewById(R.id.yearlyBonusID);
            yearlyBonusEditText.setText(String.valueOf(currentJob.getYearlyBonus()));

            EditText weeklyTeleworkDaysEditText = findViewById(R.id.weeklyTeleworkDaysID);
            weeklyTeleworkDaysEditText.setText(String.valueOf(currentJob.getAllowedTeleworkDays()));

            EditText yearlyLeaveTimeDaysEditText = findViewById(R.id.yearlyLeaveTimeDaysID);
            yearlyLeaveTimeDaysEditText.setText(String.valueOf(currentJob.getLeaveTime()));

            EditText numberCompanySharesEditText = findViewById(R.id.numberCompanySharesID);
            numberCompanySharesEditText.setText(String.valueOf(currentJob.getSharedOffered()));
        }

        // Button to go back to main menu
        Button backToMainMenuButton = findViewById(R.id.backToMainMenuButtonID);
        backToMainMenuButton.setOnClickListener(
                v -> openMainMenuPage(db)
        );

        // Button to submit current job details
        Button submitCurrentJobButton = findViewById(R.id.submitCurrentJobButtonID);
        Job finalCurrentJob = currentJob;
        MainMenu finalMainMenu = mainMenu;
        submitCurrentJobButton.setOnClickListener(
                v -> {
                    Job jobFieldEntryResult = jobFieldsEntryResult();

                    // If no error, then that means job result is not null, so save job details and go back to main menu
                    if (jobFieldEntryResult != null) {
                        // Delete previous current job since new one will overwrite it
                        if (finalCurrentJob != null) {
                            jobDao.delete(finalCurrentJob);
                        }

                        jobDao.insertJobs(jobFieldEntryResult);

                        if (finalMainMenu != null) {
                            finalMainMenu.setCurrentJobID(jobFieldEntryResult.getJid());
                            mainMenuDao.updateMainMenu(finalMainMenu);
                        } else {
                            // Main menu table is empty, so add row
                            MainMenu newMainMenu = new MainMenu();
                            newMainMenu.setCurrentJobID(jobFieldEntryResult.getJid());
                            mainMenuDao.insertMainMenu(newMainMenu);
                        }

                        openMainMenuPage(db);
                    }
                });
    }

    protected void openJobOfferPage(AppDatabase db) {
        setContentView(R.layout.enter_job_offer);

        // Get main menu data
        MainMenuDao mainMenuDao = db.mainMenuDao();
        List<MainMenu> mainMenuList = mainMenuDao.getAll();
        MainMenu mainMenu = null;

        if (mainMenuList.size() > 0) {
            mainMenu = mainMenuList.get(0);
        }

        JobDao jobDao = db.jobDao();

        // Button to submit job offer
        Button enterJobOfferButton = findViewById(R.id.offerSaveJobDetails);
        MainMenu finalMainMenu = mainMenu;
        enterJobOfferButton.setOnClickListener(

                v -> {
                    Job jobOfferEntryResult = jobFieldsEntryResult();

                    if(jobOfferEntryResult != null){
                        jobDao.insertJobs(jobOfferEntryResult);

                        if (finalMainMenu != null) {

                            List<String> jobOfferList = finalMainMenu.getListOfJobOfferIDs();

                            if(jobOfferList != null){
                                jobOfferList.add(jobOfferEntryResult.getJid());
                            }
                            else{
                                jobOfferList = new ArrayList<String>();
                                jobOfferList.add(jobOfferEntryResult.getJid());
                            }


                            finalMainMenu.setListOfJobOfferIDs(jobOfferList);
                            mainMenuDao.updateMainMenu(finalMainMenu);
                        } else {
                            // Main menu table is empty, so add row
                            MainMenu newMainMenu = new MainMenu();
                            List<String> jobOfferList = new ArrayList<>();
                            jobOfferList.add(jobOfferEntryResult.getJid());
                            newMainMenu.setListOfJobOfferIDs(jobOfferList);
                            mainMenuDao.insertMainMenu(newMainMenu);
                        }
                        postJobOfferPage(db, jobOfferEntryResult.getJid());
                    }

                }
        );

        // Button to go back to main menu
        Button backToMainMenuButton = findViewById(R.id.offerBackToMainMenuButtonID);
        backToMainMenuButton.setOnClickListener(
                v -> openMainMenuPage(db)
        );

    }

    protected void postJobOfferPage(AppDatabase db, String offerJID){
        setContentView(R.layout.post_enter_job_offer);

        // Get main menu data
        MainMenuDao mainMenuDao = db.mainMenuDao();
        List<MainMenu> mainMenuList = mainMenuDao.getAll();
        MainMenu mainMenu = null;

        if (mainMenuList.size() > 0) {
            mainMenu = mainMenuList.get(0);
        }

        // Enter another job offer
        Button enterAnotherJobOfferButton = findViewById(R.id.enterAnotherOfferID);
        enterAnotherJobOfferButton.setOnClickListener(
                v -> openJobOfferPage(db)
        );

        // Return to main menu
        Button returnToMainMenuButton = findViewById(R.id.returnToMainMenuID);
        returnToMainMenuButton.setOnClickListener(
                v -> openMainMenuPage(db)
        );

        JobDao jobDao = db.jobDao();
        Job currentJobOffer = null;
        Job jobOffer = null;

        MainMenu finalMainMenu = mainMenu;


        // Compare job offers
        Button compareJobOfferButton = findViewById(R.id.compareOfferID);
        compareJobOfferButton.setOnClickListener(
                v -> {

                    final Job job1 = jobDao.getByID(finalMainMenu.getCurrentJobID());
                    final Job job2 = jobDao.getByID(offerJID);

                    Button button = findViewById(R.id.compareOfferID);

                    if(job1 == null || job2 == null){
                        button.setError("Current Job not present");
                    }

                    postCompareJobOffersPage(db, job1, job2);
                }
        );
    }

    protected void openComparisonSettingsPage(AppDatabase db) {
        setContentView(R.layout.comparison_settings);

        // Get Comparison Settings
        ComparisonSettingsDao comparisonSettingsDao = db.comparisonSettingsDao();
        List<ComparisonSettings> comparisonSettingsList = comparisonSettingsDao.getAll();
        ComparisonSettings comparisonSettings = new ComparisonSettings(1,1,1,1,1);

        // If comparison settings already exists, use that instead
        if(comparisonSettingsList.size() > 0){

            comparisonSettings = comparisonSettingsList.get(0);

        }

        // Pre-set the comparison weights
        if(comparisonSettings != null){
            EditText yearlySalaryEditText = findViewById(R.id.yearlySalaryWeight);
            yearlySalaryEditText.setText(String.valueOf(comparisonSettings.getYearlySalaryWeight()));

            EditText yearlyBonusEditText = findViewById(R.id.yearlyBonusWeight);
            yearlyBonusEditText.setText(String.valueOf(comparisonSettings.getYearlyBonusWeight()));

            EditText weeklyTeleworkDaysEditText = findViewById(R.id.weeklyTeleworkDaysWeight);
            weeklyTeleworkDaysEditText.setText(String.valueOf(comparisonSettings.getAllowedTeleworkDaysWeight()));

            EditText leaveTimeEditText = findViewById(R.id.leaveTimeWeight);
            leaveTimeEditText.setText(String.valueOf(comparisonSettings.getLeaveTimeWeight()));

            EditText sharesOfferedEditText = findViewById(R.id.sharesOfferedWeight);
            sharesOfferedEditText.setText(String.valueOf(comparisonSettings.getSharesOfferedWeight()));
        }

        // Button to Update Comparison Weights
        Button assignWeightsButton = findViewById(R.id.assignWeightsButton);
        ComparisonSettings finalComparisonSettings = comparisonSettings;
        assignWeightsButton.setOnClickListener(
                v -> {

                    ComparisonSettings comparisonSettingsEntryResult = comparisonSettingsEntryResult();

                    if(comparisonSettingsEntryResult != null){
                        comparisonSettingsDao.insertComparisonSettings(comparisonSettingsEntryResult);
                        finalComparisonSettings.setYearlySalaryWeight(comparisonSettingsEntryResult.getYearlySalaryWeight());
                        finalComparisonSettings.setYearlyBonusWeight(comparisonSettingsEntryResult.getYearlyBonusWeight());
                        finalComparisonSettings.setAllowedTeleworkDaysWeight(comparisonSettingsEntryResult.getAllowedTeleworkDaysWeight());
                        finalComparisonSettings.setLeaveTimeWeight(comparisonSettingsEntryResult.getLeaveTimeWeight());
                        finalComparisonSettings.setSharesOfferedWeight(comparisonSettingsEntryResult.getSharesOfferedWeight());
                        comparisonSettingsDao.updateComparisonSettings(finalComparisonSettings);
                        openMainMenuPage(db);
                    }
                });


        // Button to go back to main menu
        Button backToMainMenuButton = findViewById(R.id.comparisonSettingsCancel);
        backToMainMenuButton.setOnClickListener(
                v -> openMainMenuPage(db)
        );
    }

    protected void openCompareJobOffersPage(AppDatabase db) {
        setContentView(R.layout.compare_job_offers);

        // Get main menu data
        MainMenuDao mainMenuDao = db.mainMenuDao();
        List<MainMenu> mainMenuList = mainMenuDao.getAll();
        MainMenu mainMenu = null;

        if (mainMenuList.size() > 0) {
            mainMenu = mainMenuList.get(0);
        }

        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> company = new ArrayList<>();
        ArrayList<Double> score = new ArrayList<>();
        ArrayList<Boolean> current = new ArrayList<>();

        JobDao jobDao = db.jobDao();
        Job currentJobOffer = null;
        Job job1 = null;
        Job job2 = null;

        // Get Comparison Settings
        ComparisonSettingsDao comparisonSettingsDao = db.comparisonSettingsDao();
        List<ComparisonSettings> comparisonSettingsList = comparisonSettingsDao.getAll();
        ComparisonSettings comparisonSettings = new ComparisonSettings(1,1,1,1,1);

        // If comparison settings already exists, use that instead
        if(comparisonSettingsList.size() > 0){
            comparisonSettings = comparisonSettingsList.get(0);
        }


        List<String> listOfJobOffers = mainMenu.getListOfJobOfferIDs();

        ArrayList<String> listOfJobs = (ArrayList<String>) mainMenu.getListOfJobOfferIDs();
        listOfJobs.add(mainMenu.getCurrentJobID());

        // Create lists to store scores
        for(int i = 0; i < listOfJobs.size(); i++){
            String jobOfferId = listOfJobs.get(i);
            score.add(calculateJobScore(jobDao.getByID(jobOfferId), comparisonSettings));
        }

        //sort jobIDs by score
        Map<Double, String> map = new HashMap<Double,String>();
        for (int i = 0; i<score.size(); i++){
            map.put(score.get(i), listOfJobs.get(i));
        }
        Collections.sort(score, Collections.reverseOrder());
        List<String> sortedJobID = new ArrayList<>();
        for (int i = 0; i < map.size(); i++){
            sortedJobID.add(map.get(score.get(i)));
        }

        for(int i = 0; i < sortedJobID.size(); i++){
            String jobOfferId = sortedJobID.get(i);
            currentJobOffer = jobDao.getByID(jobOfferId);

            title.add(currentJobOffer.getTitle());
            company.add(currentJobOffer.getCompany());
            if (jobOfferId==mainMenu.getCurrentJobID()){
                current.add(true);
            } else {current.add(false);}
        }



        // If main menu data has a current job, then get that job
        Job currentJob = null;
        if (mainMenuList.size() > 0) {
            mainMenu = mainMenuList.get(0);
            String currentJobID = mainMenu.getCurrentJobID();
            currentJob = jobDao.getByID(currentJobID);
        }

        // Set current job information
        TextView currentJobTitle = findViewById(R.id.currentJobTitleID);
        currentJobTitle.setText(currentJob.getTitle());

        TextView currentJobCompany = findViewById(R.id.currentJobCompanyID);
        currentJobCompany.setText(currentJob.getCompany());

        TextView currentJobScore = findViewById(R.id.currentJobScoreID);
        currentJobScore.setText(String.valueOf(Math.floor(calculateJobScore(currentJob, comparisonSettings))));

        LinearLayout currentJobListing = findViewById(R.id.currentJobID);



        // Create the list to display all job offers
        RecyclerView recyclerView = findViewById(R.id.listOfJobOffersID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, title, company, score, current);
        recyclerView.setAdapter(adapter);




        // Button to compare selected job offers
        Button compareJobOffersButton = findViewById(R.id.compareJobsButtonID);

        //Check if two job offers are selected
        compareJobOffersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected().size()==2){
                    int job1Position = adapter.getSelected().get(0);
                    int job2Position = adapter.getSelected().get(1);

                    String job1ID = sortedJobID.get(job1Position);
                    String job2ID = sortedJobID.get(job2Position);

                    Job job1 = jobDao.getByID(String.valueOf(job1ID));
                    Job job2 = jobDao.getByID(String.valueOf(job2ID));
                    postCompareJobOffersPage(db, job1, job2);
                } else {
                    compareJobOffersButton.setError("Please select two jobs to compare.");
                }
            }
        });
        // Button to go back to main menu
        Button backToMainMenuButton = findViewById(R.id.returnToMainMenuID);
        backToMainMenuButton.setOnClickListener(
                v -> openMainMenuPage(db)
        );
    }

    protected void postCompareJobOffersPage(AppDatabase db, Job job1, Job job2){
        setContentView(R.layout.post_compare_job_offers);

        // Get Comparison Settings
        ComparisonSettingsDao comparisonSettingsDao = db.comparisonSettingsDao();
        List<ComparisonSettings> comparisonSettingsList = comparisonSettingsDao.getAll();
        ComparisonSettings comparisonSettings = new ComparisonSettings(1,1,1,1,1);

        // If comparison settings already exists, use that instead
        if(comparisonSettingsList.size() > 0){
            comparisonSettings = comparisonSettingsList.get(0);
        }


        if(job1 != null){
            TextView job1Title = findViewById(R.id.job1TitleID);
            job1Title.setText(job1.getTitle());

            TextView job1Company = findViewById(R.id.job1CompanyID);
            job1Company.setText(job1.getCompany());

            TextView job1Location = findViewById(R.id.job1LocationID);
            job1Location.setText(job1.getLocation());

            TextView job1Salary = findViewById(R.id.job1YearlySalaryID);
            job1Salary.setText(String.valueOf(job1.getYearlySalary()));

            TextView job1Bonus = findViewById(R.id.job1YearlyBonusID);
            job1Bonus.setText(String.valueOf(job1.getYearlyBonus()));

            TextView job1Telework = findViewById(R.id.job1TeleworkID);
            job1Telework.setText(String.valueOf(job1.getAllowedTeleworkDays()));

            TextView job1LeaveTime = findViewById(R.id.job1LeaveTimeID);
            job1LeaveTime.setText(String.valueOf(job1.getLeaveTime()));

            TextView job1Share = findViewById(R.id.job1SharesID);
            job1Share.setText(String.valueOf(job1.getSharedOffered()));

            TextView job1Score = findViewById(R.id.job1ScoreID);
            Double job1ScoreResult = calculateJobScore(job1, comparisonSettings);
            job1Score.setText(String.valueOf(Math.floor(job1ScoreResult)));
        }

        if(job2 != null){
            TextView job2Title = findViewById(R.id.job2TitleID);
            job2Title.setText(job2.getTitle());

            TextView job2Company = findViewById(R.id.job2CompanyID);
            job2Company.setText(job2.getCompany());

            TextView job2Location = findViewById(R.id.job2LocationID);
            job2Location.setText(job2.getLocation());

            TextView job2Salary = findViewById(R.id.job2YearlySalaryID);
            job2Salary.setText(String.valueOf(job2.getYearlySalary()));

            TextView job2Bonus = findViewById(R.id.job2YearlyBonusID);
            job2Bonus.setText(String.valueOf(job2.getYearlyBonus()));

            TextView job2Telework = findViewById(R.id.job2TeleworkID);
            job2Telework.setText(String.valueOf(job2.getAllowedTeleworkDays()));

            TextView job2LeaveTime = findViewById(R.id.job2LeaveTimeID);
            job2LeaveTime.setText(String.valueOf(job2.getLeaveTime()));

            TextView job2Share = findViewById(R.id.job2SharesID);
            job2Share.setText(String.valueOf(job2.getSharedOffered()));

            TextView job2Score = findViewById(R.id.job2ScoreID);
            Double job2ScoreResult = calculateJobScore(job2, comparisonSettings);
            job2Score.setText(String.valueOf(Math.floor(job2ScoreResult)));
        }


        // Button to perform another comparison
        Button compareJobOffersButton = findViewById(R.id.performAnotherComparisonID);
        compareJobOffersButton.setOnClickListener(
                v -> openCompareJobOffersPage(db)
        );

        // Button to go back to main menu
        Button backToMainMenuButton = findViewById(R.id.returnToMainMenuID);
        backToMainMenuButton.setOnClickListener(
                v -> openMainMenuPage(db)
        );

    }

    protected Job jobFieldsEntryResult() {
        boolean errorEncountered = false;

        // Role title must be non-empty and contain letters
        EditText titleEditText = findViewById(R.id.titleID);
        String title = titleEditText.getText().toString();
        if (title.equals("") || isLetterless(title)) {
            // Error for empty or letterless entry
            titleEditText.setError("Invalid Title");
            errorEncountered = true;
        }

        // Company name must be non-empty and contain letters
        EditText companyEditText = findViewById(R.id.companyID);
        String company = companyEditText.getText().toString();
        if (company.equals("") || isLetterless(company)) {
            // Error for empty or letterless entry
            companyEditText.setError("Invalid Company");
            errorEncountered = true;
        }

        // Location must be non-empty and contain letters
        EditText locationEditText = findViewById(R.id.locationID);
        String location = locationEditText.getText().toString();
        if (location.equals("") || isLetterless(location)) {
            // Error for empty or letterless entry
            locationEditText.setError("Invalid Location");
            errorEncountered = true;
        }

        // Location cost of living index must be >= 0
        EditText locationColEditText = findViewById(R.id.locationCostOfLivingID);
        String locationColString = locationColEditText.getText().toString();
        if (locationColString.equals("") || hasNonNumber(locationColString) || Integer.parseInt(locationColString) < 0) {
            locationColEditText.setError("Invalid location cost of living index");
            errorEncountered = true;
        }

        // Yearly salary must be >= 0
        EditText yearlySalaryEditText = findViewById(R.id.yearlySalaryID);
        String yearlySalaryString = yearlySalaryEditText.getText().toString();
        if (yearlySalaryString.equals("") || hasNonNumber(yearlySalaryString) || Integer.parseInt(yearlySalaryString) < 0) {
            yearlySalaryEditText.setError("Invalid yearly salary");
            errorEncountered = true;
        }

        // Yearly bonus must be >= 0
        EditText yearlyBonusEditText = findViewById(R.id.yearlyBonusID);
        String yearlyBonusString = yearlyBonusEditText.getText().toString();
        if (yearlyBonusString.equals("") || hasNonNumber(yearlyBonusString) || Integer.parseInt(yearlyBonusString) < 0) {
            yearlyBonusEditText.setError("Invalid yearly bonus");
            errorEncountered = true;
        }

        // Weekly telework days must be >= 0 or <= 5
        EditText weeklyTeleworkDaysEditText = findViewById(R.id.weeklyTeleworkDaysID);
        String weeklyTeleworkDaysString = weeklyTeleworkDaysEditText.getText().toString();
        if (weeklyTeleworkDaysString.equals("") || hasNonNumber(weeklyTeleworkDaysString)
                || Integer.parseInt(weeklyTeleworkDaysString) < 0
                || Integer.parseInt(weeklyTeleworkDaysString) > 5) {
            weeklyTeleworkDaysEditText.setError("Invalid weekly telework days");
            errorEncountered = true;
        }

        // Yearly leave time must be >= 0
        EditText yearlyLeaveTimeDaysEditText = findViewById(R.id.yearlyLeaveTimeDaysID);
        String yearlyLeaveTimeDaysString = yearlyLeaveTimeDaysEditText.getText().toString();
        if (yearlyLeaveTimeDaysString.equals("") || hasNonNumber(yearlyLeaveTimeDaysString)
                || Integer.parseInt(yearlyLeaveTimeDaysString) < 0) {
            yearlyLeaveTimeDaysEditText.setError("Invalid yearly leave time");
            errorEncountered = true;
        }

        // Number of company shares must be >= 0
        EditText numberCompanySharesEditText = findViewById(R.id.numberCompanySharesID);
        String numberCompanySharesString = numberCompanySharesEditText.getText().toString();
        if (numberCompanySharesString.equals("") || hasNonNumber(numberCompanySharesString)
                || Integer.parseInt(numberCompanySharesString) < 0) {
            numberCompanySharesEditText.setError("Invalid number of company shares");
            errorEncountered = true;
        }

        // Return null Job if an error was encountered above
        if (!errorEncountered) {
            return new Job(title, company, location, Integer.parseInt(locationColString),
                    Integer.parseInt(yearlySalaryString), Integer.parseInt(yearlyBonusString),
                    Integer.parseInt(weeklyTeleworkDaysString), Integer.parseInt(yearlyLeaveTimeDaysString),
                    Integer.parseInt(numberCompanySharesString));
        } else {
            return null;
        }
    }

    protected ComparisonSettings comparisonSettingsEntryResult() {
        boolean errorEncountered = false;

        // Yearly Salary must be a non-negative integer
        EditText yearlySalaryWeightEditText = findViewById(R.id.yearlySalaryWeight);
        String yearlySalaryWeight = yearlySalaryWeightEditText.getText().toString();
        if(yearlySalaryWeight.equals("") || hasNonNumber(yearlySalaryWeight) || Integer.parseInt(yearlySalaryWeight) < 0 || Integer.parseInt(yearlySalaryWeight) > Integer.MAX_VALUE){
            yearlySalaryWeightEditText.setError("Invalid weight set for yearly salary");
            errorEncountered = true;
        }

        // Yearly Bonus must be a non-negative integer
        EditText yearlyBonusWeightEditText = findViewById(R.id.yearlyBonusWeight);
        String yearlyBonusWeight = yearlyBonusWeightEditText.getText().toString();
        if(yearlyBonusWeight.equals("") || hasNonNumber(yearlyBonusWeight) || Integer.parseInt(yearlyBonusWeight) < 0 || Integer.parseInt(yearlyBonusWeight) > Integer.MAX_VALUE){
            yearlyBonusWeightEditText.setError("Invalid weight set for yearly bonus");
            errorEncountered = true;
        }

        // Allowed weekly telework days must be non-negative integer from 0 to 5 inclusive
        EditText allowedTeleworkDaysEditText = findViewById(R.id.weeklyTeleworkDaysWeight);
        String allowedTeleworkDaysWeight = allowedTeleworkDaysEditText.getText().toString();
        if(allowedTeleworkDaysWeight.equals("") || hasNonNumber(allowedTeleworkDaysWeight) || Integer.parseInt(allowedTeleworkDaysWeight) < 0 || Integer.parseInt(allowedTeleworkDaysWeight) > 5 || Integer.parseInt(allowedTeleworkDaysWeight) > Integer.MAX_VALUE){
            allowedTeleworkDaysEditText.setError("Invalid weight set for telework days");
            errorEncountered = true;
        }

        // Leave Time must be non-negative integer
        EditText allowedLeaveTimeEditText = findViewById(R.id.leaveTimeWeight);
        String allowedLeaveTimeWeight = allowedLeaveTimeEditText.getText().toString();
        if(allowedLeaveTimeWeight.equals("") || hasNonNumber(allowedLeaveTimeWeight) || Integer.parseInt(allowedLeaveTimeWeight) < 0 || Integer.parseInt(allowedLeaveTimeWeight) > Integer.MAX_VALUE){
            allowedLeaveTimeEditText.setError("Invalid weight set for allowed leave time");
            errorEncountered = true;
        }

        // Company shares offered must be non-negative integer
        EditText companySharesEditText = findViewById(R.id.sharesOfferedWeight);
        String companySharesWeight = companySharesEditText.getText().toString();
        if(companySharesWeight.equals("") || hasNonNumber(companySharesWeight) || Integer.parseInt(companySharesWeight) < 0 || Integer.parseInt(companySharesWeight) > Integer.MAX_VALUE){
            companySharesEditText.setError("Invalid weight set for company shares");
            errorEncountered = true;
        }


        if(!errorEncountered){
            return new ComparisonSettings(Integer.parseInt(yearlySalaryWeight), Integer.parseInt(yearlyBonusWeight), Integer.parseInt(allowedTeleworkDaysWeight), Integer.parseInt(allowedLeaveTimeWeight), Integer.parseInt(companySharesWeight));
        }
        else{
            return null;
        }

    }

    protected double calculateJobScore(Job job, ComparisonSettings comparisonSettings){

        double yearlySalary = job.getYearlySalary();
        double yearlyBonus = job.getYearlyBonus();
        double CSO = job.getSharedOffered();
        double LT = job.getLeaveTime();
        double RWT = job.getAllowedTeleworkDays();
        double costOfLiving = job.getCostOfLiving();

        double AYS = yearlySalary / costOfLiving * 100;
        double AYB = yearlyBonus / costOfLiving * 100;


        int weightTotal = comparisonSettings.getYearlySalaryWeight() + comparisonSettings.getYearlyBonusWeight() + comparisonSettings.getSharesOfferedWeight() + comparisonSettings.getLeaveTimeWeight() + comparisonSettings.getAllowedTeleworkDaysWeight();

        double score = 0;

        score = (AYS * (comparisonSettings.getYearlySalaryWeight()/weightTotal)) + (AYB * (comparisonSettings.getYearlyBonusWeight())/weightTotal) + (comparisonSettings.getSharesOfferedWeight()/weightTotal)*(CSO/4)
                + (comparisonSettings.getLeaveTimeWeight()/weightTotal) * (LT*AYS/260) - (comparisonSettings.getAllowedTeleworkDaysWeight()/weightTotal) * ((260 - 52 * RWT) * (AYS/260)/8);

        return score;
    }

    protected boolean hasNonNumber(String inputString) {
        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (Character.isLetter(currentChar) || Character.isWhitespace(currentChar)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isLetterless(String inputString) {
        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (Character.isLetter(currentChar)) {
                return false;
            }
        }
        return true;
    }
}