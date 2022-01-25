package edu.gatech.seclass.jobcompare6300;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class ComparisonSettings {

    @PrimaryKey
    @NonNull
    private String cid;

    @ColumnInfo(name = "Yearly Salary Weight")
    private int yearlySalaryWeight;

    @ColumnInfo(name = "Yearly Bonus Weight")
    private int yearlyBonusWeight;

    @ColumnInfo(name = "Allowed Weekly Telework Days Weight")
    private int allowedTeleworkDaysWeight;

    @ColumnInfo(name = "Leave Time Weight")
    private int leaveTimeWeight;

    @ColumnInfo(name = "Shares Offered Weight")
    private int sharesOfferedWeight;

    public ComparisonSettings(){
        cid = UUID.randomUUID().toString();
    }

    public ComparisonSettings(int yearlySalaryWeight, int yearlyBonusWeight, int allowedTeleworkDaysWeight, int leaveTimeWeight, int sharesOfferedWeight){
        this.cid = UUID.randomUUID().toString();
        this.yearlySalaryWeight = yearlySalaryWeight;
        this.yearlyBonusWeight = yearlyBonusWeight;
        this.allowedTeleworkDaysWeight = allowedTeleworkDaysWeight;
        this.leaveTimeWeight = leaveTimeWeight;
        this.sharesOfferedWeight = sharesOfferedWeight;
    }

    @NonNull
    public String getCid(){
        return cid;
    }

    public void setCid(@NonNull String cid){
        this.cid = cid;
    }

    public int getYearlySalaryWeight(){
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight){
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight(){
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight){
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public int getAllowedTeleworkDaysWeight(){
        return allowedTeleworkDaysWeight;
    }

    public void setAllowedTeleworkDaysWeight(int allowedTeleworkDaysWeight){
        this.allowedTeleworkDaysWeight = allowedTeleworkDaysWeight;
    }

    public int getLeaveTimeWeight(){
        return leaveTimeWeight;
    }

    public void setLeaveTimeWeight(int leaveTimeWeight){
        this.leaveTimeWeight = leaveTimeWeight;
    }

    public int getSharesOfferedWeight(){
        return sharesOfferedWeight;
    }

    public void setSharesOfferedWeight(int sharesOfferedWeight){
        this.sharesOfferedWeight = sharesOfferedWeight;
    }


}
