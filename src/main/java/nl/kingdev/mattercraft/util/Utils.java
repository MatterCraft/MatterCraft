package nl.kingdev.mattercraft.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemStack;
import nl.kingdev.mattercraft.info.Reference;

public class Utils {

    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getFormatterLogger(Reference.mod_id);
        }
        return logger;
    }
    
	/**
	 * Converts the given {@link ItemStack} to a nice looking string
	 * 
	 * @param stack
	 *            The {@link ItemStack} to convert to a string
	 * @return The {@link ItemStack} as a string
	 */
	public static String stackToString(ItemStack stack) {
		String string = (stack.stackSize / 64 != 0 ? stack.stackSize / 64 + "x64" : "x")
				+ ((stack.stackSize - ((stack.stackSize / 64) * 64)) != 0
						? " + " + (stack.stackSize - ((stack.stackSize / 64) * 64)) : "")
				+ " " + stack.getDisplayName();
		if ((stack.stackSize / 64) == 0)
			string = string.replace(" + ", "");
		return string;
	}

}
