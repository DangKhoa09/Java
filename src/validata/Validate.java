package validata;


import java.time.LocalDate;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.regex.Pattern;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.UtilDateModel;


public class Validate extends JFrame {
	String rexSpecial ="[^$#*%~`!?{}=;^]";
	 long millis=System.currentTimeMillis();  
     java.sql.Date toDay= new java.sql.Date(millis);  
	
     
     //regex giao diện quản lý công trình
public boolean rThemCongTrinh(JTextField txt1,UtilDateModel dateStart,UtilDateModel dateEnd,UtilDateModel dateExpected,JTextField txt2,JTextField txt3) {
	
	String str1 = txt1.getText();
	String str2 = txt2.getText();
	String str3 = txt3.getText();
	Date ngaycap = Date.valueOf(LocalDate.of(dateStart.getYear(), dateStart.getMonth() + 1, dateStart.getDay()));
	Date ngaystart= Date.valueOf(LocalDate.of(dateEnd.getYear(), dateEnd.getMonth() + 1, dateEnd.getDay()));
	Date ngayend = Date.valueOf(LocalDate.of(dateExpected.getYear(), dateExpected.getMonth() +1, dateExpected.getDay()));
		
	if(str1.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Tên công trình không được trống!");
		txt1.requestFocus();
		return false;
	}
	else if(!Pattern.matches("[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẾẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ '_0-9]+", str1)) {
		JOptionPane.showMessageDialog(this, "Tên chỉ chứa chữ cái, số, dấu ' và dấu gạch dưới");
		txt1.requestFocus();
		txt1.selectAll();
		return false;
	}else if(!validDate(ngaycap, ngaystart)) {
		JOptionPane.showMessageDialog(this, "Ngày khởi công phải sau ngày cấp phép");
		return false;
	}else if(!validDate(ngaystart, ngayend)) {
		JOptionPane.showMessageDialog(this, "Ngày dự kiến hoàn thành sau ngày khởi công");
		return false;
	}
	
	else if(str2.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Địa điểm không được trống!");
		txt2.requestFocus();
		return false;
	}
	else if(str3.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Số lượng nhân công không được trống!");
		txt3.requestFocus();
		return false;
	}else {
		try {
			int num = Integer.parseInt(txt3.getText().trim());
			if(num<=4) {
				JOptionPane.showMessageDialog(this,"Số nhân công của công trình phải lớn hơn 4");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Nhập số!");
			txt3.requestFocus();
			txt3.selectAll();
			return false;
		}
	}		
	 return true;
	
}

//regex giao diện quản lý lao động
public boolean rThemLaoDong(JTextField txt1,UtilDateModel birthday,UtilDateModel workingDay,JTextField txt2,JTextField txt3,JTextField txt4) {
	String str1 = txt1.getText();
	String str2 = txt2.getText();
	String str3 = txt3.getText();
	String str4 = txt4.getText();
	int tuoi = - birthday.getYear() + java.time.LocalDateTime.now().getYear();
	Date ngaysinh = Date.valueOf(LocalDate.of(birthday.getYear(), birthday.getMonth() + 1, birthday.getDay()));
	Date ngaylam= Date.valueOf(LocalDate.of(workingDay.getYear(), workingDay.getMonth() + 1, workingDay.getDay()));
	if(str1.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Tên lao động không được trống!");
		txt1.requestFocus();
		return false;
	}
	if(!Pattern.matches("[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẾẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ']+", str1)) {
		JOptionPane.showMessageDialog(this, "Tên chỉ chứa chữ cái, khoảng trắng và dấu '");
		txt1.requestFocus();
		txt1.selectAll();
		return false;
	}
	 if(!validDate(ngaysinh,toDay )) {	
		 JOptionPane.showMessageDialog(this, "Ngày sinh phải trước ngày hiện tại");
			return false;
		}
	 if(!validDate( ngaysinh,ngaylam)) {	
		 JOptionPane.showMessageDialog(this, "Ngày vào làm sau ngày sinh");
			return false;
		}
	if(str2.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Phải điền trường này!");
		txt2.requestFocus();
		return false;
	}
	if(str3.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Phải điền trường này!");
		txt3.requestFocus();
		return false;
	}
	if(!str3.matches("[0-9]{10,11}")) {
		JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 hoặc 11 số!");
		txt3.requestFocus();
		return false;
	}
	if(str4.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Phải điền trường này!");
		txt4.requestFocus();
		return false;
	}
	if(!(str4.matches("[0-9]{9}") || str4.matches("[0-9]{12}") )) {
		JOptionPane.showMessageDialog(this, "CMND phải đúng 9 số và CCCD phải đúng 12 số");
		txt4.requestFocus();
		txt4.selectAll();
		return false;
	}
	if(tuoi<21) {
		JOptionPane.showMessageDialog(this,"Tuổi người lao động phải từ 21 trở lên");
		return false;
	}
	
	return true;
	
}
//regex giao diện chuyên môn
public boolean rChuyenMon(JTextField txt) {
	String str = txt.getText();
	if(str.trim().equalsIgnoreCase("")) {
		JOptionPane.showMessageDialog(this, "Nhập tên chuyên môn");
		txt.requestFocus();
		return false;
	}
	if(!Pattern.matches("[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẾẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ']+", str)) {
		JOptionPane.showMessageDialog(this, "Tên chỉ chứa chữ cái, khoảng trắng và dấu '");
		txt.requestFocus();
		txt.selectAll();
		return false;
	}
	return true;
	
}
private boolean validDate(Date start,Date end) {
	if(!end.after(start)) {
		
		return false;
	}
	return true;
}
}
