package main;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 import main.ItemsDao;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 4; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 5; // 50MB
 

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        // 检测是否为多媒体上传
    	
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
 
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        //String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
        String uploadPath = "/root/apache-tomcat-7.0.96/webapps/TomcatTest/" +UPLOAD_DIRECTORY;
        System.out.println(uploadPath);
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            //List<FileItem> formItems = upload.parseRequest(request);
            Map<String, String> dataMap = new HashMap<String, String>();
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                	if (item.isFormField()) {
                        String key = item.getFieldName();//获取input中name值
                        String value = item.getString("UTF-8");//获取input中value值
                        // 把获取到的数据保存到map中
                        dataMap.put(key, value);
                    }
                	
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        String key = "filename";//获取input中name值
                        String value =  "upload/"+fileName ;//获取input中value值
                        dataMap.put(key, value);
                        // 把获取到的数据保存到map中
                        dataMap.put(key, value);
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                            "文件上传成功!");
                    }
                }
                
                ItemBean itemBean=new ItemBean();
                itemBean.setItemId(dataMap.get("itemId"));
                itemBean.setItemName(dataMap.get("itemName"));
                itemBean.setFilename(dataMap.get("filename"));
                itemBean.setItemPrice(Double.parseDouble(dataMap.get("itemPrice")));
                itemBean.setItemStock(Integer.parseInt(dataMap.get("itemStock")));
        		int b=ItemsDao.check(itemBean.getItemId());
        		
        		System.out.print("b:"+b);
        		
        		if(b==0)//数据库id不存在，插入
        		{
        			if((ItemsDao.insert(itemBean))==1)
        			System.out.println("插入成功！");
        			response.sendRedirect("ListItemServlet2");
        	
        			
        		}
        		else 
        		{
        			request.setAttribute("DBMes", "该商品编号已存在");
        			request.getSession().setAttribute("itemBean",itemBean);
        			request.getRequestDispatcher("/addItems.jsp").forward(request, response);
        			
        		}
        		
               // System.out.println(itemBean.getFilename()+itemBean.getItemId()+itemBean.getItemName());
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        // 跳转到 message.jsp
       // getServletContext().getRequestDispatcher("/upload.jsp").forward( request, response);
    }
}