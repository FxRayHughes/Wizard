package ray.mintcat.wizard.wizard

import ray.mintcat.wizard.Wizard
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

object WizardSql {
    /**
     * 这部分是SQL数据库链接参数
     */
    var DB_URL: String? = Wizard.sqlcofnig.getString("DB_URL")
    var PORT: String? = Wizard.sqlcofnig.getString("PORT")
    var USER: String? = Wizard.sqlcofnig.getString("USER")
    var PASSWORD: String? = Wizard.sqlcofnig.getString("PASSWORD")
    var TABLE: String? = Wizard.sqlcofnig.getString("TABLE")
    var PARAMETER: String? = Wizard.sqlcofnig.getString("PARAMETER")
    var CONNECTION: Connection =
        DriverManager.getConnection("jdbc:mysql://$DB_URL:$PORT/$TABLE?$PARAMETER", USER, PASSWORD)
    var STATEMENT: Statement? = CONNECTION.createStatement()

    init {
        Wizard.plugin.logger.info("正在尝试链接数据库...")
        if (!CONNECTION.metaData.getTables("$TABLE",null,"Wizard",null).next()){
            STATEMENT?.execute(
                "CREATE TABLE if not exists `Wizard` (\n" +
                        "  `PlayerName` varchar (32) CHARACTER SET `utf8` COLLATE `utf8_general_ci` NOT NULL COMMENT '玩家ID',\n" +
                        "  `Value` int (32) NOT NULL DEFAULT 0 COMMENT '变量值',\n" +
                        "  PRIMARY KEY (`PlayerName`),\n" +
                        "  unique `PlayerNameList` USING btree (`PlayerName`)\n" +
                        ") ENGINE = innodb DEFAULT CHARACTER SET = \"utf8\" COLLATE = \"utf8_general_ci\""
            )
        }
    }
}