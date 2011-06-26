//package unity.controller.unitygames;
//
//import java.io.StringWriter;
//
//import org.slim3.controller.Controller;
//import org.slim3.controller.Navigation;
//
//public class WikiController extends Controller {
//
//    @Override
//    public Navigation run() throws Exception {
//
//        String text = asString("data");
//
//        StringWriter writer = new StringWriter();
//        HtmlDocumentBuilder builder = new HtmlDocumentBuilder(writer);
//
//        MarkupParser parser = new MarkupParser(new TracWikiLanguage());
//        parser.setBuilder(builder);
//        parser.parse(text);
//
//       // String htmlContent = writer.toString();
//        String htmlContent = text.replaceAll("\n", "<br />");
//        System.out.println(htmlContent);
//
//         response.setContentType("text/html");
//         response.setCharacterEncoding("utf-8");
//        response.getWriter().write(""+htmlContent);
//        response.getWriter().flush();
//
//        return null;
//    }
// }
