package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

fun migrations(): Array<Migration> = arrayOf(AddPaperSimilaritiesTable())

class AddPaperSimilaritiesTable : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `paper_similarities` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `from_paper_id` INTEGER NOT NULL,
                `to_paper_id` INTEGER NOT NULL,
                `similarity` REAL NOT NULL,
                FOREIGN KEY(`from_paper_id`) REFERENCES `papers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE ,
                FOREIGN KEY(`to_paper_id`) REFERENCES `papers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE
            )
            """)
    }
}

