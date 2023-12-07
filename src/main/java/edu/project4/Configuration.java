package edu.project4;

import edu.project4.models.Rectangle;
import edu.project4.transformations.BentTransformation;
import edu.project4.transformations.CrossTransformation;
import edu.project4.transformations.DiamondTransformation;
import edu.project4.transformations.ExTransformation;
import edu.project4.transformations.ExponentialTransformation;
import edu.project4.transformations.FisheyeTransformation;
import edu.project4.transformations.HandkerchiefTransformation;
import edu.project4.transformations.HorseshoeTransformation;
import edu.project4.transformations.JuliaTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.PerspectiveTransformation;
import edu.project4.transformations.PolarTransformation;
import edu.project4.transformations.PowerTransformation;
import edu.project4.transformations.SinusoidalTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.transformations.SpiralTransformation;
import edu.project4.transformations.SwirlTransformation;
import edu.project4.transformations.TangentTransformation;
import edu.project4.transformations.Transformation;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings("checkstyle:MagicNumber")
public final class Configuration {
    public static final List<Transformation> TRANSFORMATIONS = List.of(
        new BentTransformation(),
        new CrossTransformation(),
        new DiamondTransformation(),
        // new DiscTransformation(),
        new ExponentialTransformation(),
        new ExTransformation(),
        new FisheyeTransformation(),
        new HandkerchiefTransformation(),
        // new HeartTransformation(),
        new HorseshoeTransformation(),
        // new HyperbolicTransformation(),
        new JuliaTransformation(),
        new LinearTransformation(),
        new PerspectiveTransformation(Math.toRadians(30), 10),
        new PolarTransformation(),
        new PowerTransformation(),
        new SinusoidalTransformation(),
        new SphericalTransformation(),
        new SpiralTransformation(),
        new SwirlTransformation(),
        new TangentTransformation()
        // new BlurTransformation()
    );

    public static final int IMAGE_HEIGHT = 1080;
    public static final int IMAGE_WIDTH = 1920;

    public static final int SAMPLES = 20;
    public static final int ITERATIONS_PER_SAMPLE = 10_000_000;
    public static final int SYMMETRY = 1;

    public static final Rectangle WORLD = new Rectangle(-4, -3, 8, 6);

    public static final Path OUTPUT_FOLDER = Path.of("src/main/java/edu/project4/results/");

    private Configuration() {}
}
