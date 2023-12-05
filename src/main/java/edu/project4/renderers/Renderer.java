package edu.project4.renderers;

import edu.project4.models.FractalImage;
import edu.project4.models.Rectangle;
import edu.project4.transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetry
    );
}
