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
package com.redthirddivision.quad.utils.math;



/**
 * <strong>Project:</strong> ProjectQuad <br>
 * <strong>File:</strong> Vector2f.java
 *
 * @author <a href = "http://redthirddivision.com/team/BossLetsPlays"> Matthew Rogers</a>
 */
public class Vector2f {
    
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f v) {
        return x * v.getX() + y * v.getY();
    }

    public float max() {
        return Math.max(x, y);
    }

    public Vector2f normalize() {
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    public Vector2f rotate(float angle) {

        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y * cos));
    }

    public Vector2f lerp(Vector2f destination, float lerpFactor) {
        return destination.subtract(this).multiply(lerpFactor).add(this);
    }

    public float cross(Vector2f vec) {
        return x * vec.getY() - y * vec.getX();
    }

    public Vector2f add(Vector2f v) {
        return new Vector2f(x + v.getX(), y + v.getY());
    }

    public Vector2f add(float f) {
        return new Vector2f(x + f, y + f);
    }

    public Vector2f subtract(Vector2f v) {
        return new Vector2f(x - v.getX(), y - v.getY());
    }

    public Vector2f subtract(float f) {
        return new Vector2f(x - f, y - f);
    }

    public Vector2f multiply(Vector2f v) {
        return new Vector2f(x * v.getX(), y * v.getY());
    }

    public Vector2f multiply(float f) {
        return new Vector2f(x * f, y * f);
    }

    public Vector2f divide(Vector2f v) {
        return new Vector2f(x / v.getX(), y / v.getY());
    }

    public Vector2f divide(float f) {
        return new Vector2f(x / f, y / f);
    }

    public Vector2f absolute() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2f set(Vector2f v) {
        return set(v.x, v.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Vector2f vec = (Vector2f) obj;
        return x == vec.x && y == vec.y;
    }

}
