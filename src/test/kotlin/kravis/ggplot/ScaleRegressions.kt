package kravis.ggplot

import io.kotlintest.shouldBe
import krangl.irisData
import kravis.*
import kravis.Aesthetic.*
import kravis.ggplot.IrisData.*
import kravis.nshelper.ggplot
import org.junit.Test
import java.io.File

class ScaleRegressions : AbstractSvgPlotRegression() {

    override val testDataDir: File
        get() = File("src/test/resources/kravis")


    @Test
    fun `allow to change coordinate system`() {
        val plot = irisData.ggplot(SepalLength to x, SepalWidth to y)
            .geomPoint()

        assertExpected(plot.coordFlip(), "flip")
        assertExpected(plot.coordFixed(0.5), "fixed")
        assertExpected(plot.coordCartesian(xlim = 5.5 to 7.1), "cartesian")
    }

    @Test
    fun `allow for custom colors`() {
        val plot = irisData.ggplot(SepalLength to x, SepalWidth to y, Species to color)
            .geomPoint()
            .scaleColorManual(values = mapOf(
                "setosa" to RColor.black,
                "virginica" to RColor.red2,
                "versicolor" to RColor.yellow
            ),
                // to make sure that dotargs work as well we reset the aesthetic here
                dotdotdot = *arrayOf("aesthetics" to "colour")
            )

        assertExpected(plot)
    }

}