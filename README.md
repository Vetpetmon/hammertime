# Hammertime!
### A mod & API for Minecraft Forge
(1.20.1)
*"Now, pay very close attention to that ham, because this is not the only time it'll appear in the story."*


# Description
Adds hammers to the Vanilla game, along with a way to obtain them. 
**Hammertime!** is a lightweight mod which should have no virtual impact on live performance. 
For mod authors, this mod can be used as an optional dependency to add their own hammers using their tool tiers.

Hammers are like upgraded pickaxes, with a (default) breaking range of 3x3 blocks, which can be disabled by sneaking. 
Additionally, hammers are axe-like weapons, and will deal more damage than a normal axe! (At the cost of being slower.)

**Hammertime!** comes with built-in Better Combat support.

# Adding Your Own Hammers
(Mod devs)

*This guide implies you already have a tool tier defined in your mod and item registration subscribed to.*

Adding your own hammers is quite easy, we'll start by defining our custom hammer in our item registries:
```java
public static final RegistryObject<Item>
        CUSTOM_HAMMER =  ITEMS.register("custom_hammer",  () -> Hammertime.newHammer(Tiers.CUSTOM_TIER));
```
Alternatively, you can create a new hammer directly:
```java
public static final RegistryObject<Item>
        CUSTOM_HAMMER =  ITEMS.register("custom_hammer",  () -> new HammerItem(tier, 8,-2.9f, new Item.Properties().stacksTo(1), 0)); //Modify that last int if you want to go beyond 3x3 mining
```
It's recommended to handle these registries on a conditional statement that checks if ***Hammertime!*** is loaded.

Now, it is registered into the game, but we aren't done yet! In your mod's datapack folder, create a folder named `weapon_attributes`, and create a file inside of it named `custom_hammer.json`, with the following contents:
```json
{
    "parent": "hammertime:hammer"
}
```
***Hammertime!*** provides a combo for Hammer-type weapons for Better Combat.

Once these are done, you can use datagen or manually create the item model in your mod's assets folder, using the `heldhammer` as a parent model.
```json
{
  "parent": "hammertime:item/heldhammer",
  "textures": {
    "layer0": "mymod:item/custom_hammer"
  }
}
```
Now, all that is left is a lang file entry and adding in your texture. It's that easy and painless!



# Build instructions
Import into your IDE of choice, direct the gradle wrapper to use JDK 21, and run `build` to compile the jar

# Add as a dependency
TODO


# Credits
Code: Modoromu (モドローム)
Art: TBA