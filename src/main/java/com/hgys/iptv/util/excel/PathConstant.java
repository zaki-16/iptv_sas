package com.hgys.iptv.util.excel;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PathConstant {

	/**
	 * @return
	 * @throws IOException
	 */
	public static String getExcelExportResource() throws FileNotFoundException {
		return ResourceUtils.getFile("classpath:excel").toString();
	}


}
