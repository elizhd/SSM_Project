package com.pcb.management.util;

import com.pcb.management.model.PCB;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * @author : Haodong Zhao
 * @date : 2019/6/3
 * @time : 9:02
 */
public class ExportExcelUtil {
    public void export(String[] titles, List<PCB> list, ServletOutputStream out) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            // 第五步，写入实体数据
            for (int i = 0; i < list.size(); i++) {
                row = hssfSheet.createRow(i+1);
                PCB pcb = list.get(i);

                // 第六步，创建单元格，并设置值
                String id = String.valueOf(pcb.getId());
                row.createCell(0).setCellValue(id);

                String name = "";
                if(pcb.getName() != null)
                    name = pcb.getName();
                row.createCell(1).setCellValue(name);


                String address = "";
                if(pcb.getAddress() != null)
                    address = pcb.getAddress();
                row.createCell(2).setCellValue(address);

                String type = null;
                if(pcb.getType() !=null)
                    type = pcb.getType();
                row.createCell(3).setCellValue(type);
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }
    }
}
