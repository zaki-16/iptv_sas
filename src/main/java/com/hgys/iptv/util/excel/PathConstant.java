package com.hgys.iptv.util.excel;

import org.springframework.util.ClassUtils;

import java.io.IOException;

public class PathConstant {

	/**
	 * @return
	 * @throws IOException
	 */
	public static String getExcelExportResource() {
		return ClassUtils.getDefaultClassLoader().getResource("excel").getPath();
	}


}
