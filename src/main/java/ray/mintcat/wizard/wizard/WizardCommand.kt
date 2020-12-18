package ray.mintcat.wizard.wizard

import io.izzel.taboolib.module.command.base.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import ray.mintcat.wizard.Wizard

@BaseCommand(name = "wizard", aliases = ["wiz"], permission = "Wizard.wizard.command")
class WizardCommand : BaseMainCommand() {
    override fun getCommandTitle(): String {
        return ""
    }

    @SubCommand
    var look: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "查看变量"
        }

        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("变量名"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage(Wizard.getTitle() + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            sender.sendMessage(Wizard.getTitle()
                    + "§7目标 §f" + args[0] + " §7的 §f"
                    + args[1] + "§7 变量为§f "
                    + WizardObject.getIntegral(player, args[1], "不存在").toString())
        }
    }

    @SubCommand
    var set: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "设置变量"
        }

        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("变量名"), Argument("参数"), Argument("是否是数字",false))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage(Wizard.getTitle() + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            WizardObject.setIntegral(player, args[1], args[2], args[3])
        }
    }

    @SubCommand
    var add: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "增加变量"
        }

        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("变量名"), Argument("参数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage(Wizard.getTitle() + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            WizardObject.addIntegral(player, args[1], args[2].toInt())
        }
    }

    @SubCommand
    var take: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "减少变量"
        }

        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("变量名"), Argument("参数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage(Wizard.getTitle() + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            WizardObject.takeIntegral(player, args[1], args[2].toInt())
        }
    }
}