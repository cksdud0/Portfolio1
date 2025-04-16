package gokr.weekend.dto;

public class WebzineDTO {
	//멤버 변수 선언
    private String wno; 
	private String wtitle;
    private String wtext;
    private java.sql.Date wwdate;
    private Number wviewcount;
    private String wofile;
    private String wsfile;
    private Number uno;
    
    
    public String getWno() {
		return wno;
	}
	public void setWno(String wno) {
		this.wno = wno;
	}
	public String getWtitle() {
		return wtitle;
	}
	public void setWtitle(String wtitle) {
		this.wtitle = wtitle;
	}
	public String getWtext() {
		return wtext;
	}
	public void setWtext(String wtext) {
		this.wtext = wtext;
	}
	public java.sql.Date getWwdate() {
		return wwdate;
	}
	public void setWwdate(java.sql.Date wwdate) {
		this.wwdate = wwdate;
	}
	public Number getWviewcount() {
		return wviewcount;
	}
	public void setWviewcount(Number wviewcount) {
		this.wviewcount = wviewcount;
	}
	public String getWofile() {
		return wofile;
	}
	public void setWofile(String wofile) {
		this.wofile = wofile;
	}
	public String getWsfile() {
		return wsfile;
	}
	public void setWsfile(String wsfile) {
		this.wsfile = wsfile;
	}
	public Number getUno() {
		return uno;
	}
	public void setUno(Number uno) {
		this.uno = uno;
	}

}
