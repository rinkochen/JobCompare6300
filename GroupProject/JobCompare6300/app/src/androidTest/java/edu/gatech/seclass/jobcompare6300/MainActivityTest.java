package edu.gatech.seclass.jobcompare6300;

import androidx.room.Room;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.contrib.RecyclerViewActions.*;


/**
 * Test for Main Activity screens
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> tActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void EditCurrentJobHappyPath() {
        onView(withId(R.id.enterOrEditCurrentJobButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("CurrentJobTitle"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("Company"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("Location"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("4"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("6"));
        onView(withId(R.id.submitCurrentJobButtonID)).perform(click());
        onView(withId(R.id.enterOrEditCurrentJobButtonID)).check(matches(isClickable()));
        deleteCurrentJob();
    }

    @Test
    public void EditCurrentJobReturnToMainMenu() {
        onView(withId(R.id.enterOrEditCurrentJobButtonID)).perform(click());
        onView(withId(R.id.backToMainMenuButtonID)).perform(click());
        onView(withId(R.id.enterOrEditCurrentJobButtonID)).check(matches(isClickable()));
    }

    @Test
    public void EditCurrentJobErrors() {
        onView(withId(R.id.enterOrEditCurrentJobButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("321"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("string"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("invalid"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("6"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("notNumber"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.submitCurrentJobButtonID)).perform(click());

        // Check that expected errors all show
        onView(withId(R.id.titleID)).check(matches(hasErrorText("Invalid Title")));
        onView(withId(R.id.companyID)).check(matches(hasErrorText("Invalid Company")));
        onView(withId(R.id.locationID)).check(matches(hasErrorText("Invalid Location")));
        onView(withId(R.id.locationCostOfLivingID)).check(matches(hasErrorText("Invalid location cost of living index")));
        onView(withId(R.id.yearlySalaryID)).check(matches(hasErrorText("Invalid yearly salary")));
        onView(withId(R.id.yearlyBonusID)).check(matches(hasErrorText("Invalid yearly bonus")));
        onView(withId(R.id.weeklyTeleworkDaysID)).check(matches(hasErrorText("Invalid weekly telework days")));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).check(matches(hasErrorText("Invalid yearly leave time")));
        onView(withId(R.id.numberCompanySharesID)).check(matches(hasErrorText("Invalid number of company shares")));

        // Check that we're still on the current job screen and not main menu
        onView(withId(R.id.backToMainMenuButtonID)).check(matches(isClickable()));
    }

    @Test
    public void CompareJobOffersDisabledBecauseNoJobOffers() {
        deleteAllJobsAndMainMenu();
        
        onView(withId(R.id.compareJobOffersButtonID)).check(matches(withText("Compare Job Offers (Disabled)")));

        onView(withId(R.id.compareJobOffersButtonID)).perform(click());
        onView(withId(R.id.compareJobOffersButtonID)).check(matches(isClickable()));

        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("JobOfferTitle"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("Company"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("Location"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("4"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("6"));
        onView(withId(R.id.offerSaveJobDetails)).perform(click());
        onView(withId(R.id.returnToMainMenuID)).perform(click());

        onView(withId(R.id.compareJobOffersButtonID)).check(matches(isClickable()));
        onView(withId(R.id.compareJobOffersButtonID)).check(matches(withText("Compare Job Offers")));

        deleteAllJobsAndMainMenu();
    }

    @Test
    public void EnterJobOffersErrors() {
        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("321"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("string"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("invalid"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("6"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("notNumber"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.offerSaveJobDetails)).perform(click());

        // Check that expected errors all show
        onView(withId(R.id.titleID)).check(matches(hasErrorText("Invalid Title")));
        onView(withId(R.id.companyID)).check(matches(hasErrorText("Invalid Company")));
        onView(withId(R.id.locationID)).check(matches(hasErrorText("Invalid Location")));
        onView(withId(R.id.locationCostOfLivingID)).check(matches(hasErrorText("Invalid location cost of living index")));
        onView(withId(R.id.yearlySalaryID)).check(matches(hasErrorText("Invalid yearly salary")));
        onView(withId(R.id.yearlyBonusID)).check(matches(hasErrorText("Invalid yearly bonus")));
        onView(withId(R.id.weeklyTeleworkDaysID)).check(matches(hasErrorText("Invalid weekly telework days")));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).check(matches(hasErrorText("Invalid yearly leave time")));
        onView(withId(R.id.numberCompanySharesID)).check(matches(hasErrorText("Invalid number of company shares")));

        // Check that we're still on the enter job offer details screen and not main menu
        onView(withId(R.id.offerBackToMainMenuButtonID)).check(matches(isClickable()));
    }

    @Test
    public void UpdateComparisonSettingsError() {
        onView(withId(R.id.adjustComparisonSettingsButtonID)).perform(click());
        onView(withId(R.id.yearlySalaryWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.yearlyBonusWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.weeklyTeleworkDaysWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.leaveTimeWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.sharesOfferedWeight)).perform(clearText(), replaceText(""));
        onView(withId(R.id.assignWeightsButton)).perform(click());

        // Check that expected errors all show
        onView(withId(R.id.yearlySalaryWeight)).check(matches(hasErrorText("Invalid weight set for yearly salary")));
        onView(withId(R.id.yearlyBonusWeight)).check(matches(hasErrorText("Invalid weight set for yearly bonus")));
        onView(withId(R.id.weeklyTeleworkDaysWeight)).check(matches(hasErrorText("Invalid weight set for telework days")));
        onView(withId(R.id.leaveTimeWeight)).check(matches(hasErrorText("Invalid weight set for allowed leave time")));
        onView(withId(R.id.sharesOfferedWeight)).check(matches(hasErrorText("Invalid weight set for company shares")));

        // Check that we're still on the update comparison settings screen and not main menu
        onView(withId(R.id.comparisonSettingsCancel)).check(matches(isClickable()));
    }

    @Test
    public void CompareJobOfferWithCurrentJob() {
        deleteAllJobsAndMainMenu();
        onView(withId(R.id.enterOrEditCurrentJobButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Title1"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("gatech"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("GA"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("90"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("90000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.submitCurrentJobButtonID)).perform(click());

        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Title4"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("CompanyC"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("CCC"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("110"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("120000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("15"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerSaveJobDetails)).perform(click());
        onView(withId(R.id.compareOfferID)).perform(click());

        onView(withId(R.id.job1TitleID)).check(matches(withText("Title1")));
        onView(withId(R.id.job2TitleID)).check(matches(withText("Title4")));
        onView(withId(R.id.job1YearlySalaryID)).check(matches(withText("90000")));
        onView(withId(R.id.job2YearlySalaryID)).check(matches(withText("120000")));
        deleteAllJobsAndMainMenu();
    }

    @Test
    public void ChooseTwoJobToCompare() {
        deleteAllJobsAndMainMenu();
        onView(withId(R.id.enterOrEditCurrentJobButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Title1"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("gatech"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("GA"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("90"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("90000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.submitCurrentJobButtonID)).perform(click());

        onView(withId(R.id.enterJobOfferButtonID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Title2"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("CompanyA"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("AAA"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("90"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("100000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("15"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerSaveJobDetails)).perform(click());
        onView(withId(R.id.enterAnotherOfferID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Title3"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("CompanyB"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("BBB"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("100"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("110000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("15"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerSaveJobDetails)).perform(click());
        onView(withId(R.id.enterAnotherOfferID)).perform(click());
        onView(withId(R.id.titleID)).perform(clearText(), replaceText("Title4"));
        onView(withId(R.id.companyID)).perform(clearText(), replaceText("CompanyC"));
        onView(withId(R.id.locationID)).perform(clearText(), replaceText("CCC"));
        onView(withId(R.id.locationCostOfLivingID)).perform(clearText(), replaceText("110"));
        onView(withId(R.id.yearlySalaryID)).perform(clearText(), replaceText("120000"));
        onView(withId(R.id.yearlyBonusID)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.weeklyTeleworkDaysID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.yearlyLeaveTimeDaysID)).perform(clearText(), replaceText("15"));
        onView(withId(R.id.numberCompanySharesID)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.offerSaveJobDetails)).perform(click());
        onView(withId(R.id.returnToMainMenuID)).perform(click());

        onView(withId(R.id.compareJobOffersButtonID)).perform(click());
        //onView(withId(R.id.compareJobOffersButtonID)).perform(click());
        onView(withId(R.id.listOfJobOffersID)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.listOfJobOffersID)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        onView(withId(R.id.compareJobsButtonID)).perform(click());
        onView(withId(R.id.job1TitleID)).check(matches(withText("Title2")));
        onView(withId(R.id.job2TitleID)).check(matches(withText("Title3")));
        onView(withId(R.id.job1YearlySalaryID)).check(matches(withText("100000")));
        onView(withId(R.id.job2YearlySalaryID)).check(matches(withText("110000")));
        onView(withId(R.id.job1ScoreID)).check(matches(withText("1111.0")));
        onView(withId(R.id.job2ScoreID)).check(matches(withText("1000.0")));
        deleteAllJobsAndMainMenu();
    }



    public void deleteCurrentJob() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "job-compare-db").allowMainThreadQueries().build();

        MainMenuDao mainMenuDao = db.mainMenuDao();
        List<MainMenu> mainMenuList = mainMenuDao.getAll();

        JobDao jobDao = db.jobDao();
        if (mainMenuList.size() > 0) {
            MainMenu mainMenu = mainMenuList.get(0);
            String currentJobID = mainMenu.getCurrentJobID();
            Job currentJob = jobDao.getByID(currentJobID);
            jobDao.delete(currentJob);

            mainMenu.setCurrentJobID(null);
            mainMenuDao.updateMainMenu(mainMenu);
        }
    }

    public void deleteAllJobsAndMainMenu() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "job-compare-db").allowMainThreadQueries().build();

        MainMenuDao mainMenuDao = db.mainMenuDao();
        List<MainMenu> mainMenuList = mainMenuDao.getAll();

        for (MainMenu mainMenu: mainMenuList) {
            mainMenuDao.delete(mainMenu);
        }

        JobDao jobDao = db.jobDao();
        List<Job> jobList = jobDao.getAll();
        for (Job job: jobList) {
            jobDao.delete(job);
        }
    }
}