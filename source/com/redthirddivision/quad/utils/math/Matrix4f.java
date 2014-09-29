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
 * <strong>File:</strong> Matrix4f.java
 *
 * @author <a href = "http://redthirddivision.com/team/BossLetsPlays"> Matthew Rogers</a>
 */
public class Matrix4f {

    private float[][] matrix;

    public Matrix4f() {
        matrix = new float[4][4];
    }

    public Matrix4f initIdentity() {
        //@formatter:off
        matrix[0][0] = 1;   matrix[1][0] = 0;   matrix[2][0] = 0;   matrix[3][0] = 0;
        matrix[0][1] = 0;   matrix[1][1] = 1;   matrix[2][1] = 0;   matrix[3][1] = 0;
        matrix[0][2] = 0;   matrix[1][2] = 0;   matrix[2][2] = 1;   matrix[3][2] = 0;
        matrix[0][3] = 0;   matrix[1][3] = 0;   matrix[2][3] = 0;   matrix[3][3] = 1;
        //@formatter:on
        return this;
    }

    public Matrix4f initTranslation(float x, float y, float z) {
        //@formatter:off
        matrix[0][0] = 1;   matrix[1][0] = 0;   matrix[2][0] = 0;    matrix[3][0] = 0;
        matrix[0][1] = 0;   matrix[1][1] = 1;   matrix[2][1] = 0;    matrix[3][1] = 0;
        matrix[0][2] = 0;   matrix[1][2] = 0;   matrix[2][2] = 1;    matrix[3][2] = 0;
        matrix[0][3] = x;   matrix[1][3] = y;   matrix[2][3] = z;    matrix[3][3] = 1;
        //@formatter:on
        return this;
    }

    public Matrix4f initRotation(float x, float y, float z) {
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
        Matrix4f rz = new Matrix4f();
        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);

        //@formatter:off
        rx.matrix[0][0] = 1;    rx.matrix[1][0] = 0;                       rx.matrix[2][0] = 0;                      rx.matrix[3][0] = 0;
        rx.matrix[0][1] = 0;    rx.matrix[1][1] =  (float) Math.cos(x);    rx.matrix[2][1] = (float) Math.sin(x);    rx.matrix[3][1] = 0;
        rx.matrix[0][2] = 0;    rx.matrix[1][2] = -(float) Math.sin(x);    rx.matrix[2][2] = (float) Math.cos(x);    rx.matrix[3][2] = 0;
        rx.matrix[0][3] = 0;    rx.matrix[1][3] = 0;                       rx.matrix[2][3] = 0;                      rx.matrix[3][3] = 1;

        /*-------------------------------------------------------------------------*/

        ry.matrix[0][0] =  (float) Math.cos(y);    ry.matrix[1][0] = 0;    ry.matrix[2][0] = (float) Math.sin(y);    ry.matrix[3][0] = 0;
        ry.matrix[0][1] = 0;                       ry.matrix[1][1] = 1;    ry.matrix[2][1] = 0;                      ry.matrix[3][1] = 0;
        ry.matrix[0][2] = -(float) Math.sin(y);    ry.matrix[1][2] = 0;    ry.matrix[2][2] = (float) Math.cos(y);    ry.matrix[3][2] = 0;
        ry.matrix[0][3] = 0;                       ry.matrix[1][3] = 0;    ry.matrix[2][3] = 0;                      ry.matrix[3][3] = 1;
        
        /*-------------------------------------------------------------------------*/
        
        rz.matrix[0][0] =  (float) Math.cos(z);    rz.matrix[1][0] = (float) Math.sin(z);    rz.matrix[2][0] = 0;    rz.matrix[3][0] = 0;
        rz.matrix[0][1] = -(float) Math.sin(z);    rz.matrix[1][1] = (float) Math.cos(z);    rz.matrix[2][1] = 0;    rz.matrix[3][1] = 0;
        rz.matrix[0][2] = 0;                       rz.matrix[1][2] = 0;                      rz.matrix[2][2] = 1;    rz.matrix[3][2] = 0;
        rz.matrix[0][3] = 0;                       rz.matrix[1][3] = 0;                      rz.matrix[2][3] = 0;    rz.matrix[3][3] = 1;
        //@formatter:on

        matrix = rz.multiply(ry.multiply(rx)).getMatrixData();
        return this;
    }

    public Matrix4f initRotation(Vector3f forward, Vector3f up) {
        Vector3f f = forward.normalize();
        Vector3f r = up.normalize();
        r = r.cross(f);
        Vector3f u = f.cross(r);

        //@formatter:off
        matrix[0][0] = r.getX();    matrix[1][0] = u.getX();    matrix[2][0] = f.getX();    matrix[3][0] = 0;
        matrix[0][1] = r.getY();    matrix[1][1] = u.getY();    matrix[2][1] = f.getY();    matrix[3][1] = 0;
        matrix[0][2] = r.getZ();    matrix[1][2] = u.getZ();    matrix[2][2] = f.getZ();    matrix[3][2] = 0;
        matrix[0][3] = 0;           matrix[1][3] = 0;           matrix[2][3] = 0;           matrix[3][3] = 1;
        //@formatter:on

        return this;
    }

    public Matrix4f initRotation(Vector3f forward, Vector3f up, Vector3f right) {
        Vector3f f = forward;
        Vector3f r = right;
        Vector3f u = up;

        //@formatter:off
        matrix[0][0] = r.getX();    matrix[1][0] = u.getX();    matrix[2][0] = f.getX();    matrix[3][0] = 0;
        matrix[0][1] = r.getY();    matrix[1][1] = u.getY();    matrix[2][1] = f.getY();    matrix[3][1] = 0;
        matrix[0][2] = r.getZ();    matrix[1][2] = u.getZ();    matrix[2][2] = f.getZ();    matrix[3][2] = 0;
        matrix[0][3] = 0;           matrix[1][3] = 0;           matrix[2][3] = 0;           matrix[3][3] = 1;
        //@formatter:on

        return this;
    }

    public Matrix4f initScale(float x, float y, float z) {
        //@formatter:off
        matrix[0][0] = x;    matrix[1][0] = 0;    matrix[2][0] = 0;    matrix[3][0] = 0;
        matrix[0][1] = 0;    matrix[1][1] = y;    matrix[2][1] = 0;    matrix[3][1] = 0;
        matrix[0][2] = 0;    matrix[1][2] = 0;    matrix[2][2] = z;    matrix[3][2] = 0;
        matrix[0][3] = 0;    matrix[1][3] = 0;    matrix[2][3] = 0;    matrix[3][3] = 1;
        //@formatter:on

        return this;
    }

    public Matrix4f initPerspective(float fov, float aspectRatio, float zNear, float zFar) {
        float halfTan_fov = (float) Math.tan(fov / 2);
        float zRange = zNear - zFar;

        //@formatter:off
        matrix[0][0] = 1.0f / (halfTan_fov * aspectRatio);    matrix[1][0] = 0;                     matrix[2][0] = 0;                            matrix[3][0] = 0;
        matrix[0][1] = 0;                                     matrix[1][1] = 1.0f / halfTan_fov;    matrix[2][1] = 0;                            matrix[3][1] = 0;
        matrix[0][2] = 0;                                     matrix[1][2] = 0;                     matrix[2][2] = (-zNear - zFar) / zRange;     matrix[3][2] = 1;
        matrix[0][3] = 0;                                     matrix[1][3] = 0;                     matrix[2][3] = 2 * zFar * zNear / zRange;    matrix[3][3] = 0;
        //@formatter:on

        return this;
    }

    public Matrix4f initOrthographic(float left, float right, float bottom, float top, float near, float far) {
        float width = right - left;
        float height = top - bottom;
        float depth = far - near;

        //@formatter:off
        matrix[0][0] = 2 / width;                  matrix[1][0] = 0;                           matrix[2][0] = 0;                        matrix[3][0] = 0;
        matrix[0][1] = 0;                          matrix[1][1] = 2 / height;                  matrix[2][1] = 0;                        matrix[3][1] = 0;
        matrix[0][2] = 0;                          matrix[1][2] = 0;                           matrix[2][2] = -2 / depth;               matrix[3][2] = 0;
        matrix[0][3] = -(right + left) / width;    matrix[1][3] = -(top + bottom) / height;    matrix[2][3] = -(far + near) / depth;    matrix[3][3] = 1;
        //@formatter:on

        return this;
    }

    public Vector3f transform(Vector3f v) {
        return new Vector3f(
                matrix[0][0] * v.getX() + matrix[0][1] * v.getY() + matrix[0][2] * v.getZ() + matrix[0][3],
                matrix[1][0] * v.getX() + matrix[1][1] * v.getY() + matrix[1][2] * v.getZ() + matrix[1][3],
                matrix[2][0] * v.getX() + matrix[2][1] * v.getY() + matrix[2][2] * v.getZ() + matrix[2][3]);
    }

    public Matrix4f multiply(Matrix4f r) {
        Matrix4f res = new Matrix4f();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                res.set(i, j, matrix[i][0] * r.get(0, j) + matrix[i][1] * r.get(1, j) + matrix[i][2] * r.get(2, j) + matrix[i][3] * r.get(3, j));
        return res;
    }

    public float get(int x, int y) {
        return matrix[x][y];
    }

    public void set(int x, int y, float value) {
        matrix[x][y] = value;
    }

    public float[][] getMatrixData() {
        float[][] res = new float[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                res[i][j] = matrix[i][j];
        return res;
    }

    public void setMatrixData(float[][] m) {
        this.matrix = m;
    }

    @Override
    public String toString() {
        int rowNum = 1;
        String result = "Matrix Data:\nRow 0: ";
        StringBuilder builder = new StringBuilder(result);
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (r == 3) builder.append(matrix[r][c] + "\n");
                else builder.append(matrix[r][c] + " ");
            }
            if (c != 3) builder.append("Row " + rowNum + ": ");
            rowNum++;
        }
        return builder.toString();
    }

}
