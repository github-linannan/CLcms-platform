package com.letu.healthplatform.platformmanage.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.letu.healthplatform.platformmanage.excel.entity.ImportParams;
import com.letu.healthplatform.platformmanage.excel.entity.result.ExcelImportResult;
import com.letu.healthplatform.platformmanage.excel.handler.inter.IExcelReadRowHanlder;
import com.letu.healthplatform.platformmanage.excel.imports.ExcelImportServer;
import com.letu.healthplatform.platformmanage.excel.imports.sax.SaxReadExcel;
import com.letu.healthplatform.platformmanage.excel.imports.sax.parse.ISaxRowRead;


/**
 * Excel 导入工具
 * 
 * @author JueYue
 * @date 2013-9-24
 * @version 1.0
 */
@SuppressWarnings({ "unchecked" })
public final class ExcelImportUtil {

    private ExcelImportUtil() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportUtil.class);

    /**
     * Excel 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     * 
     * @param file
     * @param pojoClass
     * @param params
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcel(File file, Class<?> pojoClass, ImportParams params) {
        FileInputStream in = null;
        List<T> result = null;
        try {
            in = new FileInputStream(file);
            result = new ExcelImportServer().importExcelByIs(in, pojoClass, params).getList();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Excel 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     * 
     * @param file  流文件
     * @param pojoClass  实体类
     * @param params 参数
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcel(InputStream inputstream, Class<?> pojoClass,
                                          ImportParams params) throws Exception {
        return new ExcelImportServer().importExcelByIs(inputstream, pojoClass, params).getList();
    }

    /**
     * Excel 导入 数据源IO流,返回校验结果 字段类型 Integer,Long,Double,Date,String,Boolean
     * 
     * @param file
     * @param pojoClass
     * @param params
     * @return
     * @throws Exception
     */
    public static <T> ExcelImportResult<T> importExcelVerify(InputStream inputstream,
                                                             Class<?> pojoClass, ImportParams params)
                                                                                                     throws Exception {
        return new ExcelImportServer().importExcelByIs(inputstream, pojoClass, params);
    }

    /**
     * Excel 导入 数据源本地文件,返回校验结果 字段类型 Integer,Long,Double,Date,String,Boolean
     * 
     * @param file
     * @param pojoClass
     * @param params
     * @return
     * @throws Exception
     */
    public static <T> ExcelImportResult<T> importExcelVerify(File file, Class<?> pojoClass,
                                                             ImportParams params) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return new ExcelImportServer().importExcelByIs(in, pojoClass, params);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * Excel 通过SAX解析方法,适合大数据导入,不支持图片
     * 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     * 
     * @param inputstream
     * @param pojoClass
     * @param params
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcelBySax(InputStream inputstream, Class<?> pojoClass,
                                               ImportParams params) {
        return new SaxReadExcel().readExcel(inputstream, pojoClass, params, null, null);
    }

    /**
     * Excel 通过SAX解析方法,适合大数据导入,不支持图片
     * 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     * 
     * @param file
     * @param rowRead
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void importExcelBySax(InputStream inputstream, Class<?> pojoClass,
                                        ImportParams params, IExcelReadRowHanlder hanlder) {
        new SaxReadExcel().readExcel(inputstream, pojoClass, params, null, hanlder);
    }

    /**
     * Excel 通过SAX解析方法,适合大数据导入,不支持图片
     * 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     * 
     * @param file
     * @param rowRead
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcelBySax(InputStream inputstream, ISaxRowRead rowRead) {
        return new SaxReadExcel().readExcel(inputstream, null, null, rowRead, null);
    }

}
