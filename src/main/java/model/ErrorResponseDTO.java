package model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseDTO {
	private String err;
	private List<String> detail = new ArrayList<String>();
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public List<String> getDetail() {
		return detail;
	}
	public void setDetail(List<String> detail) {
		this.detail = detail;
	}

}

