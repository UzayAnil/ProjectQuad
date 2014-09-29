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
 * <strong>File:</strong> Vector3f.java
 *
 * @author <a href = "http://redthirddivision.com/team/BossLetsPlays"> Matthew Rogers</a>
 */
public class Vector3f {

    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3f v) {
        return x * v.getX() + y * v.getY() + z * v.getZ();
    }

    public Vector3f cross(Vector3f v) {

        float xx = y * v.getZ() - z * v.getY();
        float yy = z * v.getX() - x * v.getZ();
        float zz = x * v.getY() - y * v.getX();

        return new Vector3f(xx, yy, zz);
    }

    public float max() {
        return Math.max(x, Math.max(y, z));
    }

    public Vector3f normalize() {
        float length = length();
        return new Vector3f(x / length, y / length, z / length);
    }

    public Vector3f rotate(Vector3f axis, float angle) {
        float sinAngle = (float) Math.sin(-angle);
        float cosAngle = (float) Math.cos(-angle);

        return this.cross(axis.multiply(sinAngle)).add( //Rotation on local X
                (this.multiply(cosAngle)).add //Rotation on local Z
                        (axis.multiply(this.dot(axis.multiply(1 - cosAngle))))); //Rotation on local Y
    }

    public Vector3f rotate(Quaternion rot) {
        Quaternion conjugate = rot.conjugate();
        Quaternion w = rot.multiply(this).multiply(conjugate);
        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    public Vector3f lerp(Vector3f destination, float lerpFactor) {
        return destination.subtract(this).multiply(lerpFactor).add(this);
    }

    public Vector3f add(Vector3f v) {
        return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
    }

    public Vector3f add(float f) {
        return new Vector3f(x + f, y + f, z + f);
    }

    public Vector3f subtract(Vector3f v) {
        return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
    }

    public Vector3f subtract(float f) {
        return new Vector3f(x - f, y - f, z - f);
    }

    public Vector3f multiply(Vector3f v) {
        return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
    }

    public Vector3f multiply(float f) {
        return new Vector3f(x * f, y * f, z * f);
    }

    public Vector3f divide(Vector3f v) {
        return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
    }

    public Vector3f divide(float f) {
        return new Vector3f(x / f, y / f, z / f);
    }

    public Vector3f absolute() {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vector2f getXY() {
        return new Vector2f(x, y);
    }

    public Vector2f getXZ() {
        return new Vector2f(x, z);
    }

    public Vector2f getYZ() {
        return new Vector2f(y, z);
    }

    public Vector2f getYX() {
        return new Vector2f(y, x);
    }

    public Vector2f getZX() {
        return new Vector2f(z, x);
    }

    public Vector2f getZY() {
        return new Vector2f(z, y);
    }

    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3f set(Vector3f vec) {
        return set(vec.x, vec.y, vec.z);
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Vector3f vec = (Vector3f) obj;
        return x == vec.x && y == vec.y && z == vec.z;
    }

}
