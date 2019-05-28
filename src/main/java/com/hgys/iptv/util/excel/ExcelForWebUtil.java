package com.hgys.iptv.util.excel;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

public class ExcelForWebUtil {

	/**
	 * 根据模板，导出excel
	 *
	 * 注意 web 链接，返回为null;
	 *
	 * @param dir
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *
	 */
	public static void exportExcel(HttpServletResponse response,
                                   Map<String, Object> beanParams, String templementFile, String dir,
                                   String fileName) {
		try {
			String url = dir + File.separator + templementFile;
			FileInputStream fin = new FileInputStream(url);
			XLSTransformer transformer = new XLSTransformer();
			Workbook workBook = transformer.transformXLS(fin, beanParams);
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + "\"");
			ServletOutputStream out = response.getOutputStream();
			workBook.write(out);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据模板，导出excel
	 *
	 * 注意 web 链接，返回为null;
	 *
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *
	 */
	public static void exportExcelLiunx(HttpServletResponse response,
                                   Map<String, Object> beanParams, String templementFile, String fileName) {
		try {
			ClassPathResource classPathResource = new ClassPathResource("excel/" + templementFile);
			InputStream inputStream =classPathResource.getInputStream();

			XLSTransformer transformer = new XLSTransformer();
			Workbook workBook = transformer.transformXLS(inputStream, beanParams);
			// ContentType 可以不设置
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes(), "iso-8859-1") + ".xlsx");
			ServletOutputStream out = response.getOutputStream();
			workBook.write(out);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void workBookExportExcel(HttpServletResponse response,Workbook workBook,String fileName) {
		try {
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes(), "iso-8859-1") + ".xls");
			ServletOutputStream out = response.getOutputStream();
			workBook.write(out);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
