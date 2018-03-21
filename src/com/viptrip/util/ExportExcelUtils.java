package com.viptrip.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcelUtils {

	/**
	 * 创建sheet页，可包含多个table
	 * 
	 * @param workbook
	 * @param sheetNum
	 *            sheet编号
	 * @param sheetName
	 *            sheet名称
	 * @param sheetTitle
	 *            sheet标题
	 * @param tableTitles
	 *            table的标题
	 * @param tablesHeaders
	 *            table的表头 List<String[]> String[]为一个表格的表头
	 * @param tables
	 *            table的内容 List<List<List<String>>>
	 *            最内层的List<String>为一行数据与tablesHeaders中的长度一一对应
	 * @param sheetTitleFont
	 *            sheet的标题字体设置
	 * @param tableTitleFont
	 *            table的标题字体设置
	 * @param tableHeaderFont
	 *            table的表头字体设置
	 * @param contentFont
	 *            主体内容字体设置
	 * @param sheetTitleHeight
	 *            sheet标题高度设置
	 * @param tableTitleHeight
	 *            table标题高度设置
	 * @param tableHeaderHeight
	 *            table表头高度设置
	 * @param contentHeight
	 *            主体内容高度设置
	 */
	public static void createSheetWithTables(HSSFWorkbook workbook,
			int sheetNum, String sheetName, String sheetTitle,
			List<String> tableTitles, List<String[]> tablesHeaders,
			List<List<List<String>>> tables, HSSFFont sheetTitleFont,
			HSSFFont tableTitleFont, HSSFFont tableHeaderFont,
			HSSFFont contentFont, short sheetTitleHeight,
			short tableTitleHeight, short tableHeaderHeight, short contentHeight) {
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetName);
		int maxColsNum = 0;// 用来保存所以table中最大列数，即是sheet的标题需要合并的列数
		if (null != tablesHeaders && tablesHeaders.size() > 0) {
			for (String[] headers : tablesHeaders) {
				if (null != headers && headers.length > 0) {
					maxColsNum = Math.max(maxColsNum, headers.length);
				}
			}
		}
		for (int i = 0; i < maxColsNum; i++) {
			sheet.setColumnWidth(i, 5 * 2 * 256);// 设置默认列宽为10
		}
		// 以下设置sheet的标题
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.setHeight((short) (sheetTitleHeight * 20));// 设置行高
		Cell cell = headerRow.createCell(0);
		cell.setCellValue(sheetTitle);
		HSSFCellStyle sheetTitleStyle = workbook.createCellStyle();// sheet标题样式
		sheetTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
		sheetTitleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		if (null != sheetTitleFont) {// 设置sheet标题字体
			sheetTitleStyle.setFont(sheetTitleFont);
		}
		cell.setCellStyle(sheetTitleStyle);
		CellRangeAddress region = new CellRangeAddress(0, 0, 0, Math.max(
				maxColsNum - 1, 0));// sheet标题合并单元格

		sheet.addMergedRegion(region);
		// 设置标题到此为止

		// 遍历所有的table 逐个输出
		int rowNum = 2;// 记录行号
		for (int idx = 0; idx < tablesHeaders.size(); idx++) {
			String tableTitle = tableTitles.get(idx);
			String[] header = tablesHeaders.get(idx);
			List<List<String>> contents = tables.get(idx);
			if (null != contents && contents.size() > 0) {// 内容不能为空
				int mergeRowNum = rowNum;

				// 第一行为table的标题
				HSSFRow titleRow = sheet.createRow(rowNum++);
				titleRow.setHeight((short) (tableTitleHeight * 20));// 设置行高
				HSSFCellStyle tableTitleStyle = workbook.createCellStyle();// table标题样式
				tableTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
				tableTitleStyle
						.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
				tableTitleStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);// 上加粗
				tableTitleStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左加粗
				tableTitleStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右加粗
				for (int i = 0; i < header.length; i++) {
					HSSFCell titleRowCell = titleRow.createCell(i);
					titleRowCell.setCellValue(tableTitle);
					if (null != tableTitleFont) {
						tableTitleStyle.setFont(tableHeaderFont);
					}
					titleRowCell.setCellStyle(tableTitleStyle);
				}
				CellRangeAddress reg = new CellRangeAddress(mergeRowNum,
						mergeRowNum, 0, contents.get(0).size() - 1);// 合并单元格
				sheet.addMergedRegion(reg);

				// 第二行为table的表头
				HSSFRow row = sheet.createRow(rowNum++);
				row.setHeight((short) (tableHeaderHeight * 20));// 设置行高
				for (int i = 0; i < header.length; i++) {
					HSSFCell c = row.createCell(i);
					c.setCellValue(header[i]);
					HSSFCellStyle tableHeaderStyle = workbook.createCellStyle();
					tableHeaderStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
					tableHeaderStyle
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
					setBorderArround_THIN(tableHeaderStyle);// 设置外边框
					if (0 == i) {// 第一列
						tableHeaderStyle
								.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左加粗
					}
					if (i == header.length - 1) {// 最后一列
						tableHeaderStyle
								.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右加粗
					}
					if (null != tableHeaderFont) {// 设置表头字体
						tableHeaderStyle.setFont(tableHeaderFont);
					}
					setColWidth(sheet.getColumnWidth(i),
							getCNStrLen(header[i]) * 256, sheet, i);// 设置列宽
					c.setCellStyle(tableHeaderStyle);
				}

				// 设置table的内容
				for (int i = 0; i < contents.size(); i++) {
					HSSFRow r = sheet.createRow(rowNum++);
					r.setHeight((short) (contentHeight * 20));// 设置行高
					List<String> rowValues = contents.get(i);
					for (int j = 0; j < rowValues.size(); j++) {
						HSSFCellStyle style = workbook.createCellStyle();
						// style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
						style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
						HSSFCell c = r.createCell(j);
						c.setCellValue(rowValues.get(j));
						setBorderArround_THIN(style);// 设置外边框
						if (i == (contents.size() - 1)) {// 最后一行
							style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);// 下加粗
						}
						if (j == 0) {// 第一列
							style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左加粗
						} else if (j == (rowValues.size() - 1)) {// 最后一列
							style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右加粗
						}
						if (null != contentFont) {// 设置正文字体
							style.setFont(contentFont);
						}
						setColWidth(sheet.getColumnWidth(j),
								getCNStrLen(rowValues.get(j)) * 256, sheet, j);// 设置列宽
						c.setCellStyle(style);
					}
				}
				sheet.createRow(rowNum++);// 空一行
			}
		}
	}

	/**
	 * 设置正常的外边框
	 * 
	 * @param style
	 */
	private static void setBorderArround_THIN(HSSFCellStyle style) {
		if (null != style) {
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		}
	}

	/**
	 * 获取字符串长度 中文占2个
	 * 
	 * @param value
	 * @return
	 */
	private static int getCNStrLen(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 设置列宽 选择比较宽的 预留2字符
	 * 
	 * @param before
	 *            之前的列宽
	 * @param now
	 *            现在的文字宽度
	 * @param sheet
	 * @param num
	 *            列索引
	 */
	private static void setColWidth(int before, int now, Sheet sheet, int num) {
		// cellView.setSize(before>=now+5*256?before:now+5*256);
		sheet.setColumnWidth(num, before >= now + 2 * 256 ? before
				: now + 2 * 256);
	}

	public static void main(String[] args) {
		try {
			OutputStream out = new FileOutputStream("D:\\test.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();

			String sheetName = "测试sheetName";
			String sheetTitle = "测试sheetTitle";

			List<String> tableTitles = new ArrayList<String>();// 所有表的标题
			tableTitles.add("测试表title1");
			tableTitles.add("测试表title2");
			tableTitles.add("测试表title3");
			tableTitles.add("测试表title4");

			String[] header1 = { "id", "名称" };
			String[] header2 = { "编号", "名称" };
			String[] header3 = { "序号", "名称", "数量" };
			String[] header4 = { "id", "数量", "价格" };
			List<String[]> tablesHeaders = new ArrayList<String[]>();// 所有表的表头
			tablesHeaders.add(header1);
			tablesHeaders.add(header2);
			tablesHeaders.add(header3);
			tablesHeaders.add(header4);

			List<List<String>> table1 = new ArrayList<List<String>>();
			List<String> table1_row1 = new ArrayList<String>();
			table1_row1.add("11111111111111111");
			table1_row1.add("表1名称1");
			List<String> table1_row2 = new ArrayList<String>();
			table1_row2.add("2");
			table1_row2.add("表1名称2");
			List<String> table1_row3 = new ArrayList<String>();
			table1_row3.add("3");
			table1_row3.add("表1名称3");
			table1.add(table1_row1);
			table1.add(table1_row2);
			table1.add(table1_row3);

			List<List<String>> table2 = new ArrayList<List<String>>();
			List<String> table2_row1 = new ArrayList<String>();
			table2_row1.add("8");
			table2_row1.add("表2名称22222222222");
			List<String> table2_row2 = new ArrayList<String>();
			table2_row2.add("9");
			table2_row2.add("表2名称2");
			List<String> table2_row3 = new ArrayList<String>();
			table2_row3.add("10");
			table2_row3.add("表2名称3");
			table2.add(table2_row1);
			table2.add(table2_row2);
			table2.add(table2_row3);

			List<List<String>> table3 = new ArrayList<List<String>>();
			List<String> table3_row1 = new ArrayList<String>();
			table3_row1.add("12");
			table3_row1.add("表3名称1");
			table3_row1.add("12");
			List<String> table3_row2 = new ArrayList<String>();
			table3_row2.add("13");
			table3_row2.add("表3名称2_长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度");
			table3_row2.add("145");
			List<String> table3_row3 = new ArrayList<String>();
			table3_row3.add("14");
			table3_row3.add("表3名称3333333333333333333333");
			table3_row3.add("58");
			table3.add(table3_row1);
			table3.add(table3_row2);
			table3.add(table3_row3);

			List<List<String>> table4 = new ArrayList<List<String>>();
			List<String> table4_row1 = new ArrayList<String>();
			table4_row1.add("1");
			table4_row1.add("数量1");
			table4_row1.add("24.50");
			List<String> table4_row2 = new ArrayList<String>();
			table4_row2.add("2");
			table4_row2.add("数量2");
			table4_row2.add("12.50");
			List<String> table4_row3 = new ArrayList<String>();
			table4_row3.add("3");
			table4_row3.add("数量3");
			table4_row3.add("38.00");
			List<String> table4_row4 = new ArrayList<String>();
			table4_row4.add("4");
			table4_row4.add("数量4");
			table4_row4.add("16.50");
			table4.add(table4_row1);
			table4.add(table4_row2);
			table4.add(table4_row3);
			table4.add(table4_row4);

			List<List<List<String>>> contents = new ArrayList<List<List<String>>>();
			contents.add(table1);
			contents.add(table2);
			contents.add(table3);
			contents.add(table4);

			ExportExcelUtils.createSheetWithTables(workbook, 0, sheetName,
					sheetTitle, tableTitles, tablesHeaders, contents, null,
					null, null, null, (short) 30, (short) 25, (short) 20,
					(short) 20);

			// 以下测试字体设置
			/*
			 * HSSFFont sheetTitleFont = workbook.createFont();
			 * sheetTitleFont.setBold(true);//加粗
			 * sheetTitleFont.setFontHeightInPoints((short) 30);//字体大小
			 * 
			 * HSSFFont tableTitleFont = workbook.createFont();
			 * tableTitleFont.setBold(true);//加粗
			 * tableTitleFont.setFontHeightInPoints((short) 25);//字体大小
			 * 
			 * HSSFFont tableHeaderFont = workbook.createFont();
			 * tableHeaderFont.setBold(true);//加粗
			 * tableHeaderFont.setFontHeightInPoints((short) 20);//字体大小
			 * 
			 * HSSFFont contentFont = workbook.createFont();
			 * contentFont.setFontHeightInPoints((short) 20);//字体大小
			 * 
			 * ExportExcelUtils.createSheetWithTables(workbook, 0, sheetName,
			 * sheetTitle, tableTitles, tablesHeaders,
			 * contents,sheetTitleFont,tableTitleFont
			 * ,tableHeaderFont,contentFont);
			 */

			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
