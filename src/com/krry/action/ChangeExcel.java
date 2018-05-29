package com.krry.action;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 文字写入Excel类
 * @author asusaad
 *
 */
public class ChangeExcel {


    public static void writeExcel(List<ShopMsg> shopMsg,HttpServletRequest request) {
        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("表一");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("企业名称");
        cell = row.createCell(1);
        cell.setCellValue("企业注册号");

        //第五步，写入实体数据
        for (int i = 0; i < shopMsg.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            ShopMsg msg = shopMsg.get(i);
            //创建单元格设值
            row1.createCell(0).setCellValue(msg.getUsername());
            row1.createCell(1).setCellValue(msg.getNumber());
        }

        //将文件保存到指定的位置
        String repl = request.getServletContext().getRealPath("/");
        try {
            FileOutputStream fos = new FileOutputStream(repl+"/msg/msg.xls");
            workbook.write(fos);
            System.out.println("写入excel成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}