import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.util.IOUtils;

import com.model.ApplicationFile;
import com.utils.FileUtils;

public class ZipFactory {

	public static void main(String[] args) throws Exception {	
		existingZip();
	}
	
	public static void existingZip() throws IOException{
		final List<ApplicationFile> ApplicationFiles = new LinkedList<ApplicationFile>();
		String sourceFile = "C:\\Users\\smukherjee\\Desktop\\src\\company-logo-complete.png";
		File fileToZip1 = new File(sourceFile);
		FileInputStream fis1 = new FileInputStream(fileToZip1);
		byte[] bytes = IOUtils.toByteArray(fis1);
		ApplicationFile f =  new ApplicationFile("JIHNA_"+fileToZip1.getName(), bytes);
		ApplicationFiles.add(f);
		File fileToZip12 = new File(sourceFile);
        FileInputStream fis2 = new FileInputStream(fileToZip12);
        bytes = IOUtils.toByteArray(fis2);
        f =  new ApplicationFile("secondDir\\PINHA_"+fileToZip12.getName(), bytes);
        ApplicationFiles.add(f);
        fis1.close();
        fis2.close();
        FileUtils.generateArbitraryFile(FileUtils.getZippedBytes(ApplicationFiles), "C:\\Users\\smukherjee\\Desktop\\src\\compressed_new.zip");
	}
	
	public static void plainZipFile() throws IOException {
		String sourceFile = "C:\\Users\\smukherjee\\Desktop\\src\\company-logo-complete.png";
        FileOutputStream fos = new FileOutputStream("C:\\Users\\smukherjee\\Desktop\\src\\compressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip1 = new File(sourceFile);
        FileInputStream fis1 = new FileInputStream(fileToZip1);
        ZipEntry zipEntry1 = new ZipEntry("JIHNA_"+fileToZip1.getName());
        zipOut.putNextEntry(zipEntry1);
        byte[] bytes = new byte[1024];
        int length;
        while((length = fis1.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        File fileToZip12 = new File(sourceFile);
        FileInputStream fis2 = new FileInputStream(fileToZip12);
        ZipEntry zipEntry2 = new ZipEntry("secondDir\\PINHA_"+fileToZip12.getName());
        zipOut.putNextEntry(zipEntry2);
        bytes = new byte[1024];
        while((length = fis2.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis1.close();
        fis2.close();
        fos.close();
        System.out.println("DONE");
	}
	
	public static void previousCode() throws Exception {
		URL url = new URL("http://imageserver.seekmentore.com/images/company-logo-complete.png");
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();
		byte[] bytes = IOUtils.toByteArray(inputStream);
		FileUtils.generateArbitraryFile(bytes, "C:\\Users\\smukherjee\\Desktop\\src\\company-logo-complete.png");
		BufferedImage img = ImageIO.read(url);
		File file = new File("C:\\Users\\smukherjee\\Desktop\\src\\company-logo-complete.png");
		ImageIO.write(img, "png", file);
	}

}
