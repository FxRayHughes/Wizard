package ray.mintcat.wizard.wizard

import io.izzel.taboolib.module.inject.TListener
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.scheduler.BukkitRunnable
import ray.mintcat.wizard.Wizard

@TListener
object WizardEvent : Listener {
    @EventHandler
    fun onPlayerLogin(event: PlayerLoginEvent) {
        object : BukkitRunnable (){
            override fun run() {
                object : BukkitRunnable(){
                    override fun run() {
                        var execute = WizardSql.STATEMENT?.executeQuery("SELECT * FROM Wizard WHERE PlayerName='${event.player.name}'")
                        if (execute?.next() == false) {
                            WizardSql.STATEMENT?.execute("insert into Wizard values ('${event.player.name}',0)")
                        }
                    }
                }.runTask(Wizard.plugin)
            }
        }.runTaskAsynchronously(Wizard.plugin)
    }
}