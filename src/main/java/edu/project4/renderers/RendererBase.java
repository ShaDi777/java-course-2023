package edu.project4.renderers;

import edu.project4.models.AffineCoefficients;
import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.models.Rectangle;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class RendererBase implements Renderer {
    protected static final int START = -20;

    protected void renderSample(
        FractalImage canvas,
        Rectangle world,
        int iterations,
        int symmetry,
        List<Transformation> transformations,
        AffineCoefficients[] coefficientsArray
    ) {
        Point pw = world.getRandomPoint();
        for (int step = START; step < iterations; step++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, coefficientsArray.length);
            AffineCoefficients coefficients = coefficientsArray[randomIndex];
            pw = getPointAfterAffineTransformation(coefficients, pw);
            Transformation transformation =
                transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size()));
            pw = transformation.apply(pw);
            if (step >= 0) {
                double theta = 0;
                for (int s = 0; s < symmetry; s++) {
                    theta += 2 * Math.PI / symmetry;
                    Point pwr = getRotatedPoint(pw, theta);
                    if (!world.contains(pwr)) {
                        continue;
                    }
                    Pixel pixel =
                        canvas.getPixel(
                            (int) ((pwr.x() - world.x()) * canvas.getWidth() / world.width()),
                            (int) ((pwr.y() - world.y()) * canvas.getHeight() / world.height())
                        );
                    if (pixel == null) {
                        continue;
                    }
                    synchronized (pixel) {
                        setPixelColor(pixel, coefficients);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }
    }

    protected Point getRotatedPoint(Point pw, double theta) {
        double xRot = pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta);
        double yRot = pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta);
        return new Point(xRot, yRot);
    }

    protected double getNewXUsingCoefficients(AffineCoefficients coefficients, Point pw) {
        return coefficients.a() * pw.x() + coefficients.b() * pw.y()
            + coefficients.c();
    }

    protected double getNewYUsingCoefficients(AffineCoefficients coefficients, Point pw) {
        return coefficients.d() * pw.x() + coefficients.e() * pw.y()
            + coefficients.f();
    }

    protected void setPixelColor(Pixel pixel, AffineCoefficients coefficients) {
        if (pixel.getHitCount() == 0) {
            pixel.setR(coefficients.color().getRed());
            pixel.setG(coefficients.color().getGreen());
            pixel.setB(coefficients.color().getBlue());
        } else {
            pixel.setR((pixel.getR() + coefficients.color().getRed()) / 2);
            pixel.setG((pixel.getG() + coefficients.color().getGreen()) / 2);
            pixel.setB((pixel.getB() + coefficients.color().getBlue()) / 2);
        }
    }

    protected AffineCoefficients[] getRandomAffineCoefficients(int samples) {
        AffineCoefficients[] transformations = new AffineCoefficients[samples];
        for (int i = 0; i < samples; i++) {
            transformations[i] = AffineCoefficients.create();
        }
        return transformations;
    }

    protected Point getPointAfterAffineTransformation(AffineCoefficients coefficients, Point pw) {
        double x = getNewXUsingCoefficients(coefficients, pw);
        double y = getNewYUsingCoefficients(coefficients, pw);
        return new Point(x, y);
    }
}
