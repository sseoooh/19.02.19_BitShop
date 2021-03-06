package command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import proxy.ImageProxy;
import proxy.Proxy;
import proxy.RequestProxy;
import service.CustomerServiceImpl;
import service.ImageServiceImpl;

import com.oreilly.servlet.MultipartRequest;

import domain.CustomerDTO;
import domain.ImageDTO;
import enums.Action;

public class FileCommand extends Command {

	public FileCommand(Map<String, Proxy> pxy) {
		super(pxy);
		RequestProxy req = (RequestProxy) pxy.get("req");
		HttpServletRequest request = req.getRequest();
		
		switch(Action.valueOf(request.getParameter("cmd").toUpperCase())) {
		case CUST_FILE_UPLOAD :
			System.out.println("파일커맨드 파일업로드 진입");
			ImageProxy ipxy = new ImageProxy();
			ipxy.carryOut(request);
			ImageDTO image = ipxy.getImg();
			System.out.println("여기서뜨냐????????????????????"+ipxy.getImg());
			String customerID = ipxy
									.getImg()
									.getOwner();
	
			CustomerDTO cus = new CustomerDTO();
			cus.setCustomerID(customerID);
			cus = CustomerServiceImpl
					.getInstance()
					.retrieveCustomer(cus);
			request.setAttribute("image", image);
			request.setAttribute("cus", cus);
			break;
		default :
			break;
			}
		
		}
	}

