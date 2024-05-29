import org.lwjgl.opengl.GL11;

public class Shapes {

    public static void renderCube(float x, float y, float z, float size, float[] color) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glColor3f(color[0], color[1], color[2]);
        GL11.glBegin(GL11.GL_QUADS);
        
        // Front face
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f(size, -size, size);
        GL11.glVertex3f(size, size, size);
        GL11.glVertex3f(-size, size, size);
        
        // Back face
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(-size, size, -size);
        GL11.glVertex3f(size, size, -size);
        GL11.glVertex3f(size, -size, -size);
        
        // Top face
        GL11.glVertex3f(-size, size, -size);
        GL11.glVertex3f(-size, size, size);
        GL11.glVertex3f(size, size, size);
        GL11.glVertex3f(size, size, -size);
        
        // Bottom face
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(size, -size, -size);
        GL11.glVertex3f(size, -size, size);
        GL11.glVertex3f(-size, -size, size);
        
        // Right face
        GL11.glVertex3f(size, -size, -size);
        GL11.glVertex3f(size, size, -size);
        GL11.glVertex3f(size, size, size);
        GL11.glVertex3f(size, -size, size);
        
        // Left face
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f(-size, size, size);
        GL11.glVertex3f(-size, size, -size);
        
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void renderSphere(float x, float y, float z, float radius, float[] color, int slices, int stacks) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glColor3f(color[0], color[1], color[2]);

        for (int i = 0; i <= stacks; i++) {
            float lat0 = (float) Math.PI * (-0.5f + (float) (i - 1) / stacks);
            float z0 = (float) Math.sin(lat0);
            float zr0 = (float) Math.cos(lat0);

            float lat1 = (float) Math.PI * (-0.5f + (float) i / stacks);
            float z1 = (float) Math.sin(lat1);
            float zr1 = (float) Math.cos(lat1);

            GL11.glBegin(GL11.GL_QUAD_STRIP);
            for (int j = 0; j <= slices; j++) {
                float lng = 2 * (float) Math.PI * (float) (j - 1) / slices;
                float x1 = (float) Math.cos(lng);
                float y1 = (float) Math.sin(lng);

                GL11.glNormal3f(x1 * zr0, y1 * zr0, z0);
                GL11.glVertex3f(radius * x1 * zr0, radius * y1 * zr0, radius * z0);
                GL11.glNormal3f(x1 * zr1, y1 * zr1, z1);
                GL11.glVertex3f(radius * x1 * zr1, radius * y1 * zr1, radius * z1);
            }
            GL11.glEnd();
        }
        GL11.glPopMatrix();
    }

    public static void renderPyramid(float x, float y, float z, float size, float[] color) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glColor3f(color[0], color[1], color[2]);

        GL11.glBegin(GL11.GL_TRIANGLES);
        
        // Front face
        GL11.glVertex3f(0.0f, size, 0.0f);
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f(size, -size, size);

        // Right face
        GL11.glVertex3f(0.0f, size, 0.0f);
        GL11.glVertex3f(size, -size, size);
        GL11.glVertex3f(size, -size, -size);

        // Back face
        GL11.glVertex3f(0.0f, size, 0.0f);
        GL11.glVertex3f(size, -size, -size);
        GL11.glVertex3f(-size, -size, -size);

        // Left face
        GL11.glVertex3f(0.0f, size, 0.0f);
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(-size, -size, size);

        GL11.glEnd();

        // Bottom face
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f(size, -size, size);
        GL11.glVertex3f(size, -size, -size);
        GL11.glVertex3f(-size, -size, -size);
        GL11.glEnd();

        GL11.glPopMatrix();
    }
}
