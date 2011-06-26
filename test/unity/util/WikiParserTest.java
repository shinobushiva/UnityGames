//package unity.util;
//
//import java.io.StringWriter;
//
//import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
//import org.eclipse.mylyn.wikitext.core.parser.builder.HtmlDocumentBuilder;
//import org.eclipse.mylyn.wikitext.tracwiki.core.TracWikiLanguage;
//import org.eclipse.mylyn.wikitext.twiki.core.TWikiLanguage;
//
//public class WikiParserTest {
//    public static void main(String[] args) throws Exception {
//        String text = "==太字==";
//
//        StringWriter writer = new StringWriter();
//        HtmlDocumentBuilder builder = new HtmlDocumentBuilder(writer);
//
//        MarkupParser parser = new MarkupParser(new TracWikiLanguage());
//        parser.setBuilder(builder);
//        parser.parse(text);
//
//        String htmlContent = writer.toString();
//        System.out.println(htmlContent);
//    }
//
//}