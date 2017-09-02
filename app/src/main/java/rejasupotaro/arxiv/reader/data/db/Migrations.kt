package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

fun migrations(): Array<Migration> = arrayOf(AddPaperSimilaritiesTable())

class AddPaperSimilaritiesTable : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `paper_similarities` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `from_paper_id` INTEGER NOT NULL,
                `to_paper_id` INTEGER NOT NULL,
                `similarity` REAL NOT NULL,
                FOREIGN KEY(`from_paper_id`) REFERENCES `papers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE ,
                FOREIGN KEY(`to_paper_id`) REFERENCES `papers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE
            )
            """)
        db.execSQL("CREATE UNIQUE INDEX `index_paper_similarities_from_paper_id_to_paper_id` ON `paper_similarities` (`from_paper_id`, `to_paper_id`)")
    }
}

