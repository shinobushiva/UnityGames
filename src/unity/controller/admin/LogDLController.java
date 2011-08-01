package unity.controller.admin;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.ByteUtil;

import unity.meta.LogTextMeta;
import unity.model.LogText;
import unity.model.UploadedDataFragment;

public class LogDLController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        String logDate = "";
        if (requestScope("logDate") != null)
            logDate = (String) requestScope("logDate");
        else {
            logDate = asString("fileName");
        }
        String[] split = logDate.split("/");
        String fileName = split[0] + "/" + split[1];
        try {
            LogText asSingle =
                Datastore
                    .query(LogText.class)
                    .filter(LogTextMeta.get().fileName.equal(fileName))
                    .asSingle();

            List<UploadedDataFragment> fragmentList2 =
                asSingle.getFragmentListRef().getModelList();
            byte[][] bytesArray2 = new byte[fragmentList2.size()][0];
            for (int i = 0; i < fragmentList2.size(); i++) {
                bytesArray2[i] = fragmentList2.get(i).getBytes();
            }
            download(
                split[0] + "_" + split[1] + ".txt",
                ByteUtil.join(bytesArray2));
        } catch (Exception e) {
            response.getWriter().write("Log is nothing at this day.");
        }
        return null;
    }
}
