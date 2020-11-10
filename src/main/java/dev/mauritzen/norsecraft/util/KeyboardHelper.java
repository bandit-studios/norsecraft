package dev.mauritzen.norsecraft.util;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;

public class KeyboardHelper {

	private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingShift() {
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
	}
}
