# MOTD Editor – Dynamic & Randomized Server Description Plugin

MOTD Editor is a lightweight Paper/Spigot plugin that generates dynamic, randomized MOTDs for your Minecraft server.  
It supports:
- Random **names**
- Random **messages**
- Multiple `{name}` placeholders inside one message
- Full customization via a simple **JSON configuration file**

Perfect for fun, unpredictable MOTDs that change every time the server list refreshes.

<img width="1247" height="149" alt="grafik" src="https://github.com/user-attachments/assets/1182e235-31cd-423a-bc95-efc1d3e88574" />


## Download

You can download the latest version from the **Releases** page.

## Installation

1. Download the latest `MOTDEditor.jar` from the Releases page.
2. Place the JAR inside your server’s `plugins` folder.
3. Start (or restart) your server.
4. The plugin will automatically generate a folder
   `/plugins/MOTDEditor/`
   with a default configuration file: `motds.json`

The plugin is ready to use immediately with the default settings.

## Configuration

The configuration uses two lists:

- **names** → a list of random names
- **messages** → text templates that can contain one or more `{name}` placeholders

Example `motds.json`:

```json
{
    "names": [
        "Alex Rivers",
        "Jordan Hale",
        "Casey Morgan",
        "Taylor Brooks",
        "Riley Carter",
        "Avery Blake"
    ],
    "messages": [
        "§a{name} §fdiscovered a mysterious cave.",
        "§a{name} §fjust stole a cookie from §a{name}§f.",
        "§a{name} §fand §a{name} teamed up for an adventure.",
        "§a{name} claimed they saw §cHerobrine §fyesterday.",
        "§eBreaking news§f: §a{name} §flost their shovel.",
        "§a{name} §fand §a{name} started a §bdance battle§f!"
    ]
}

```

### Using Color Codes

Minecraft supports classic color/formatting codes using the § symbol.

Example:

- `§a` → light green
- `§c` → red
- `§e` → yellow
- `§b` → aqua
- `§f` → white
- `§l` → bold
- `§o` → italic

A complete list with all color/formatting codes can be found [here](https://minecraft.wiki/w/Formatting_codes#Color_codes)


### How Randomization Works

Every time the server list pings your server, the plugin:

1. Picks a random message
2. Searches the message for `{name}` placeholders
3. Replaces each occurrence with a different random name
4. Sends the final MOTD to the player

If a message uses more `{name}` placeholders than the number of available names, leftover placeholders are left unchanged.

### Updating the Config

After editing motds.json, restart your server or run `/reload confirm`
