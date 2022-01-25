package edu.gatech.seclass.jobcompare6300;

import androidx.room.*;

import java.util.List;

@Dao
public interface JobDao {
    @Query("SELECT * FROM job")
    List<Job> getAll();

    @Query("SELECT * FROM job WHERE jid = :jobID")
    Job getByID(String jobID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertJobs(Job... jobs);

    @Delete
    void delete(Job jobs);
}
