package com.hgys.iptv.util.excel;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class PathConstant {

	private static final String fileRootDir = File.separator+"file";

	//excel模板文件夹
	private static final String excelExportTemplementDir = fileRootDir+File.separator+"EXCEL_templement";

	/**
	 * Excel模板路径
	 * @param request
	 * @return
	 */
	public static String getExcelExportWebDir(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("")+excelExportTemplementDir;
	}

}
