package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {

	public static String md5(String str) {
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bi = new BigInteger(1, md.digest());

			result = bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		// Thay Ä‘ thÃ nh d
		slug = slug.replaceAll("Ä‘", "d");
		// XÃ³a cÃ¡c kÃ½ tá»± Ä‘áº·t biá»‡t
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		// Thay space thÃ nh dáº¥u gáº¡ch ngang
		slug = slug.replaceAll("[\\s]", "-");
		// Ä�á»•i nhiá»�u kÃ½ tá»± gáº¡ch ngang liÃªn tiáº¿p thÃ nh 1 kÃ½ tá»± gáº¡ch
		// ngang
		slug = slug.replaceAll("(-+)", "-");
		// XÃ³a cÃ¡c kÃ½ tá»± gáº¡ch ngang á»Ÿ Ä‘áº§u vÃ  cuá»‘i
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}

	public static String returnMessage(HttpServletRequest request, int msg) {
		String msgText = "";
		switch (msg) {
		case 0:
			msgText = "Có lỗi trong quá trình thực hiện, vui lòng kiểm tra lại !";
			break;
		case 1:
			msgText = "Thêm thành công !";
			break;
		case 2:
			msgText = "Sửa thành công !";
			break;
		case 3:
			msgText = "Xóa thành công !";
			break;
		case 4:
			msgText = "Bạn không có quyền thêm!";
			break;
		case 5:
			msgText = "Bạn không có quyền sửa!";
			break;
		case 6:
			msgText = "Bạn không có quyền xóa!";
			break;
		case 7:
			msgText = "Id không tồn tại !";
			break;
		}
		return msgText;
	}
	
	public static Date convertIntoDate(Timestamp ts) {
		Date date = new Date(ts.getTime());
		return date;
	}
}
