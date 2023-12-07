package edu.project4.renderers;

import edu.project4.models.AffineCoefficients;
import edu.project4.models.FractalImage;
import edu.project4.models.Rectangle;
import edu.project4.transformations.Transformation;
import java.util.List;

public class SimpleRenderer extends RendererBase {

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
            renderSample(
                canvas,
                world,
                iterationsPerSample,
                symmetry,
                transformations,
                coefficientsArray
            );
        }
        return canvas;
    }
}
