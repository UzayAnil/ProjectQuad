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
package com.redthirddivision.quad;

import com.redthirddivision.quad.core.Window;
import com.redthirddivision.quad.core.input.KeyHandler;
import com.redthirddivision.quad.core.input.MouseHandler;
import com.redthirddivision.quad.rendering.RenderEngine;
import com.redthirddivision.quad.utils.Time;

/**
 * <strong>Project:</strong> ProjectQuad <br>
 * <strong>File:</strong> ProjectQuad.java
 *
 * @author <a href = "http://redthirddivision.com/team/BossLetsPlays"> Matthew Rogers</a>
 */
public class ProjectQuad {

    public static final int    WIDTH  = 1280;
    public static final int    HEIGHT = WIDTH / 16 * 9;
    public static final String TITLE  = "Project Quad";

    private boolean            running;
    private double             frameCap;
    private RenderEngine       renderEngine;

    public ProjectQuad() {
        Window.create(WIDTH, HEIGHT, TITLE);
        renderEngine = new RenderEngine();
        frameCap = 1.0 / 60.0;
    }

    public void start() {
        if (running) return;
        run();
    }

    public void stop() {
        if (!running) return;
        running = false;
    }

    public void cleanUp() {
        Window.dispose();
    }

    public void run() {
        running = true;
        int frames = 0;
        double frameCounter = 0;
        double lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (running) {
            boolean needsToRender = false;

            double startTime = Time.getTime();
            double passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime;
            frameCounter += passedTime;

            while (unprocessedTime > frameCap) {
                needsToRender = true;

                unprocessedTime -= frameCap;

                if (Window.isCloseRequested()) stop();

                KeyHandler.update();
                MouseHandler.update();

                if (frameCounter >= 1.0) {
                    System.out.println("FPS:  " + frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (needsToRender) {
                renderEngine.render();
                Window.render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        cleanUp();

    }

    public static void main(String[] args) {
        ProjectQuad game = new ProjectQuad();
        game.start();
    }

}
