package edu.gatech.seclass.jobcompare6300;

import androidx.room.*;

import java.util.List;

@Dao
public interface MainMenuDao {
    @Query("SELECT * FROM mainmenu")
    List<MainMenu> getAll();

    @Update
    void updateMainMenu(MainMenu... mainMenus);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMainMenu(MainMenu... mainMenus);

    @Delete
    void delete(MainMenu mainMenu);
}
