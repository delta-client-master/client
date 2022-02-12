# client

This readme for now is going to be to keep track of a few things

The way the client will work is just by abstraction, Most of our mods will be written only once in the common module.
This will be supported by "Bridges", A bridge is just an interface around a Minecraft class like "IMinecraftClientBridge" will have methods we need and those methods will be implemented by mixins per version