import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.*;


public class Figures {

    protected static void setup( GL2 gl2, int width, int height ) {
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // Початок системи координат в центрі вікна
        GLU glu = new GLU();
        glu.gluOrtho2D( -width, width, -height, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );
    }


    protected static void render( GL2 gl2, int width, int height ) {

        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );

        // Вісі координат
        gl2.glLoadIdentity();
        gl2.glColor3f(1,0,0);
        drawLine(gl2, 0,height,0,-height);
        drawLine(gl2, width,0,-width,0);
        drawLine(gl2, width,0,width*0.95f,height*0.05f);
        drawLine(gl2, width,0,width*0.95f,-height*0.05f);
        drawLine(gl2, 0,height,width*0.03f,height*0.93f);
        drawLine(gl2, 0,height,-width*0.03f,height*0.93f);

        // Параметри графіка
        double x, y;
        gl2.glColor3f(0,0,1);
        gl2.glPointSize(3);

        // Малювання графіка
        for(x=-6;x<=6;x+=0.005) {
            y = 3*Math.sin(Math.PI*(x/6))-4*Math.sin(Math.PI*(x/2));
            drawPoint(gl2,x*(width/12),y*(height/10));
        }


        // x розмітка
        gl2.glColor3f(0,1,0);
        for( x = -width;x<width*0.95; x+=width/12) {

            drawPoint(gl2,x,0);
        }

        // y розмітка
        for(y=-height;y<height*0.9;y+=height/10){

            drawPoint(gl2, 0,y);
        }

        // Підписи
        for (int i = 0, w = 0; i < 24; i++ ) {

            if( i != 12)
                drawText((i - 12) + "", w, (height / 2 - 20),width,height);

            w += width / 24;
        }

        // Підписи
        for (int i = 0, h = 0; i < 20; i++ ) {

            drawText((i - 10) + "", width / 2 - 25, h,width,height);

            h += height / 20;
        }

        drawText("f(x) = 3sin(PI*x/6)-4sin(PI*x/2)", 0,height/10,width,height);
    }

    private static void drawLine(GL2 gl2, double startX, double startY, double endX, double endY){
        gl2.glBegin(GL2ES3.GL_LINES);
        gl2.glVertex2d(startX,startY);
        gl2.glVertex2d(endX,endY);
        gl2.glEnd();
    }

    private static void drawPoint(GL2 gl2, double x, double y){
        gl2.glBegin(GL2ES3.GL_POINTS);
        gl2.glVertex2d(x,y);
        gl2.glEnd();
    }

    private static void drawText(String s, int w, int h, int width, int height){
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 11));
        textRenderer.beginRendering(width, height);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);
        textRenderer.draw(s, w, h);
        textRenderer.endRendering();
    }
}
