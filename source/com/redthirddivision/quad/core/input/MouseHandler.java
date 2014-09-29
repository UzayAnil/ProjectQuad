/*   Copyright 2014 Matthew Rogers "BossLetsPlays"
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package com.redthirddivision.quad.core.input;

import org.lwjgl.input.Mouse;

import com.redthirddivision.quad.utils.math.Vector2f;



/**
 * <strong>Project:</strong> ProjectQuad <br>
 * <strong>File:</strong> MouseHandler.java
 *
 * @author <a href = "http://redthirddivision.com/team/BossLetsPlays"> Matthew Rogers</a>
 */
public class MouseHandler {

    public static final int NUM_BUTTONS = 5;
    private static boolean[] lastMouse = new boolean[NUM_BUTTONS];

    public static void update() {
        for (int i = 0; i < NUM_BUTTONS; i++)
            lastMouse[i] = getButton(i);
    }

    public static boolean getButton(int mouseButton) {
        return Mouse.isButtonDown(mouseButton);
    }

    public static boolean getButtonDown(int mouseButton) {
        return getButton(mouseButton) && !lastMouse[mouseButton];
    }

    public static boolean getButtonUp(int mouseButton) {
        return !getButton(mouseButton) && lastMouse[mouseButton];
    }

    public static Vector2f getMousePosition() {
        return new Vector2f(Mouse.getX(), Mouse.getY());
    }

    public static void setMousePosition(Vector2f pos) {
        Mouse.setCursorPosition((int) pos.getX(), (int) pos.getY());
    }

    public static void setCursor(boolean enabled) {
        Mouse.setGrabbed(!enabled);
    }

}
