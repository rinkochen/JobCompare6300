package edu.gatech.seclass.jobcompare6300;

import androidx.room.*;

import java.util.List;

@Dao
public interface ComparisonSettingsDao {
    @Query("Select * FROM ComparisonSettings")
    List<ComparisonSettings> getAll();

    @Query("Select * FROM ComparisonSettings WHERE cid = :settingsID")
    ComparisonSettings getCID(String settingsID);

    @Update
    void updateComparisonSettings(ComparisonSettings... comparisonSettings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComparisonSettings(ComparisonSettings... comparisonSettings);

}
