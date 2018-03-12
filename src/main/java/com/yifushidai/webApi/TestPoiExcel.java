//package com.yifushidai.webApi;
//
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.wp.usermodel.Paragraph;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import java.awt.*;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//
///**
// * Created by liumei on 2017/11/20 17:15.
// * desc:
// */
//@RestController
//public class TestPoiExcel {
//    //导出数据为excel并下载
//    @SuppressWarnings("deprecation")
//    @GetMapping("/downExcel")
//    public void downexcel(HttpServletResponse response, HttpSession session)
//            throws UnsupportedEncodingException {
//        // 获取当前条件下的还款计划
//        @SuppressWarnings("unchecked")
//        List<Plan> plans = (List<Plan>) session.getAttribute("plans");
//        // 下面是生成Excel的操作
//        // 1.创建一个workbook，对应一个Excel文件
//        @SuppressWarnings("resource")
//        HSSFWorkbook wb = new HSSFWorkbook();
//        // 2.在workbook中添加一个sheet，对应Excel中的一个sheet
//        HSSFSheet sheet = wb.createSheet("还款计划");
//
//        // PdfWriter.getInstance(sheet, response.getOutputStream());
//        // 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
//        HSSFRow row = sheet.createRow((int) 0);
//        // 4.创建单元格，设置值表头，设置表头居中
//        HSSFCellStyle style = wb.createCellStyle();
//        // 居中格式
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 设置表头
//        HSSFCell cell = row.createCell((short) 0);
//        cell.setCellValue("期次");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(1);
//        cell.setCellValue("还款金额");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(2);
//        cell.setCellValue("当期本金");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(3);
//        cell.setCellValue("当期利息");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(4);
//        cell.setCellValue("剩余本金");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(5);
//        cell.setCellValue("还款时间");
//        cell.setCellStyle(style);
//
//        // 循环将数据写入Excel
//
//        DecimalFormat df=new DecimalFormat("#0.00");//double类型数据的格式化
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0; i < plans.size(); i++) {
//            row = sheet.createRow((int) i + 1);
//            Plan plan = plans.get(i);
//            // 创建单元格，设置值
//            row.createCell(0).setCellValue(plan.getId());
//            row.createCell(1).setCellValue(df.format(plan.getRepayAmount()));
//            row.createCell(2).setCellValue(df.format(plan.getNowAmount()));
//            row.createCell(3).setCellValue(df.format(plan.getInterest()));
//            row.createCell(4).setCellValue(df.format(plan.getBalance()));
//            row.createCell(5).setCellValue(sdf.format(plan.getPlanDate()));
//        }
//        response.setContentType("application/vnd.ms-excel");
//        String codedFileName = java.net.URLEncoder.encode("还款计划.xls", "UTF-8");
//        response.setHeader("content-disposition", "attachment;filename="+ codedFileName);
//
//        try {
//            OutputStream fOut = response.getOutputStream();
//            wb.write(fOut);
//            fOut.flush();
//            fOut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //导出数据为pdf
//    @GetMapping("/downPDF")
//    public void downpdf(HttpServletResponse response, HttpSession session) {
//        // 获取当前条件下的还款计划
//        @SuppressWarnings("unchecked")
//        List<Plan> plans = (List<Plan>) session.getAttribute("plans");
//        Document document = new Document();
//        // 加入中文字体
//        try {
//            // BaseFont bfChinese=BaseFont.createFont("STSong-Light",
//            // "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//            BaseFont bfChinese = BaseFont.createFont(
//                    "C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H,
//                    BaseFont.EMBEDDED);
//            Font keyfont = new Font(bfChinese, 8, Font.BOLD);
//            // Font textfont=new Font(bfChinese,8,Font.NORMAL);
//            response.setContentType("application/octet-stream");
//            String codedFileName = java.net.URLEncoder.encode("还款计划.pdf","UTF-8");
//            response.setHeader("Content-Disposition", "attachment;filename="+ codedFileName);
//            PdfWriter.getInstance(document, response.getOutputStream());
//            // PdfWriter.getInstance(document,"E:/日志信息.pdg");
//            // response.setHeader("", "");
//            document.open();
//            DecimalFormat df=new DecimalFormat("#0.00");//double类型数据的格式化
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//            for (Plan plan : plans) {
//                document.add(new Paragraph("期次：" + plan.getId(), keyfont));
//                document.add(new Paragraph("还款金额：" + df.format(plan.getRepayAmount()),keyfont));
//                document.add(new Paragraph("当期本金：" + df.format(plan.getNowAmount()),keyfont));
//                document.add(new Paragraph("当期利息：" + df.format(plan.getInterest()),keyfont));
//                document.add(new Paragraph("剩余本金：" + df.format(plan.getBalance()), keyfont));
//                document.add(new Paragraph("还款日期：" + sdf.format(plan.getPlanDate()), keyfont));
//            }
//            document.close();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public void test(){
//
//    }
//}
