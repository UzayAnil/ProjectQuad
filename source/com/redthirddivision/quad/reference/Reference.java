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
package com.redthirddivision.quad.reference;

import com.redthirddivision.quad.utils.math.Vector3f;

/**
 * <strong>Project:</strong> ProjectQuad <br>
 * <strong>File:</strong> Reference.java
 *
 * @author <a href = "http://redthirddivision.com/team/BossLetsPlays"> Matthew Rogers</a>
 */
public class Reference {

    /* Axis Constants */
    public static final Vector3f X_AXIS   = new Vector3f(1, 0, 0);
    public static final Vector3f Y_AXIS   = new Vector3f(0, 1, 0);
    public static final Vector3f Z_AXIS   = new Vector3f(0, 0, 1);
    public static final Vector3f XY_AXIS  = new Vector3f(1, 1, 0);
    public static final Vector3f YZ_AXIS  = new Vector3f(0, 1, 1);
    public static final Vector3f ZX_AXIS  = new Vector3f(1, 0, 1);
    public static final Vector3f XYZ_AXIS = new Vector3f(1, 1, 1);
    public static final Vector3f NO_AXIS  = new Vector3f(0, 0, 0);

}
