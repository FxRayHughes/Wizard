package ray.mintcat.wizard.wizard

import org.bukkit.entity.Player
import java.sql.PreparedStatement

object WizardObject {
    var getPlayer: PreparedStatement = WizardSql.CONNECTION.prepareStatement("SELECT Value FROM Wizard WHERE PlayerName = ?")
    var setPlayer: PreparedStatement = WizardSql.CONNECTION.prepareStatement("UPDATE Wizard SET Value = ? WHERE PlayerName = ?")


    fun getIntegral(player: Player?): Any {
        getPlayer.setString(1,player?.name)
        val executeQuery = getPlayer.executeQuery()
        return executeQuery.getString("Value")
    }

    fun setIntegral(player: Player, integral: Any, value: Any) {
        setPlayer.setInt(1, value as Int)
        setPlayer.setString(2,player.name)
        setPlayer.executeUpdate()
    }

    fun addIntegral(player: Player, value: Int) {
        getPlayer.setString(1,player.name)
        setPlayer.setInt(1,getPlayer.executeQuery().getInt("Value")+value)
        setPlayer.setString(2,player.name)
        setPlayer.executeUpdate()
    }

    fun takeIntegral(player: Player, value: Int) {
        getPlayer.setString(1,player.name)
        setPlayer.setInt(1,getPlayer.executeQuery().getInt("Value")-value)
        setPlayer.setString(2,player.name)
        setPlayer.executeUpdate()
    }
}