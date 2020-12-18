package ray.mintcat.wizard.wizard

import io.izzel.taboolib.module.inject.THook
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import ray.mintcat.wizard.Wizard
import ray.mintcat.wizard.wizard.WizardObject.getIntegral

@THook
class WizardPlaceholder : PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return "wizard"
    }

    override fun getAuthor(): String {
        return "Ray_Hughes"
    }

    override fun getVersion(): String {
        return Wizard.getVersion()
    }

    override fun persist(): Boolean {
        return true
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {
        if (!player.isOnline) {
            return "N/A"
        }
        val param = params.split("_".toRegex())
        return when (param[0]) {
            "version" -> {
                Wizard.getVersion()
            }
            "info" -> {
                //%konintegral_info_key_def% ?
                getIntegral(player, param[1], param[2]).toString()
            }
            "has" -> {

                //%wizard_has_key_value_yes_no% ?
                //              0   1   2     3   4
                val value = getIntegral(player, param[1], "0") as Int
                if (value >= param[2].toInt()) {
                    param[3]
                } else {
                    param[4]
                }
            }
            "is" -> {

                //%wizard_is_key_value_yes_no% ?
                //              0   1   2     3   4
                val value = getIntegral(player, param[1], param[2]) as String
                if (value.equals(param[2], ignoreCase = true)) {
                    param[3]
                } else {
                    param[4]
                }
            }
            else -> {
                "N/A"
            }
        }
    }
}