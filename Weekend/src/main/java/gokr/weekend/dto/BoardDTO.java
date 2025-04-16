package gokr.weekend.dto;

public class BoardDTO {
	//멤버 변수 선언
    private String cno; 
	private String ctitle;
    private String ctext;
    private java.sql.Date cwdate;
    private Number cviewcount;
    private String cwuser;
    private String cpw;
    private String cofile;
    private String csfile;
    
    
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public String getCtext() {
		return ctext;
	}
	public void setCtext(String ctext) {
		this.ctext = ctext;
	}
	public java.sql.Date getCwdate() {
		return cwdate;
	}
	public void setCwdate(java.sql.Date cwdate) {
		this.cwdate = cwdate;
	}
	public Number getCviewcount() {
		return cviewcount;
	}
	public void setCviewcount(Number cviewcount) {
		this.cviewcount = cviewcount;
	}
	public String getCwuser() {
		return cwuser;
	}
	public void setCwuser(String cwuser) {
		this.cwuser = cwuser;
	}
	public String getCpw() {
		return cpw;
	}
	public void setCpw(String cpw) {
		this.cpw = cpw;
	}
	public String getCofile() {
		return cofile;
	}
	public void setCofile(String cofile) {
		this.cofile = cofile;
	}
	public String getCsfile() {
		return csfile;
	}
	public void setCsfile(String csfile) {
		this.csfile = csfile;
	}


}
