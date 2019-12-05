package cn.zeffect.thy.bean;

public class ResultInfo {
	private int code = -1;
	private String msg = "";
	private Object data;

	public ResultInfo(int tmpCode) {
		this.code = tmpCode;
	}

	public ResultInfo(int tmpCode, String tmpMsg) {
		this.code = tmpCode;
		this.msg = tmpMsg;
	}

	public ResultInfo(int tmpCode, String tmpMsg, Object tmpData) {
		this.code = tmpCode;
		this.msg = tmpMsg;
		this.data = tmpData;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
