import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;

public class MergeFile {
    public static void main(String[] args) {
        //这里写文件路径,改！！！！！！
        String[] files = {"C:\\Users\\admin\\Desktop\\1.pdf","C:\\Users\\admin\\Desktop\\2.pdf"};
        //这里写新文件路径，改！！！！！
        String newFile = "C:\\Users\\admin\\Desktop\\新的.pdf";
        //这里下面就不用改了，刷新Maven之后直接跑主方法
        Boolean bool = mergePdfFiles(files,newFile);
        System.out.println(bool);
    }

    private static Boolean mergePdfFiles(String[] files, String newFile) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document,new FileOutputStream(newFile));
            document.open();
            for (int i=0;i<files.length;i++){
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j=1;j<=n;j++){
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader,j);
                    copy.addPage(page);
                }
            }
            retValue=true;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("完成");
            document.close();
        }
        return retValue;
    }
}
