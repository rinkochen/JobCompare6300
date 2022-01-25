package edu.gatech.seclass.jobcompare6300;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Job.class, MainMenu.class, ComparisonSettings.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract JobDao jobDao();
    public abstract MainMenuDao mainMenuDao();
    public abstract ComparisonSettingsDao comparisonSettingsDao();
}
