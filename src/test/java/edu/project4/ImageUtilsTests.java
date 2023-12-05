package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.models.ImageFormat;
import edu.project4.utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ImageUtilsTests {
    private static Stream<Arguments> paramImageFormat() {
        return Stream.of(
            Arguments.of(ImageFormat.PNG),
            Arguments.of(ImageFormat.BMP),
            Arguments.of(ImageFormat.JPEG)
        );
    }

    @ParameterizedTest
    @MethodSource("paramImageFormat")
    void savingImage( ImageFormat format, @TempDir Path path) throws IOException {
        Path savedImg = path.resolve("img");
        ImageUtils.save(FractalImage.create(400, 400), savedImg, format);
        assertThat(savedImg).exists();
    }
}
