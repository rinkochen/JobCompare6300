package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the database
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private JobDao jobDao;
    private MainMenuDao mainMenuDao;
    private ComparisonSettingsDao comparisonSettingsDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        jobDao = db.jobDao();
        mainMenuDao = db.mainMenuDao();
        comparisonSettingsDao = db.comparisonSettingsDao();
    }

    @After
    public void closeDb() {
        db.clearAllTables();
        db.close();
    }

    @Test
    public void writeJobAndRetrieve() {
        Job job = new Job("title", "company", "location", 1, 2, 3, 4, 5, 6);
        jobDao.insertJobs(job);
        Job retrievedJob = jobDao.getByID(job.getJid());

        assertEquals(job.getJid(), retrievedJob.getJid());
        assertEquals(job.getTitle(), retrievedJob.getTitle());
        assertEquals(job.getCompany(), retrievedJob.getCompany());
        assertEquals(job.getLocation(), retrievedJob.getLocation());
        assertEquals(job.getCostOfLiving(), retrievedJob.getCostOfLiving());
        assertEquals(job.getYearlySalary(), retrievedJob.getYearlySalary());
        assertEquals(job.getYearlyBonus(), retrievedJob.getYearlyBonus());
        assertEquals(job.getAllowedTeleworkDays(), retrievedJob.getAllowedTeleworkDays());
        assertEquals(job.getLeaveTime(), retrievedJob.getLeaveTime());
        assertEquals(job.getSharedOffered(), retrievedJob.getSharedOffered());
    }

    @Test
    public void writeMultipleAndDelete() {
        Job job1 = new Job("title", "company", "location", 1, 2, 3, 4, 5, 6);
        Job job2 = new Job("title2", "company", "location", 1, 2, 3, 4, 5, 6);
        Job job3 = new Job("title2", "company", "location", 1, 2, 3, 4, 5, 6);
        jobDao.insertJobs(job1, job2, job3);
        List<Job> jobList = jobDao.getAll();

        assertEquals(jobList.size(), 3);

        // Delete one of the three
        jobDao.delete(job2);
        jobList = jobDao.getAll();
        assertEquals(jobList.size(), 2);
    }

    @Test
    public void writeComparisonSettingAndRetrieve() {
        ComparisonSettings comparisonSettings = new ComparisonSettings(1,1,1,1,1);
        comparisonSettingsDao.insertComparisonSettings(comparisonSettings);
        ComparisonSettings retrievedSettings = comparisonSettingsDao.getCID(comparisonSettings.getCid());

        assertEquals(comparisonSettings.getCid(), retrievedSettings.getCid());
        assertEquals(comparisonSettings.getYearlySalaryWeight(), retrievedSettings.getYearlySalaryWeight());
        assertEquals(comparisonSettings.getYearlyBonusWeight(), retrievedSettings.getYearlyBonusWeight());
        assertEquals(comparisonSettings.getAllowedTeleworkDaysWeight(), retrievedSettings.getAllowedTeleworkDaysWeight());
        assertEquals(comparisonSettings.getLeaveTimeWeight(), retrievedSettings.getLeaveTimeWeight());
        assertEquals(comparisonSettings.getSharesOfferedWeight(), retrievedSettings.getSharesOfferedWeight());

    }

    @Test
    public void writeMainMenuAndRetrieve() {
        List<String> jobIDList = new ArrayList<>();
        jobIDList.add("one");
        jobIDList.add("two");
        MainMenu mainMenu = new MainMenu("jobID", jobIDList);
        mainMenuDao.insertMainMenu(mainMenu);
        List<MainMenu> retrieveMainMenu = mainMenuDao.getAll();

        assertEquals(retrieveMainMenu.size(), 1);
        assertEquals(mainMenu.getMid(), retrieveMainMenu.get(0).getMid());
        assertEquals(mainMenu.getCurrentJobID(), retrieveMainMenu.get(0).getCurrentJobID());
        assertEquals(mainMenu.getListOfJobOfferIDs(), retrieveMainMenu.get(0).getListOfJobOfferIDs());
    }

    @Test
    public void writeMainMenuAndDelete() {
        List<String> jobIDList = new ArrayList<>();
        jobIDList.add("one");
        jobIDList.add("two");
        MainMenu mainMenu = new MainMenu("jobID", jobIDList);
        mainMenuDao.insertMainMenu(mainMenu);
        List<MainMenu> retrieveMainMenu = mainMenuDao.getAll();

        assertEquals(retrieveMainMenu.size(), 1);

        // Delete the main menu row
        mainMenuDao.delete(mainMenu);
        retrieveMainMenu = mainMenuDao.getAll();
        assertEquals(retrieveMainMenu.size(), 0);
    }

    @Test
    public void writeMainMenuAndUpdate() {
        List<String> jobIDList = new ArrayList<>();
        jobIDList.add("one");
        jobIDList.add("two");
        MainMenu mainMenu = new MainMenu("jobID", jobIDList);
        mainMenuDao.insertMainMenu(mainMenu);
        List<MainMenu> retrieveMainMenu = mainMenuDao.getAll();

        // Assert inserted row is as expected
        assertEquals(retrieveMainMenu.size(), 1);
        assertEquals(mainMenu.getMid(), retrieveMainMenu.get(0).getMid());
        assertEquals(mainMenu.getCurrentJobID(), retrieveMainMenu.get(0).getCurrentJobID());
        assertEquals(mainMenu.getListOfJobOfferIDs(), retrieveMainMenu.get(0).getListOfJobOfferIDs());

        String updatedJobID = "updatedJobID";
        mainMenu.setCurrentJobID(updatedJobID);
        mainMenuDao.updateMainMenu(mainMenu);
        retrieveMainMenu = mainMenuDao.getAll();

        // Assert updated row is as expected
        assertEquals(retrieveMainMenu.size(), 1);
        assertEquals(mainMenu.getMid(), retrieveMainMenu.get(0).getMid());
        assertEquals(updatedJobID, retrieveMainMenu.get(0).getCurrentJobID());
        assertEquals(mainMenu.getListOfJobOfferIDs(), retrieveMainMenu.get(0).getListOfJobOfferIDs());
    }
}