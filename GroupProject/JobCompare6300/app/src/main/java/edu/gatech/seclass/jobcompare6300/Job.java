package edu.gatech.seclass.jobcompare6300;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Job {
    @PrimaryKey
    @NonNull
    private String jid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "company")
    private String company;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "cost_of_living")
    private int costOfLiving;

    @ColumnInfo(name = "yearly_salary")
    private int yearlySalary;

    @ColumnInfo(name = "yearly_bonus")
    private int yearlyBonus;

    @ColumnInfo(name = "allowed_telework_days")
    private int allowedTeleworkDays;

    @ColumnInfo(name = "leave_time")
    private int leaveTime;

    @ColumnInfo(name = "shares_offered")
    private int sharedOffered;

    public Job() {
        jid = UUID.randomUUID().toString();
    }

    public Job(String title, String company, String location, int costOfLiving, int yearlySalary, int yearlyBonus, int allowedTeleworkDays, int leaveTime, int sharedOffered) {
        this.jid = UUID.randomUUID().toString();
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLiving = costOfLiving;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.allowedTeleworkDays = allowedTeleworkDays;
        this.leaveTime = leaveTime;
        this.sharedOffered = sharedOffered;
    }

    @NonNull
    public String getJid() {
        return jid;
    }

    public void setJid(@NonNull String jid) {
        this.jid = jid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(int costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public int getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(int yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public int getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(int yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getAllowedTeleworkDays() {
        return allowedTeleworkDays;
    }

    public void setAllowedTeleworkDays(int allowedTeleworkDays) {
        this.allowedTeleworkDays = allowedTeleworkDays;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getSharedOffered() {
        return sharedOffered;
    }

    public void setSharedOffered(int sharedOffered) {
        this.sharedOffered = sharedOffered;
    }
}
