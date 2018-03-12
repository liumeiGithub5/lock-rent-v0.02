package com.yifushidai.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumei on 2017/11/20 14:37.
 * desc:excel 文件上传与下载   //上传 excel 文件 ，批量导入数据库//将数据以excel 格式 导出 下载//下载excel 模板文件
 */
public class ExcelUtils {
    //private Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 判断是否为Excel
     */
    public static Boolean isExcel(MultipartFile mfile){
        if (mfile.getOriginalFilename().endsWith(".xls") || mfile.getOriginalFilename().endsWith(".xlsx")) {
            return true;
        }
        return false;
    }
    /**
     * 判断Excel是否为 空
     */
    public static Boolean isExcelNull(MultipartFile mfile){
       return false;
    }

    /**
     读取excel 表格
     */
    public static List<Object> importDataFromExcel(Object vo, InputStream is, MultipartFile mfile){
        List<Object> list = new ArrayList<>();
        try {
            //创建工作簿
            Workbook workbook = createWorkbook(is, mfile.getOriginalFilename());
            //创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = vo.getClass().getDeclaredFields();
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                Row row = sheet.getRow(i);
                int index = 0;
                while (index < cells) {
                    Cell cell = row.getCell(index);
                    if (null == cell) {
                        cell = row.createCell(index);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = null == cell.getStringCellValue()?"":cell.getStringCellValue();

                    Field field = fields[index];
                    String fieldName = field.getName();
                    String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                    Method setMethod = vo.getClass().getMethod(methodName,new Class[]{String.class});//setLockId(java.lang.String)   new Class[]{String.class}
                    setMethod.invoke(vo, new Object[]{value});//, new Object[]{value}
                    index++;
//                    System.out.println("----fieldName----"+fieldName);//fieldName----lockId  mac
//                    System.out.println("----methodName----"+methodName);//setLockId  setMac
//                    System.out.println("----setMethod----"+setMethod.toString());//public void com.yifushidai.entity.LockIdMacEntity.setMac(java.lang.String)
                }
                if (isHasValues(vo)) {//判断对象属性是否有值
                    list.add(vo);
                    //vo=new Student();
                    //vo.getClass().newInstance();
                    vo=vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e);
        }finally{
            try {
                is.close();//关闭流
            } catch (Exception e2) {
                e2.printStackTrace();
                //logger.error(e2);
            }
        }
        return list;

    }
    /**
     判断excel文件后缀名，生成不同的workbook
     */
    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        }else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     根据sheet索引号获取对应的sheet
     */
    public static Sheet getSheet(Workbook workbook, int sheetIndex){
        return workbook.getSheetAt(sheetIndex);
    }
    /**
     * 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     */
    public static boolean isHasValues(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && !"".equals(obj)) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //logger.error(e);
            }
        }
        return flag;
    }

    /*
    生成excel 表格
    */
    public static <T> void exportDataToExcel(List<T> list,String[] headers,String title,OutputStream os){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = getCellStyle(workbook);
        //生成一个字体
        HSSFFont font = getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);

        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)300);
        HSSFCell cell = null;

        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //将数据放入sheet中
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i+1);
            T t = list.get(i);
            //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
            Field[] fields = t.getClass().getFields();
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName,new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});

                    if(null == value)
                        value ="";
                    cell.setCellValue(value.toString());

                }
            } catch (Exception e) {
                e.printStackTrace();
               // logger.error(String.valueOf(e));
            }
        }

        try {
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(String.valueOf(e));
        }finally{
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
               // logger.error(String.valueOf(e));
            }
        }

    }

    /**
     生成单元格格式
     */
    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }

    /**
     生成字体样式
     */
    public static HSSFFont getFont(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short)12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return font;
    }

    public static boolean isIE(HttpServletRequest request){
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie")>0?true:false;
    }
}
