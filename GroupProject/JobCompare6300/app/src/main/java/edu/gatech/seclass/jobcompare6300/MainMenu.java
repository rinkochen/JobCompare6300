package edu.gatech.seclass.jobcompare6300;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Entity
public class MainMenu {
    @PrimaryKey
    @NonNull
    private String mid;

    @ColumnInfo(name = "current_job_id")
    private String currentJobID;

    @ColumnInfo(name = "list_of_job_offer_ids")
    private List<String> listOfJobOfferIDs;

    public MainMenu() {
        mid = UUID.randomUUID().toString();
    }

    public MainMenu(String currentJobID, List<String> listOfJobOfferIDs) {
        this.mid = UUID.randomUUID().toString();
        this.currentJobID = currentJobID;
        this.listOfJobOfferIDs = listOfJobOfferIDs;
    }

    @NonNull
    public String getMid() {
        return mid;
    }

    public void setMid(@NonNull String mid) {
        this.mid = mid;
    }

    public String getCurrentJobID() {
        return currentJobID;
    }

    public void setCurrentJobID(String currentJobID) {
        this.currentJobID = currentJobID;
    }

    public List<String> getListOfJobOfferIDs() {
        return listOfJobOfferIDs;
    }

    public void setListOfJobOfferIDs(List<String> listOfJobOfferIDs) {
        this.listOfJobOfferIDs = listOfJobOfferIDs;
    }
}
