package com.kcm.common.properties;

import java.io.*;
import java.util.Properties;

/**
 * 解析配置文件信息
 * 
 * @author 胡波
 *
 */
public class PropertiesUtil {
	private String properiesName = "";

	public PropertiesUtil() {
	}

	public PropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}

	public String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					this.properiesName);
			Properties p = new Properties();
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				is.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					this.properiesName);
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				is.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			is = new FileInputStream(this.properiesName);
			p.load(is);
			os = new FileOutputStream(PropertiesUtil.class.getClassLoader()
					.getResource(this.properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (is != null)
				{is.close();}
				if (os != null)
				{os.close();}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (is != null)
				{is.close();}
				if (os != null)
				{os.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
