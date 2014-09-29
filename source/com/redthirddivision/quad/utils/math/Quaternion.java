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
 * <strong>File:</strong> Quaternion.java
 *
 * @author <a href = "http://redthirddivision.com/team/BossLetsPlays"> Matthew Rogers</a>
 */
public class Quaternion {

    private float x;
    private float y;
    private float z;
    private float w;

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion(Vector3f axis, float angle) {
        float rad = (float) Math.toRadians(angle);
        float sinHalfAngle = (float) Math.sin(rad / 2);
        float cosHalfAngle = (float) Math.cos(rad / 2);
        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
    }

    public Quaternion(Matrix4f rotationMatrix) {
        float trace = rotationMatrix.get(0, 0) + rotationMatrix.get(1, 1) + rotationMatrix.get(2, 2);

        if (trace > 0) {
            float s = 0.5f / (float) Math.sqrt(trace + 1.0f);
            w = 0.25f / s;
            x = (rotationMatrix.get(1, 2) - rotationMatrix.get(2, 1)) * s;
            y = (rotationMatrix.get(2, 0) - rotationMatrix.get(0, 2)) * s;
            z = (rotationMatrix.get(0, 1) - rotationMatrix.get(1, 0)) * s;
        } else {
            if (rotationMatrix.get(0, 0) > rotationMatrix.get(1, 1) && rotationMatrix.get(0, 0) > rotationMatrix.get(2, 2)) {
                float s = 2.0f * (float) Math.sqrt(1.0f + rotationMatrix.get(0, 0) - rotationMatrix.get(1, 1) - rotationMatrix.get(2, 2));
                w = (rotationMatrix.get(1, 2) - rotationMatrix.get(2, 1)) / s;
                x = 0.25f * s;
                y = (rotationMatrix.get(1, 0) + rotationMatrix.get(0, 1)) / s;
                z = (rotationMatrix.get(2, 0) + rotationMatrix.get(0, 2)) / s;
            } else if (rotationMatrix.get(1, 1) > rotationMatrix.get(2, 2)) {
                float s = 2.0f * (float) Math.sqrt(1.0f + rotationMatrix.get(1, 1) - rotationMatrix.get(0, 0) - rotationMatrix.get(2, 2));
                w = (rotationMatrix.get(2, 0) - rotationMatrix.get(0, 2)) / s;
                x = (rotationMatrix.get(1, 0) + rotationMatrix.get(0, 1)) / s;
                y = 0.25f * s;
                z = (rotationMatrix.get(2, 1) + rotationMatrix.get(1, 2)) / s;
            } else {
                float s = 2.0f * (float) Math.sqrt(1.0f + rotationMatrix.get(2, 2) - rotationMatrix.get(0, 0) - rotationMatrix.get(1, 1));
                w = (rotationMatrix.get(0, 1) - rotationMatrix.get(1, 0)) / s;
                x = (rotationMatrix.get(2, 0) + rotationMatrix.get(0, 2)) / s;
                y = (rotationMatrix.get(1, 2) + rotationMatrix.get(2, 1)) / s;
                z = 0.25f * s;
            }
        }

        float length = (float) Math.sqrt(x * x + y * y + z * z + w * w);
        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion normalize() {
        float length = length();
        return new Quaternion(x / length, y / length, z / length, w / length);
    }

    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion multiply(Quaternion q) {

        float xx = x * q.getW() + w * q.getX() + y * q.getZ() - z * q.getY();
        float yy = y * q.getW() + w * q.getY() + z * q.getX() - x * q.getZ();
        float zz = z * q.getW() + w * q.getZ() + x * q.getY() - y * q.getX();
        float ww = w * q.getW() - x * q.getX() - y * q.getY() - z * q.getZ();

        return new Quaternion(xx, yy, zz, ww);
    }

    public Quaternion multiply(Vector3f v) {

        float xx = w * v.getX() + y * v.getZ() - z * v.getY();
        float yy = w * v.getY() + z * v.getX() - x * v.getZ();
        float zz = w * v.getZ() + x * v.getY() - y * v.getX();
        float ww = -x * v.getX() - y * v.getY() - z * v.getZ();

        return new Quaternion(xx, yy, zz, ww);
    }

    public Quaternion multiply(float par1) {
        return new Quaternion(x * par1, y * par1, z * par1, w * par1);
    }

    public Quaternion divide(Quaternion q) {
        return new Quaternion(x / q.x, y / q.y, z / q.z, w / q.w);
    }

    public Quaternion add(Quaternion q) {
        return new Quaternion(x + q.x, y + q.y, z + q.z, w + q.w);
    }

    public Quaternion subtract(Quaternion q) {
        return new Quaternion(x - q.x, y - q.y, z - q.z, w - q.w);
    }

    public float dot(Quaternion q)
    {
        return x * q.x + y * q.y + z * q.z + w * q.w;
    }

    /**
     * Normalized lerping
     * 
     * @param shortest should the rotation take the shortest available path?
     */
    public Quaternion nLerp(Quaternion destination, float lerpFactor, boolean shortest) {
        Quaternion correctedDest = destination;

        if (shortest && this.dot(destination) < 0)
            correctedDest = new Quaternion(-destination.getX(), -destination.getY(), -destination.getZ(), -destination.getW());

        return correctedDest.subtract(this).multiply(lerpFactor).add(this).normalize();
    }

    /**
     * Spherical lerping
     * 
     * @param shortest should the rotation take the shortest available path?
     */
    public Quaternion sLerp(Quaternion destination, float lerpFactor, boolean shortest) {
        final float EPSILON = 1e3f;

        float cos = this.dot(destination);
        Quaternion correctedDest = destination;

        if (shortest && cos < 0)
        {
            cos = -cos;
            correctedDest = new Quaternion(-destination.getX(), -destination.getY(), -destination.getZ(), -destination.getW());
        }

        if (Math.abs(cos) >= 1 - EPSILON)
            return nLerp(correctedDest, lerpFactor, false);

        float sin = (float) Math.sqrt(1.0f - cos * cos);
        float angle = (float) Math.atan2(sin, cos);
        float invSin = 1.0f / sin;

        float srcFactor = (float) Math.sin((1.0f - lerpFactor) * angle) * invSin;
        float destFactor = (float) Math.sin((lerpFactor) * angle) * invSin;

        return this.multiply(srcFactor).add(correctedDest.multiply(destFactor));
    }

    public Matrix4f toRotationMatrix() {
        Vector3f forward = new Vector3f(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0f - 2.0f * (x * x + y * y));
        Vector3f up = new Vector3f(2.0f * (x * y + w * z), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x));
        Vector3f right = new Vector3f(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));

        return new Matrix4f().initRotation(forward, up, right);
    }

    public Vector3f getForward() {
        return new Vector3f(0, 0, 1).rotate(this);
    }

    public Vector3f getBack() {
        return new Vector3f(0, 0, -1).rotate(this);
    }

    public Vector3f getUp() {
        return new Vector3f(0, 1, 0).rotate(this);
    }

    public Vector3f getDown() {
        return new Vector3f(0, -1, 0).rotate(this);
    }

    public Vector3f getRight() {
        return new Vector3f(1, 0, 0).rotate(this);
    }

    public Vector3f getLeft() {
        return new Vector3f(-1, 0, 0).rotate(this);
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

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Quaternion set(Quaternion q) {
        return set(q.x, q.y, q.z, q.w);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Quaternion quat = (Quaternion) obj;
        return x == quat.x && y == quat.y && z == quat.z && w == quat.w;
    }

}
