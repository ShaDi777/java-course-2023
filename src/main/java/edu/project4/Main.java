package edu.project4;

import edu.project4.imageprocessing.GammaCorrectionProcessor;
import edu.project4.models.FractalImage;
import edu.project4.models.ImageFormat;
import edu.project4.renderers.ParallelRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.utils.ImageUtils;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        FractalImage fractalImage = FractalImage.create(Configuration.IMAGE_WIDTH, Configuration.IMAGE_HEIGHT);

        Renderer renderer = new ParallelRenderer(Runtime.getRuntime().availableProcessors());

        fractalImage = renderer.render(
            fractalImage,
            Configuration.WORLD,
            Configuration.TRANSFORMATIONS,
            Configuration.SAMPLES,
            Configuration.ITERATIONS_PER_SAMPLE,
            Configuration.SYMMETRY
        );

        new GammaCorrectionProcessor().process(fractalImage);

        ImageUtils.save(
            fractalImage,
            Configuration.OUTPUT_FOLDER.resolve("imageTrueChaosParallel.png"),
            ImageFormat.PNG
        );
    }
}
