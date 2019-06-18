package com.pcb.management.controller;

import com.pcb.management.model.PCB;
import com.pcb.management.model.PCBList;
import com.pcb.management.service.IPCBService;
import com.pcb.management.util.ExportExcelUtil;
import com.pcb.management.util.ImportExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author : ErGouBigDevil
 * @date : 2019/5/21
 * @time : 11:20
 */
@Controller
public class PCBController {
    @Resource(name = "PCBService")
    IPCBService pcbService;

    ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
    ImportExcelUtil importExcelUtil = new ImportExcelUtil();


    private void writeJSON2Response(Object out, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // System.out.println("SERVER: " + out);
            response.getWriter().print(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param limit, offset, sort, order, response
     * @return void
     * @Description 分页输出pcb数据
     * @author ErGouBigDevil
     * @date 2019/5/25
     */

    @RequestMapping(value = "/getAllInfo", method = RequestMethod.GET)
    @ResponseBody
    public void getAllInfo(@RequestParam(value = "limit", defaultValue = "5") Integer limit,
                           @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                           @RequestParam(value = "sort") String sort,
                           @RequestParam(value = "order") String order,

                           HttpServletResponse response) {
        int size = pcbService.count();
        PCBList pcbList = new PCBList();
        List<PCB> list = pcbService.findAll(offset, limit, sort, order);
        if (list != null) {
            pcbList.setRows(list);
            pcbList.setTotal(size);
        }
        writeJSON2Response(pcbList.toString(), response);
    }


    /**
     * @param ids, response
     * @return void
     * @Description 删除pcb信息 可批量删除，亦可可单个删除
     * @author ErGouBigDevil
     * @date 2019/5/25
     */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    @ResponseBody
    public void deleteByIds(@RequestParam(value = "ids") String ids, HttpServletResponse response) {
        String[] idArray = ids.split(",");
        Object result;
        try {
            for (String id : idArray)
                pcbService.deletePCBById(Integer.valueOf(id));
            result = "{\"flag\":true}";
        } catch (Exception e) {
            result = "{\"flag\":false}";
        }
        writeJSON2Response(result, response);
    }


    /**
     * @param pcb, response
     * @return void
     * @Description 添加pcb信息
     * @author ErGouBigDevil
     * @date 2019/5/25
     */
    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    @ResponseBody
    public void insertData(@RequestBody PCB pcb, HttpServletResponse response) {
        Object result = "{\"flag\":false}";
        if (pcbService.insertPCB(pcb) > 0)
            result = "{\"flag\":true}";
        writeJSON2Response(result, response);
    }

    /**
     * @param pcb, response
     * @return void
     * @Description 修改数据
     * @author ErGouBigDevil
     * @date 2019/5/25
     */
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    @ResponseBody
    public void updateData(@RequestBody PCB pcb, HttpServletResponse response) {
        Object result = "{\"flag\":false}";
        if (pcbService.updatePCB(pcb) > 0)
            result = "{\"flag\":true}";
        writeJSON2Response(result, response);
    }

    /**
     * @param pcb, response
     * @return void
     * @Description 精确搜索
     * @author ErGouBigDevil
     * @date 2019/5/26
     */
    @RequestMapping(value = "/accurateSearch", method = RequestMethod.POST)
    @ResponseBody
    public void accurateSearch(@RequestBody PCB pcb, HttpServletResponse response) {
        PCBList pcbList = new PCBList();
        List<PCB> list = pcbService.accurateSearch(pcb);
        if (list != null) {
            pcbList.setRows(list);
            pcbList.setTotal(list.size());
        }
        writeJSON2Response(pcbList.toString(), response);
    }


    /**
     * @param keyWord, response
     * @return void
     * @Description 模糊搜索
     * @author ErGouBigDevil
     * @date 2019/5/26
     */
    @RequestMapping(value = "/fuzzSearch", method = RequestMethod.POST)
    @ResponseBody
    public void accurateSearch(@RequestParam(value = "keyWord") String keyWord, HttpServletResponse response) {
        PCBList pcbList = new PCBList();
        List<PCB> list = pcbService.fuzzSearch(keyWord);
        if (list != null) {
            pcbList.setRows(list);
            pcbList.setTotal(list.size());
        }
        writeJSON2Response(pcbList.toString(), response);
    }


    /**
     * @param response, id, name
     * @return java.lang.String
     * @Description
     * @author ErGouBigDevil
     * @date 2019/6/3
     */
    @RequestMapping(value = "downloadExcel")
    public @ResponseBody
    String downloadExcel(HttpServletResponse response) {
        response.setContentType("application/binary;charset=UTF-8");
        try {
            ServletOutputStream out = response.getOutputStream();
            try {
                response.setHeader("Content-Disposition",
                        "attachment;fileName=" + URLEncoder.encode("data" + ".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            String[] titles = {"id", "name", "address", "type"};
            exportExcelUtil.export(titles, pcbService.findAllData(), out);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "导出信息失败";
        }
    }

    /**
     * @param file, response
     * @return void
     * @Description
     * @author ErGouBigDevil
     * @date 2019/6/3
     */

    @RequestMapping(value = "uploadExcel")
    public void ajaxUploadExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("文件不存在！");
        }

        InputStream in = null;
        in = file.getInputStream();
        List<List<Object>> listob = null;
        listob = importExcelUtil.getPCBListByExcel(in, file.getOriginalFilename());

        System.out.println(listob.size());
        for (List<Object> lo : listob) {
            PCB pcbTemp = new PCB(Integer.parseInt(lo.get(0).toString()), lo.get(1).toString(), lo.get(2).toString(), lo.get(3).toString());

            pcbService.insertPCB(pcbTemp);
            System.out.println("打印信息-->" + pcbTemp.toString());
        }

        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");
        out = response.getWriter();
        out.print("文件导入成功！");
        out.flush();
        out.close();
    }

}
