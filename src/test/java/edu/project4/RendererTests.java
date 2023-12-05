package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.models.Rectangle;
import edu.project4.renderers.ParallelRenderer;
import edu.project4.renderers.SimpleRenderer;
import edu.project4.transformations.DiamondTransformation;
import edu.project4.transformations.DiscTransformation;
import edu.project4.transformations.ExponentialTransformation;
import edu.project4.transformations.HandkerchiefTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.SinusoidalTransformation;
import edu.project4.transformations.TangentTransformation;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RendererTests {
    @Test
    void simpleRendererWithoutThrow() {
        Assertions.assertDoesNotThrow(() ->
            new SimpleRenderer().render(
                FractalImage.create(400, 400),
                new Rectangle(-2, -2, 4, 4),
                List.of(
                    new HandkerchiefTransformation(),
                    new SinusoidalTransformation(),
                    new ExponentialTransformation(),
                    new TangentTransformation()
                ),
                1000, 1000, 2
            )
        );
    }
    @Test
    void parallelRendererWithoutThrow() {
        Assertions.assertDoesNotThrow(() ->
            new ParallelRenderer(Runtime.getRuntime().availableProcessors()).render(
                FractalImage.create(400, 400),
                new Rectangle(-2, -2, 4, 4),
                List.of(
                    new HeartTransformation(),
                    new LinearTransformation(),
                    new DiscTransformation(),
                    new DiamondTransformation()
                ),
                1000, 1000, 2
            )
        );
    }
}
