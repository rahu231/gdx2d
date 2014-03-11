package hevs.gdx2d.demos.shaders.advanced;

import hevs.gdx2d.lib.GdxGraphics;
import hevs.gdx2d.lib.PortableApplication;
import hevs.gdx2d.lib.utils.Logger;

import com.badlogic.gdx.math.Vector2;

/**
 * Julia set as a shader, based on mei code 
 * @author Pierre-André Mudry (mui)
 * @version 0.2
 */
public class DemoJulia extends PortableApplication {

	public DemoJulia() {
		super(false, 700, 700);
	}

	@Override
	public void onInit() {
		this.setTitle("Julia set shader, mui 2013");
		Logger.log("Click to change picture");
	}

	float t = 0;
	boolean clicked = false;
	boolean image1 = true;

	int direction = 1;
	float juliaPrm = 0.35f;
	float scale = 1.10f;
	Vector2 offset = new Vector2(0, 0);

	@Override
	public void onGraphicRender(GdxGraphics g) {
		if (g.shaderRenderer == null) {
			g.setShader("data/shader/julia.fp");
			g.shaderRenderer.addTexture("data/shader/pal.png", "texture0");
		}

		g.shaderRenderer.setUniform("scale", scale);
		g.shaderRenderer.setUniform("offset", offset);
		g.shaderRenderer.setUniform("center", new Vector2(juliaPrm, juliaPrm));
		juliaPrm += direction * (10.0f / 30000.0f);
		
		if (juliaPrm < 0.33 || juliaPrm > 0.4)
			direction *= -1;
		
		g.clear();
		g.drawShader(t);
		g.drawFPS();
		g.drawSchoolLogo();
	}

	@Override
	public void onScroll(int amount) {
		super.onScroll(amount);
		scale += amount * 0.03;
	}
	
	@Override
	public void onPan(float x, float y, float deltaX, float deltaY) {
		super.onPan(x, y, deltaX, deltaY);
		offset.x -= deltaX / 200.0;
		offset.y -= deltaY / 200.0;
	}

	public static void main(String args[]) {
		new DemoJulia();
	}
}
