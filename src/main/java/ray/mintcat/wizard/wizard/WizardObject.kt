package ray.mintcat.wizard.wizard

import org.bukkit.entity.Player
import java.sql.PreparedStatement

object WizardObject {
    var getPlayer: PreparedStatement =
        WizardSql.CONNECTION.prepareStatement("SELECT Value FROM Wizard WHERE PlayerName = ?")
    var setPlayer: PreparedStatement =
        WizardSql.CONNECTION.prepareStatement("UPDATE Wizard SET Value = ? WHERE PlayerName = ?")


    fun getIntegral(player: Player?): Any {
        getPlayer.setString(1, player?.name)
        val executeQuery = getPlayer.executeQuery()
        executeQuery.next()
        return executeQuery.getString("Value")
    }

    fun setIntegral(player: Player, value: Any) {
        setPlayer.setInt(1, value as Int)
        setPlayer.setString(2, player.name)
        setPlayer.executeUpdate()
    }

    fun addIntegral(player: Player, value: Int) {
        getPlayer.setString(1, player.name)
        val executeQuery = getPlayer.executeQuery()
        executeQuery.next()
        setPlayer.setInt(1, executeQuery.getInt("Value") + value)
        setPlayer.setString(2, player.name)
        setPlayer.executeUpdate()
    }

    fun takeIntegral(player: Player, value: Int) {
        getPlayer.setString(1, player.name)
        val executeQuery = getPlayer.executeQuery()
        executeQuery.next()
        setPlayer.setInt(1, executeQuery.getInt("Value") - value)
        setPlayer.setString(2, player.name)
        setPlayer.executeUpdate()
    }
}