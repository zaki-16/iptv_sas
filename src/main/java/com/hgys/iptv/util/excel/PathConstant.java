package com.hgys.iptv.util.excel;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PathConstant {

	/**
	 * @return
	 * @throws IOException
	 */
	public static String getExcelExportResource() throws IOException {
//		return ResourceUtils.getFile("classpath:excel").toString();
//		return 	new ClassPathResource("excel").getFile().getPath();
		return ClassUtils.getDefaultClassLoader().getResource("excel").getPath();
	}


}
