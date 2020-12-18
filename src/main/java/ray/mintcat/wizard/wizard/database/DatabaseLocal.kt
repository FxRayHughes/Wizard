package ray.mintcat.wizard.wizard.database

import io.izzel.taboolib.module.db.local.LocalPlayer
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

/**
 * @Author sky
 * @Since 2020-08-14 14:46
 */
class DatabaseLocal : Database() {

    override fun download(player: Player): FileConfiguration {
        return LocalPlayer.get(player)
    }

    override fun upload(player: Player) {
    }

}