package ray.mintcat.wizard.wizard

import org.bukkit.entity.Player
import ray.mintcat.wizard.wizard.database.DatabaseMongodb

object WizardObject {

    fun getIntegral(player: Player?, integral: Any, def: Any): Any {
        val databaseMongodb = DatabaseMongodb()
        val download = databaseMongodb.download(player!!)
//        Wizard.plugin.logger.info("返回值为：integral->$integral def->$def，预计得到值为：integral->$integral def->$def")
        return download["Wizard.list.$integral", def] ?: def
    }

    fun setIntegral(player: Player, integral: Any, value: Any, isNumber: Any) {
        val databaseMongodb = DatabaseMongodb()
        val download = databaseMongodb.download(player!!)
//        Wizard.plugin.logger.info("新增值为：integral->$integral def->$value，预计得到值为：integral->$integral def->$value")
        if (isNumber.equals("true")){
//            Wizard.plugin.logger.info("正在用整数方法储存数据")
            if (value is String){
                download["Wizard.list.$integral"] = value.toInt()
            }
        }else{
//            Wizard.plugin.logger.info("正在用字符串方法储存数据")
            download["Wizard.list.$integral"] = value
        }
    }

    fun addIntegral(player: Player, integral: Any, value: Int) {
        val databaseMongodb = DatabaseMongodb()
        val download = databaseMongodb.download(player!!)
        val info = download.getInt("Wizard.list.$integral", 0)
//        Wizard.plugin.logger.info("玩家当前值为：$info，新增值为：integral->$integral def->$value，预计得到值为：integral->${info + value}")
        download["Wizard.list.$integral"] = info + value
    }

    fun takeIntegral(player: Player, integral: Any, value: Int) {
        val databaseMongodb = DatabaseMongodb()
        val download = databaseMongodb.download(player!!)
        val info = download.getInt("Wizard.list.$integral", 0)
//        Wizard.plugin.logger.info("玩家当前值为：$info，减少值为：integral->$integral def->$value，预计得到值为：${info - value}")
        download["Wizard.list.$integral"] = info - value
    }
}