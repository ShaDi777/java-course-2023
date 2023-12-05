package edu.project4.renderers;

import edu.project4.models.AffineCoefficients;
import edu.project4.models.FractalImage;
import edu.project4.models.Rectangle;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelRenderer extends RendererBase {
    private static final int TIMEOUT_MIN = 10;
    private final ExecutorService executorService;

    public ParallelRenderer(int numThreads) {
        executorService = Executors.newFixedThreadPool(
            numThreads > 0
                ? numThreads
                : Runtime.getRuntime().availableProcessors()
        );
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetry
    ) {
        AffineCoefficients[] coefficientsArray = getRandomAffineCoefficients(samples);
        for (int num = 0; num < samples; num++) {
            executorService.execute(() -> renderSample(
                canvas,
                world,
                iterationsPerSample,
                symmetry,
                transformations,
                coefficientsArray
            ));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(TIMEOUT_MIN, TimeUnit.MINUTES);
        } catch (InterruptedException ignored) {
        }

        return canvas;
    }
}
