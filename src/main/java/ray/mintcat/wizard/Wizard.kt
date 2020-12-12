package ray.mintcat.wizard

import io.izzel.taboolib.loader.Plugin
import io.izzel.taboolib.module.config.TConfig
import io.izzel.taboolib.module.inject.TInject
import ray.mintcat.wizard.wizard.WizardSql

object Wizard : Plugin() {
    // 数据库配置文件释放
    @TInject(value = ["SqlConfig.yml"], locale = "LOCALE-PRIORITY")
    lateinit var sqlcofnig: TConfig
        private set

    fun getTitle(): String {
        return "§7§l[§f§lWizard§7§l] "
    }

    fun getVersion(): String {
        return plugin.description.version
    }

    override fun onEnable() {
        //  初始化数据库链接
        WizardSql
    }
}