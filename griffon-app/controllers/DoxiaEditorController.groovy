import org.apache.maven.doxia.ConverterException
import org.apache.maven.doxia.DefaultConverter
import org.apache.maven.doxia.UnsupportedFormatException
import org.apache.maven.doxia.wrapper.InputReaderWrapper
import org.apache.maven.doxia.wrapper.OutputStreamWrapper

class DoxiaEditorController {

    def view
    def model

    def show = { evt = null ->
        if (!evt.source.selectedIndex) {
            return
        }
        def converter = new DefaultConverter()
        def reader = InputReaderWrapper.valueOf(new StringReader(model.source), 'apt', converter.inputFormats)
        def os = new ByteArrayOutputStream()
        def output = OutputStreamWrapper.valueOf(os, 'xhtml', "UTF-8", converter.outputFormats)
        try {
            converter.convert(reader, output)
            view.viewer.contentType = 'text/html'
            model.html = (os.toString("UTF-8") =~ /(?s:<body>.*<\\/body>)/)[0]
        } catch (e) {
            view.viewer.contentType = 'text/plain'
            model.html = e.message
        }
    }
}